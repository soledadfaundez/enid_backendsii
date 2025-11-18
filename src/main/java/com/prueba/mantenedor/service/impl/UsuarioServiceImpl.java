package com.prueba.mantenedor.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prueba.mantenedor.dao.UsuarioRepository;
import com.prueba.mantenedor.dto.UsuarioDto;
import com.prueba.mantenedor.mapper.UsuarioMapper;
import com.prueba.mantenedor.model.Usuario;
import com.prueba.mantenedor.request.UsuarioLoginRequest;
import com.prueba.mantenedor.request.UsuarioRequest;
import com.prueba.mantenedor.service.JwtService;
import com.prueba.mantenedor.service.UsuarioService;
import com.prueba.mantenedor.validation.EmailValidator;
import com.prueba.mantenedor.validation.PasswordEncryp;
import com.prueba.mantenedor.validation.PasswordValidator;
import com.prueba.mantenedor.web.response.ApiResponse;

@Service
public class UsuarioServiceImpl implements UsuarioService {

        // ESFC: Inyectar el repositorio y agregar los métodos del servicio
        @Autowired
        private UsuarioRepository usuarioRepository;

        @Autowired
        private UsuarioMapper usuarioMapper;

        @Autowired
        private PasswordEncryp passwordEncryp;

        @Autowired
        private JwtService jwtService;

        // ESFC: Inyectar las descripciones de los mensajes según idioma
        @Autowired
        private MessageSource messageSource;

        @Autowired
        private EmailValidator emailValidator;

        @Autowired
        private PasswordValidator passwordValidator;

        @Override
        public ResponseEntity<ApiResponse<UsuarioDto>> save(UsuarioRequest usuarioRequest, Locale locale) {

                // Tomar el locale de la request
                String message = "";

                // ESFC: Mapear UsuarioRequest a Usuario
                Usuario user = usuarioMapper.toEntity(usuarioRequest);

                // SFC: Validar que el email no esté repetido

                if (!isEmailUnique(user.getEmail())) {
                        message = messageSource.getMessage("user.email.exists", null, locale);
                        throw new IllegalArgumentException(message);
                }

                if (!emailValidator.isValidEmail(user.getEmail())) {
                        message = messageSource.getMessage("user.email.invalid", null, locale);
                        throw new IllegalArgumentException(message);
                }

                // SFC: Validar complejidad de la clave.
                if (!passwordValidator.isValidPassword(user.getPassword())) {
                        message = messageSource.getMessage("user.password.msj", null, locale);
                        throw new IllegalArgumentException(message);
                }

                // ESFC: Completar campos adicionales
                user.setLastLogin(LocalDateTime.now());
                user.setCuentaActiva(true);

                // SFC: Encriptar la clave
                String passwordEncript = passwordEncryp.encodePassword(user.getPassword());
                user.setPassword(passwordEncript);

                // ESFC: Generar token JWT
                String token = jwtService.createToken(user.getEmail());
                user.setToken(token);

                // ESFC: Insertar el usuario en la base de datos
                Usuario savedUser = usuarioRepository.save(user);

                // ESFC: Crear la respuesta con datos
                UsuarioDto usuarioResponse = new UsuarioDto(
                                savedUser.getId(),
                                savedUser.getNombre(),
                                savedUser.getEmail(),
                                savedUser.getToken(),
                                savedUser.isCuentaActiva(),
                                savedUser.getLastLogin());

                // "User created successfully"
                message = messageSource.getMessage("user.created.success", null, locale);

                ApiResponse<UsuarioDto> response = new ApiResponse<>(usuarioResponse, HttpStatus.CREATED.value(),
                                message, false);

                // ESFC: Retornar la respuesta con el estado HTTP 201
                return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        public boolean isEmailUnique(String email) {
                return usuarioRepository.findByEmail(email).isEmpty();
        }

        @Override
        public ResponseEntity<ApiResponse<List<UsuarioDto>>> findAll(Locale locale) {

                // Tomar el locale de la request
                String message = "";
                System.out.println("Locale in service: " + locale.toString());

                // ESFC: Obtener todos los usuarios
                List<Usuario> users = usuarioRepository.findAll();

                // ESFC: Mapear la lista de usuarios a UsuariosDto
                List<UsuarioDto> usuariosDto = usuarioMapper.toDtoList(users);

                // "Users retrieved successfully"
                message = messageSource.getMessage("user.list.success", null, locale);

                // ESFC: Crear la respuesta con datos
                ApiResponse<List<UsuarioDto>> response = new ApiResponse<>(usuariosDto, HttpStatus.OK.value(),
                                message, false);

                // ESFC: Retornar la respuesta con el estado HTTP 200
                return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @Override
        public ResponseEntity<ApiResponse<UsuarioDto>> login(UsuarioLoginRequest usuarioRequest, Locale locale) {

                // Tomar el locale de la request
                String message = "";
                // EFC: Buscar el usuario por email
                Optional<Usuario> optional = usuarioRepository.findByEmail(usuarioRequest.getEmail());

                // EFC: Validar si no existe
                if (!optional.isPresent()) {
                        message = messageSource.getMessage("user.not.exists", null, locale);
                        ApiResponse<UsuarioDto> response = new ApiResponse<>(null, HttpStatus.UNAUTHORIZED.value(),
                                        message, true);

                        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
                }

                // EFC: Validar la contraseña
                boolean isPasswordValid = passwordEncryp.matches(usuarioRequest.getPassword(),
                                optional.get().getPassword());
                if (!isPasswordValid) {
                        message = messageSource.getMessage("user.password.invalid", null, locale);
                        ApiResponse<UsuarioDto> response = new ApiResponse<>(null, HttpStatus.UNAUTHORIZED.value(),
                                        message, true);

                        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
                }

                // EFC: Validar si la cuenta está activa
                if (optional.get().isCuentaActiva() == false) {
                        message = messageSource.getMessage("user.account.inactive", null, locale);
                        ApiResponse<UsuarioDto> response = new ApiResponse<>(null, HttpStatus.UNAUTHORIZED.value(),
                                        message, true);
                        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
                }

                // ESFC: Completar campos adicionales
                optional.get().setLastLogin(LocalDateTime.now());

                // ESFC: Generar token JWT
                String token = jwtService.createToken(optional.get().getEmail());
                optional.get().setToken(token);

                // ESFC: Actualizar el usuario en la base de datos
                Usuario savedUser = usuarioRepository.save(optional.get());

                // ESFC: Crear la respuesta con datos
                UsuarioDto usuarioResponse = new UsuarioDto(
                                savedUser.getId(),
                                savedUser.getNombre(),
                                savedUser.getEmail(),
                                savedUser.getToken(),
                                savedUser.isCuentaActiva(),
                                savedUser.getLastLogin());

                message = messageSource.getMessage("user.login.success", null, locale);
                ApiResponse<UsuarioDto> response = new ApiResponse<>(usuarioResponse, HttpStatus.OK.value(),
                                message, false);

                // ESFC: Retornar la respuesta con el estado HTTP 200
                return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @Override
        public ResponseEntity<ApiResponse<UsuarioDto>> findByEmail(String email, Locale locale) {

                // Tomar el locale de la request
                String message = "";
                Optional<Usuario> optional = usuarioRepository.findByEmail(email);

                if (!optional.isPresent()) {
                        message = messageSource.getMessage("user.not.exists", null, locale);
                        ApiResponse<UsuarioDto> response = new ApiResponse<>(null, HttpStatus.NOT_FOUND.value(),
                                        message, true);

                        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                }

                // EFC: Retornar los datos
                UsuarioDto dto = UsuarioMapper.INSTANCE.toDto(optional.get());
                message = messageSource.getMessage("user.found.success", null, locale);

                ApiResponse<UsuarioDto> response = new ApiResponse<>(dto, HttpStatus.OK.value(),
                                message, false);

                return new ResponseEntity<>(response, HttpStatus.OK);

        }

}

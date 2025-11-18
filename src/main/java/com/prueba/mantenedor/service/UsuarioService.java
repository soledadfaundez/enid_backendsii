package com.prueba.mantenedor.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.prueba.mantenedor.dto.UsuarioDto;
import com.prueba.mantenedor.request.UsuarioLoginRequest;
import com.prueba.mantenedor.request.UsuarioRequest;
import com.prueba.mantenedor.web.response.ApiResponse;

import java.util.Locale;

public interface UsuarioService {

    /**
     * ESFC: Crear Usuario Guarda un nuevo usuario en la base de datos.
     *
     * @param user el objeto {@link User} a guardar.
     * @return el usuario guardado con el ID generado.
     */
    ResponseEntity<ApiResponse<UsuarioDto>> save(UsuarioRequest usuarioRequest, Locale locale);

    /**
     * ESFC: Recupera todos los usuarios registrados.
     *
     * @return una lista con todos los usuarios.
     */
    ResponseEntity<ApiResponse<List<UsuarioDto>>> findAll(Locale locale);

    // login
    /**
     * ESFC: Crear Usuario Guarda un nuevo usuario en la base de datos.
     *
     * @param user el objeto {@link User} a guardar.
     * @return el usuario guardado con el ID generado.
     */
    ResponseEntity<ApiResponse<UsuarioDto>> login(UsuarioLoginRequest usuarioRequest, Locale locale);

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param request el objeto HttpServletRequest que contiene información de la
     *                solicitud HTTP actual
     * @param email   el correo electrónico del usuario que se desea buscar
     * @return un ResponseEntity que envuelve un ApiResponse con un UsuarioDto:
     *         <ul>
     *         <li>200 OK: si se encuentra el usuario, contiene el UsuarioDto en
     *         ApiResponse</li>
     *         <li>404 NOT FOUND: si no se encuentra ningún usuario con el correo
     *         proporcionado</li>
     *         <li>500 INTERNAL SERVER ERROR: si ocurre un error interno durante la
     *         búsqueda</li>
     *         </ul>
     */
    ResponseEntity<ApiResponse<UsuarioDto>> findByEmail(String email, Locale locale);
}

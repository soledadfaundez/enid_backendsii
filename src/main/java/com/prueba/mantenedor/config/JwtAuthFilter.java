package com.prueba.mantenedor.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.prueba.mantenedor.dao.UsuarioRepository;
import com.prueba.mantenedor.model.Usuario;
import com.prueba.mantenedor.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

// ESFC: Este filtro permite validar el token en cada request 
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        // Obtener header Authorization
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        // Obtener email desde token
        String email = jwtService.extractUsername(token);

        if (email != null) {

            Optional<Usuario> optional = usuarioRepository.findByEmail(email);

            // Validar token
            if (jwtService.isTokenValid(token)) {

                // ESFC: Extraer roles desde el token
                List<String> roles = jwtService.extractRoles(token);

                // Convertir cada rol en GrantedAuthority con prefijo "ROLE_"

                List<GrantedAuthority> authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new) // -> "ADMIN", "USER"
                        .collect(Collectors.toList());

                // DEBUG: log de authorities
                System.out.println("JWT Authorities: " + authorities);
                System.out.println("JWT roles: " + roles);

                // EFC: Acá van los roles: authorities
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        optional.get(),
                        null,
                        authorities);

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

}

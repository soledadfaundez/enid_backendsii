package com.prueba.mantenedor.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.prueba.mantenedor.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtServiceImpl implements JwtService {

    // Inyectar la clave secreta desde las propiedades
    @Value("${jwt.secret}")
    private String secretKey;

    // Inyectar la duración del token desde las propiedades
    @Value("${jwt.expirationMs}")
    private long expirationMs;

    @Override
    public String extractUsername(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWT.require(algorithm).build().verify(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    // ESFC: Normalmente el token lo obtengo desde otro servicio, solo para fines
    // del ejercicio se genera acá.
    @Override
    public String createToken(String email) {

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create()
                .withSubject(email)
                .withIssuedAt(new Date())
                .withArrayClaim("roles", new String[] { "ADMIN", "USER" }) // ESFC: Solo para el ejercicio,
                // lo asignamos en duro; esto se carga desde alguna BD de roles
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationMs)) // ESFC: Configurar el tiempo de
                                                                                    // expiración
                .sign(algorithm);
    }

    public List<String> extractRoles(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            Claim claim = jwt.getClaim("roles");
            if (claim.isNull())
                return Collections.emptyList();
            String[] roles = claim.asArray(String.class);
            return roles != null ? Arrays.asList(roles) : Collections.emptyList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}

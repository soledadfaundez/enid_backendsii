package com.prueba.mantenedor.service;

import java.util.List;

public interface JwtService {

    String extractUsername(String token);

    boolean isTokenValid(String token);

    String createToken(String email);

    List<String> extractRoles(String token);

}

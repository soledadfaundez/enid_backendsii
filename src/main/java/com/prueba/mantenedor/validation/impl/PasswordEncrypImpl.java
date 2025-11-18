package com.prueba.mantenedor.validation.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.prueba.mantenedor.validation.PasswordEncryp;

@Component
public class PasswordEncrypImpl implements PasswordEncryp {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Encriptar la contraseña
    @Override
    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}

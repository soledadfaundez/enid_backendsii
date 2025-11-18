package com.prueba.mantenedor.validation;

public interface PasswordEncryp {
    /**
     * Encripta una contraseña en texto plano.
     * 
     * @param rawPassword La contraseña en texto plano.
     * @return La contraseña encriptada.
     */
    String encodePassword(String rawPassword);

    boolean matches(String rawPassword, String encodedPassword);
}

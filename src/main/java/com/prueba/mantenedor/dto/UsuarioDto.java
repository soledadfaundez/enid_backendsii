package com.prueba.mantenedor.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioDto(
                UUID id,
                String nombre,
                String email,
                String token,
                boolean cuentaActiva,
                LocalDateTime lastLogin) {
}

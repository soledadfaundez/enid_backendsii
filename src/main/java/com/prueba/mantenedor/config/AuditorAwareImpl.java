package com.prueba.mantenedor.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            return Optional.of("SYSTEM"); // o Optional.empty()
        }

        return Optional.ofNullable(auth.getName());
    }
}

// ESFC
// Para que @CreatedBy no salga null necesitas sí o sí tener configurado un
// AuditorAware<String> que devuelva el usuario autenticado.
// Sin esto, Spring no sabe quién es el “auditor” y deja el campo vacío.
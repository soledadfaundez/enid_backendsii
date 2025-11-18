package com.prueba.mantenedor.dao;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.mantenedor.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    // ESFC: Buscar un usuario por su email
    Optional<Usuario> findByEmail(String email);
}

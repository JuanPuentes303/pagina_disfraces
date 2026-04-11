package com.disfracesrivera.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disfracesrivera.backend.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreo(String correo);
}

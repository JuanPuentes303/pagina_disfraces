package com.disfracesrivera.backend.service;

import com.disfracesrivera.backend.model.Usuario;
import com.disfracesrivera.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrar(Usuario usuario) {

        if (usuario == null) {
            throw new RuntimeException("Usuario inválido");
        }

        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            throw new RuntimeException("Nombre obligatorio");
        }

        if (usuario.getCorreo() == null || usuario.getCorreo().isEmpty()) {
            throw new RuntimeException("Correo obligatorio");
        }

        if (usuario.getContraseña() == null || usuario.getContraseña().isEmpty()) {
            throw new RuntimeException("Contraseña obligatoria");
        }

        usuario.setRol("USER");

        return usuarioRepository.save(usuario);
    }

    public Usuario login(String correo, String contraseña) {

        if (correo == null || contraseña == null) {
            throw new RuntimeException("Datos incompletos");
        }

        return usuarioRepository
                .findByCorreoAndContraseña(correo, contraseña)
                .orElse(null);
    }
}


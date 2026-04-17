package com.disfracesrivera.backend.service;

import com.disfracesrivera.backend.model.Usuario;
import com.disfracesrivera.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(Usuario usuario) {

        usuario.setRol("USER");

        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        return usuarioRepository.save(usuario);
    }

public Usuario login(String correo, String contraseña) {
    Usuario usuario = usuarioRepository.findByCorreo(correo)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    if (!usuario.getContraseña().equals(contraseña)) {
        throw new RuntimeException("Contraseña incorrecta");
    }

    return usuario;
}
}


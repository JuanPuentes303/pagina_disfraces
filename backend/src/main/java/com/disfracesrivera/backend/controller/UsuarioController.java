package com.disfracesrivera.backend.controller;

import com.disfracesrivera.backend.model.Usuario;
import com.disfracesrivera.backend.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public Usuario registrar(@Valid @RequestBody Usuario usuario) {
        return usuarioService.registrarUsuario(usuario);
    }
}
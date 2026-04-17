package com.disfracesrivera.backend.controller;

import com.disfracesrivera.backend.model.Usuario;
import com.disfracesrivera.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public Usuario registrar(@RequestBody Usuario usuario) {
        return usuarioService.registrar(usuario);
    }

    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario usuario) {

        Usuario u = usuarioService.login(
                usuario.getCorreo(),
                usuario.getContraseña()
        );

        if (u == null) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        return u;
    }
}
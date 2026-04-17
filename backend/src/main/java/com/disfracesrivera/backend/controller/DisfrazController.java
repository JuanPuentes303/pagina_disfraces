package com.disfracesrivera.backend.controller;

import com.disfracesrivera.backend.model.Disfraz;
import com.disfracesrivera.backend.service.DisfrazService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disfraces")
@CrossOrigin("*")
public class DisfrazController {

    @Autowired
    private DisfrazService disfrazService;

    @GetMapping("/aleatorios")
    public List<Disfraz> aleatorios() {
        return disfrazService.aleatorios();
    }

    @PostMapping("/guardar")
    public Disfraz guardar(@RequestBody Disfraz d) {
        return disfrazService.guardar(d);
    }

    @GetMapping("/buscar")
    public List<Disfraz> buscar(@RequestParam String texto) {
        return disfrazService.buscar(texto);
    }

    @GetMapping("/listar")
    public List<Disfraz> listar() {
        return disfrazService.listar();
    }
}
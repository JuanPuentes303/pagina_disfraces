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
    public List<Disfraz> obtenerAleatorios() {
        return disfrazService.obtenerAleatorios();
    }


@PostMapping("/guardar")
public Disfraz guardar(@RequestBody Disfraz disfraz) {
    return disfrazService.guardarDisfraz(disfraz);
}
}

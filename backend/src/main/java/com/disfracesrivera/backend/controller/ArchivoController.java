package com.disfracesrivera.backend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

@RestController
@RequestMapping("/archivos")
@CrossOrigin("*")
public class ArchivoController {

    private final String RUTA = "uploads/";

    @PostMapping("/subir")
    public String subir(@RequestParam("file") MultipartFile file) throws Exception {

        String nombre = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        file.transferTo(new File(RUTA + nombre));

        return "http://localhost:8080/uploads/" + nombre;
    }
}

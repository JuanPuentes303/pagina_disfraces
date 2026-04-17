package com.disfracesrivera.backend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/archivos")
@CrossOrigin("*")
public class ArchivoController {

    private final String RUTA = "uploads/";

    @PostMapping("/subir")
    public String subirImagen(@RequestParam("file") MultipartFile file) {

        try {
            String nombreArchivo = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            File destino = new File(RUTA + nombreArchivo);
            file.transferTo(destino);

            return "http://localhost:8080/uploads/" + nombreArchivo;

        } catch (IOException e) {
            throw new RuntimeException("Error al subir imagen");
        }
    }
}

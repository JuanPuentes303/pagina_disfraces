package com.disfracesrivera.backend.service;

import com.disfracesrivera.backend.model.Disfraz;
import com.disfracesrivera.backend.repository.DisfrazRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DisfrazService {

    @Autowired
    private DisfrazRepository repository;

    public Disfraz guardar(Disfraz d) {

        if (d == null) {
            throw new RuntimeException("El disfraz no puede ser null");
        }

        if (d.getNombre() == null || d.getNombre().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio");
        }

        if (d.getPrecio() <= 0) {
            throw new RuntimeException("El precio debe ser mayor a 0");
        }

        if (d.getCantidad() < 0) {
            throw new RuntimeException("Cantidad inválida");
        }

        return repository.save(d);
    }

    public List<Disfraz> listar() {
        return repository.findAll();
    }

    public List<Disfraz> buscar(String texto) {

        if (texto == null || texto.isEmpty()) {
            return listar();
        }

        return repository.findByNombreContainingIgnoreCase(texto);
    }

    public List<Disfraz> aleatorios() {

        List<Disfraz> lista = repository.findAll();

        Collections.shuffle(lista);

        return lista.stream().limit(6).toList();
    }
}
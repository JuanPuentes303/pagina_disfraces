package com.disfracesrivera.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disfraz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String categoria;
    private String talla;
    private double precio;
    private String descripcion;
    private String imagenUrl;
    private int cantidad;
}
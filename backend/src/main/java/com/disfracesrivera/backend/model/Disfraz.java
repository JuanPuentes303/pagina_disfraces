package com.disfracesrivera.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "disfraces")
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

    private Double precio;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String imagenUrl;

    private Integer cantidad;

    private Boolean disponible;
}
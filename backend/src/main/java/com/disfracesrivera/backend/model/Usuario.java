package com.disfracesrivera.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")
    private String nombre;

    @Email
    @Column(unique = true)
    private String correo;

    private String contraseña;
    private String telefono;

    private String rol;
}
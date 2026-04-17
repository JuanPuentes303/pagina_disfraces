package com.disfracesrivera.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer usuarioId;
    private Integer disfrazId;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private String estado;
}
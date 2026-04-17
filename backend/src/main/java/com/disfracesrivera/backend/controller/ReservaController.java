package com.disfracesrivera.backend.controller;

import com.disfracesrivera.backend.model.Reserva;
import com.disfracesrivera.backend.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
@CrossOrigin("*")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping("/crear")
    public Reserva crear(@RequestBody Reserva reserva) {
        return reservaService.reservar(reserva);
    }
}

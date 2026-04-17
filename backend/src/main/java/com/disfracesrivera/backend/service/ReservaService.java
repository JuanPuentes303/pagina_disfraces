package com.disfracesrivera.backend.service;

import com.disfracesrivera.backend.model.Reserva;
import com.disfracesrivera.backend.model.Disfraz;
import com.disfracesrivera.backend.repository.ReservaRepository;
import com.disfracesrivera.backend.repository.DisfrazRepository;
import com.disfracesrivera.backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private DisfrazRepository disfrazRepository;

    @Autowired
    private EmailService emailService;

    public Reserva reservar(Reserva reserva) {

        if (reserva.getFechaInicio().isAfter(reserva.getFechaFin())) {
            throw new RuntimeException("Fecha inválida");
        }

        List<Reserva> reservas = reservaRepository.findByDisfrazId(reserva.getDisfrazId());

        for (Reserva r : reservas) {
            if (r.getFechaFin().isBefore(LocalDate.now())) {
                r.setEstado("FINALIZADA");
                reservaRepository.save(r);
            }
        }

        Disfraz disfraz = disfrazRepository.findById(reserva.getDisfrazId())
                .orElseThrow(() -> new RuntimeException("Disfraz no existe"));

        long activas = reservas.stream()
                .filter(r -> r.getEstado().equals("ACTIVA"))
                .count();

        if (activas >= disfraz.getCantidad()) {
            throw new RuntimeException("No disponible");
        }

        reserva.setEstado("ACTIVA");

        Reserva nueva = reservaRepository.save(reserva);

        emailService.enviarCorreo(
    "leslicita2675@gmail.com",
     "Nueva reserva",
            "Se ha realizado una reserva:\n\n" +
            "Usuario ID: " + reserva.getUsuarioId() + "\n" +
            "Disfraz ID: " + reserva.getDisfrazId() + "\n" +
            "Inicio: " + reserva.getFechaInicio() + "\n" +
            "Fin: " + reserva.getFechaFin()
        );

        return nueva;
    }
}

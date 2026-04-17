package com.disfracesrivera.backend.service;

import com.disfracesrivera.backend.model.Disfraz;
import com.disfracesrivera.backend.model.Reserva;
import com.disfracesrivera.backend.repository.DisfrazRepository;
import com.disfracesrivera.backend.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepo;

    @Autowired
    private DisfrazRepository disfrazRepo;

    @Autowired
    private EmailService emailService;

    public Reserva reservar(Reserva r) {

        if (r.getFechaInicio() == null || r.getFechaFin() == null) {
            throw new RuntimeException("Las fechas son obligatorias");
        }

        if (r.getFechaInicio().isAfter(r.getFechaFin())) {
            throw new RuntimeException("La fecha de inicio no puede ser mayor a la final");
        }

        Integer disfrazId = r.getDisfrazId();

        if (disfrazId == null) {
            throw new RuntimeException("DisfrazId es obligatorio");
        }

        Disfraz d = disfrazRepo.findById(disfrazId)
                .orElseThrow(() -> new RuntimeException("Disfraz no encontrado"));

        List<Reserva> reservas = reservaRepo.findByDisfrazId(disfrazId);

        for (Reserva res : reservas) {
            if (res.getFechaFin().isBefore(LocalDate.now())) {
                res.setEstado("FINALIZADA");
                reservaRepo.save(res);
            }
        }

        long activas = reservas.stream()
                .filter(x -> "ACTIVA".equals(x.getEstado()))
                .count();

        if (activas >= d.getCantidad()) {
            throw new RuntimeException("No hay disponibilidad");
        }

        r.setEstado("ACTIVA");
        Reserva nueva = reservaRepo.save(r);

        emailService.enviarCorreo(
                "TU_CORREO@gmail.com",
                "Nueva reserva",
                "Se ha realizado una reserva:\n\n" +
                        "Disfraz ID: " + r.getDisfrazId() + "\n" +
                        "Inicio: " + r.getFechaInicio() + "\n" +
                        "Fin: " + r.getFechaFin()
        );

        return nueva;
    }
}

package com.femt.citas_reservacion_sistema_backend.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.femt.citas_reservacion_sistema_backend.dto.FechaHoraDTO;

public interface FechaHoraService {
    String obtenerFechaHoraActual() throws Exception;

    List<FechaHoraDTO> obtenerHorariosDisponiblesPorMedico(Long medicoId, LocalDate fecha, LocalTime hora,
            Boolean disponible) throws Exception;
}

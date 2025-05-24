package com.femt.citas_reservacion_sistema_backend.service;

import com.femt.citas_reservacion_sistema_backend.dto.FechaHoraRequestDTO;
import com.femt.citas_reservacion_sistema_backend.entity.FechaHora;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FechaHoraService {

    List<FechaHoraRequestDTO> obtenerTodos();

    List<FechaHoraRequestDTO> obtenerPorMedico(Long medicoId);

    List<FechaHoraRequestDTO> obtenerDisponiblesPorMedico(Long medicoId);

    List<FechaHoraRequestDTO> obtenerPorMedicoYFecha(Long medicoId, LocalDate fecha);

    List<FechaHoraRequestDTO> obtenerDisponiblesDesde(Long medicoId, LocalDate fechaInicio);

    Optional<FechaHoraRequestDTO> obtenerPorMedicoFechaHora(Long medicoId, LocalDate fecha, String hora);

    FechaHoraRequestDTO crearHorario(FechaHoraRequestDTO requestDTO);

    void eliminarHorario(Long id);
}

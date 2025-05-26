package com.femt.citas_reservacion_sistema_backend.service;

import com.femt.citas_reservacion_sistema_backend.dto.request.FechaHoraRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.FechaHoraResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FechaHoraService {
    List<FechaHoraResponseDTO> listarTodos() throws Exception;
    Optional<FechaHoraResponseDTO> obtenerPorId(Long id) throws Exception;
    void registrarFechaHora(FechaHoraRequestDTO dto) throws Exception;
    void eliminarFechaHora(Long id) throws Exception;
    FechaHoraResponseDTO actualizarFechaHora(Long id, FechaHoraRequestDTO dto) throws Exception;

    List<FechaHoraResponseDTO> listarDisponiblesPorMedico(Long medicoId) throws Exception;
    List<FechaHoraResponseDTO> listarDisponiblesPorMedicoYFecha(Long medicoId, LocalDate fecha) throws Exception;
    List<FechaHoraResponseDTO> listarFechasDisponiblesPorMedico(Long idMedico) throws Exception;
}

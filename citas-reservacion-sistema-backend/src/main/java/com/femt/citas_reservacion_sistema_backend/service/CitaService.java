package com.femt.citas_reservacion_sistema_backend.service;

import com.femt.citas_reservacion_sistema_backend.dto.request.CitaRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.CitaResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Cita;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CitaService {
    List<CitaResponseDTO> listarCitas() throws Exception;
    Optional<CitaResponseDTO> obtenerCitaPorId(Long id) throws Exception;
    void registrarCita(CitaRequestDTO citaRequest) throws Exception;
    void eliminarCita(Long id) throws Exception;
    Cita actualizarCita(CitaRequestDTO citaRequest) throws Exception;
}

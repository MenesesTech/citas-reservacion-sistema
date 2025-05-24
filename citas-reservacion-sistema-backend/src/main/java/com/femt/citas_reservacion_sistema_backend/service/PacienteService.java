package com.femt.citas_reservacion_sistema_backend.service;

import com.femt.citas_reservacion_sistema_backend.dto.request.PacienteRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.PacienteResponseDTO;

import java.util.List;
import java.util.Optional;

public interface PacienteService {
    List<PacienteResponseDTO> listarPacientes() throws Exception;
    Optional<PacienteResponseDTO> obtenerPacientePorId(Long id) throws Exception;
    void registrarPaciente(PacienteRequestDTO pacienteRequestDTO) throws Exception;
    void eliminarPaciente(Long id) throws Exception;
    PacienteResponseDTO actualizarPaciente(Long id, PacienteRequestDTO pacienteRequestDTO) throws Exception;
}

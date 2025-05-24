package com.femt.citas_reservacion_sistema_backend.service;

import com.femt.citas_reservacion_sistema_backend.dto.request.EspecialidadRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.EspecialidadResponseDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.MedicoResponseDTO;

import java.util.List;
import java.util.Optional;

public interface EspecialidadService {
    List<EspecialidadResponseDTO> listarTodas();
    Optional<EspecialidadResponseDTO> obtenerPorId(Long id) throws Exception;
    EspecialidadResponseDTO registrarEspecialidad(EspecialidadRequestDTO dto) throws Exception;
    EspecialidadResponseDTO actualizarEspecialidad(Long id, EspecialidadRequestDTO dto) throws Exception;
    void eliminarEspecialidad(Long id) throws Exception;

    // Médicos por especialidad
    List<MedicoResponseDTO> listarMedicosPorEspecialidad(Long especialidadId) throws Exception;
}

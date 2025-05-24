package com.femt.citas_reservacion_sistema_backend.service;

import com.femt.citas_reservacion_sistema_backend.dto.EspecialidadRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.EspecialidadResponseDTO;

import java.util.List;
import java.util.Optional;

public interface EspecialidadService {
    List<EspecialidadResponseDTO> listarEspecialidades() throws Exception;
    Optional<EspecialidadResponseDTO> obtenerEspecialidadPorId(Long idEspecialidad) throws Exception;
    EspecialidadResponseDTO guardarEspecialidad(EspecialidadRequestDTO especialidadRequest) throws Exception;
    void eliminarEspecialidad(Long id) throws Exception;
    EspecialidadResponseDTO actualizarEspecialidad(EspecialidadRequestDTO especialidadRequest) throws Exception;
}
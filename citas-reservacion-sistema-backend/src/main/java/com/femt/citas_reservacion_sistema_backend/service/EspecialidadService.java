package com.femt.citas_reservacion_sistema_backend.service;

import com.femt.citas_reservacion_sistema_backend.dto.EspecialidadDTO;
import com.femt.citas_reservacion_sistema_backend.dto.MedicoEspecialidadDTO;

import java.util.List;
import java.util.Optional;

public interface EspecialidadService {
    List<EspecialidadDTO> listarEspecialidades() throws Exception;
    Optional<EspecialidadDTO> obtenerEspecialidadPorId(Long idEspecialidad) throws Exception;
    EspecialidadDTO guardarEspecialidad(EspecialidadDTO especialidadDTO) throws Exception;
    void eliminarEspecialidad(Long id) throws Exception;
    EspecialidadDTO actualizarEspecialidad(EspecialidadDTO especialidadDTO) throws Exception;
}

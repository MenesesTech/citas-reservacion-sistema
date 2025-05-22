package com.femt.citas_reservacion_sistema_backend.service;

import com.femt.citas_reservacion_sistema_backend.dto.EspecialidadDTO;
import com.femt.citas_reservacion_sistema_backend.dto.MedicoDTO;

import java.util.List;
import java.util.Optional;

public interface EspecialidadService {
    List<EspecialidadDTO> listaEspecialidad() throws Exception;

    Optional<EspecialidadDTO> obtenerEspecialidadPorId(Long id) throws Exception;

    List<EspecialidadDTO> listarEspecialidadPorMedico(MedicoDTO medicoDTO);

    void guardarEspecialidad(EspecialidadDTO especialidadRequest) throws Exception;

    void eliminarEspecialidad(Long id) throws Exception;

    void actualizarEspecialidad(EspecialidadDTO especialidadRequest) throws Exception;
}

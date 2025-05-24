package com.femt.citas_reservacion_sistema_backend.service;

import com.femt.citas_reservacion_sistema_backend.dto.SedeDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Sede;

import java.util.List;
import java.util.Optional;

public interface SedeService {
    List<SedeDTO> listaSedes() throws Exception;

    Optional<SedeDTO> obtenerSedePorId(Long idSede) throws Exception;

    void guardarSede(SedeDTO sedeRequest) throws Exception;

    void eliminarSede(Long id) throws Exception;

    Sede actualizarSede(SedeDTO sedeRequest) throws Exception;
}

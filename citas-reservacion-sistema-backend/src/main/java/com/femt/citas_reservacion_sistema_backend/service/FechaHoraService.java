package com.femt.citas_reservacion_sistema_backend.service;

import java.util.List;
import java.util.Optional;

public interface FechaHoraService {
    List<FechaHoraDTO> listaHorarios() throws Exception;

    Optional<FechaHoraDTO> obtenerHorarioPorId(Long id) throws Exception;

    FechaHoraDTO guardarHorario(FechaHoraDTO horarioRequest) throws Exception;

    void eliminarHorario(Long id) throws Exception;
}

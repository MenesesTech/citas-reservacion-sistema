package com.femt.citas_reservacion_sistema_backend.service;

import com.femt.citas_reservacion_sistema_backend.dto.request.SedeRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.SedeResponseDTO;

import java.util.List;
import java.util.Optional;

public interface SedeService {
    List<SedeResponseDTO> listarSedes();
    Optional<SedeResponseDTO> obtenerSedePorId(Long id);
    void registrarSede(SedeRequestDTO dto);
    void actualizarSede(Long id, SedeRequestDTO dto) throws Exception;
    void eliminarSede(Long id) throws Exception;
}

package com.femt.citas_reservacion_sistema_backend.service;

import com.femt.citas_reservacion_sistema_backend.dto.request.PagoRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.PagoResponseDTO;

import java.util.List;
import java.util.Optional;

public interface PagoService {
    List<PagoResponseDTO> listarPagos() throws Exception;
    Optional<PagoResponseDTO> obtenerPagoPorId(Long id) throws Exception;
    PagoResponseDTO guardarPago(PagoRequestDTO pagoRequest) throws Exception;
    void eliminarPago(Long id) throws Exception;
    PagoResponseDTO actualizarPago(PagoRequestDTO pagoRequest) throws Exception;
    List<PagoResponseDTO> obtenerPagosPorCita(Long citaId) throws Exception;
    Optional<PagoResponseDTO> obtenerPagoPorTransaccion(String idTransaccion) throws Exception;
}

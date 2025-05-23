package com.femt.citas_reservacion_sistema_backend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.femt.citas_reservacion_sistema_backend.dto.CitaDTO;
import com.femt.citas_reservacion_sistema_backend.entity.EstadoDeCita;

public interface CitaService {

    List<CitaDTO> listaCitas() throws Exception;

    Optional<CitaDTO> obtenerCitaPorId(Long id) throws Exception;

    CitaDTO guardarCita(CitaDTO citaRequest) throws Exception;

    void eliminarCita(Long id) throws Exception;

    CitaDTO actualizarCita(CitaDTO citaRequest) throws Exception;

    List<CitaDTO> obtenerCitasPorMedico(Long medicoId) throws Exception;

    List<CitaDTO> obtenerCitasPorUsuario(Long usuarioId) throws Exception;

    List<CitaDTO> obtenerCitasPorSede(Long sedeId) throws Exception;

    List<CitaDTO> obtenerCitasPorEspecialidad(Long especialidadId) throws Exception;

    List<CitaDTO> obtenerCitasPorFecha(LocalDate fecha) throws Exception;

    List<CitaDTO> obtenerCitasPorEstado(EstadoDeCita estado) throws Exception;
}

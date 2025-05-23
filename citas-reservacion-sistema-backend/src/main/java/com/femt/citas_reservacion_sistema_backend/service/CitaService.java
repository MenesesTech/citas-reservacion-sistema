package com.femt.citas_reservacion_sistema_backend.service;

import java.util.List;

import com.femt.citas_reservacion_sistema_backend.dto.CitaDTO;

public interface CitaService {

    List<CitaDTO> listaCitas() throws Exception;

    CitaDTO obtenerCitaPorId(Long id) throws Exception;

    void guardarCita(CitaDTO citaRequest) throws Exception;

    void eliminarCita(Long id) throws Exception;

    void actualizarCita(CitaDTO citaRequest) throws Exception;

    List<CitaDTO> obtenerCitasPorMedico(Long medicoId) throws Exception;

    List<CitaDTO> obtenerCitasPorPaciente(Long pacienteId) throws Exception;

    List<CitaDTO> obtenerCitasPorSede(Long sedeId) throws Exception;

    List<CitaDTO> obtenerCitasPorEspecialidad(Long especialidadId) throws Exception;

    List<CitaDTO> obtenerCitasPorFecha(String fecha) throws Exception;

    List<CitaDTO> obtenerCitasPorEstado(String estado) throws Exception;
}

package com.femt.citas_reservacion_sistema_backend.service;

import com.femt.citas_reservacion_sistema_backend.dto.CitaDTO;

import java.util.List;

public interface CitaService {

    void guardarCita(Long idMedico, Long idPaciente, Long idTipoPaciente, Long idFechaHora) throws Exception;

    void eliminarCita(Long id) throws Exception;

    void actualizarCita(Long id, Long idMedico, Long idPaciente, Long idTipoPaciente, Long idFechaHora)
            throws Exception;

    List<CitaDTO> listaCitas() throws Exception;

    CitaDTO obtenerCitaPorId(Long id) throws Exception;

    List<CitaDTO> obtenerCitasPorMedicoId(Long medicoId) throws Exception;

    List<CitaDTO> obtenerCitasPorPacienteId(Long pacienteId) throws Exception;
}

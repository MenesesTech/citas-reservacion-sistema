package com.femt.citas_reservacion_sistema_backend.service;

import com.femt.citas_reservacion_sistema_backend.dto.TipoPacienteDTO;
import java.util.List;

public interface TipoPacienteService {
    List<TipoPacienteDTO> listaTipoPacientes() throws Exception;

    TipoPacienteDTO obtenerTipoPacientePorId(Long id) throws Exception;

    void guardarTipoPaciente(TipoPacienteDTO tipoPacienteRequest) throws Exception;

    void eliminarTipoPaciente(Long id) throws Exception;

    void actualizarTipoPaciente(TipoPacienteDTO tipoPacienteRequest) throws Exception;

}

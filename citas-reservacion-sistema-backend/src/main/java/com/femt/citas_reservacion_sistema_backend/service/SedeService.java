package com.femt.citas_reservacion_sistema_backend.service;

import java.util.List;
import java.util.Optional;

import com.femt.citas_reservacion_sistema_backend.dto.MedicoDTO;
import com.femt.citas_reservacion_sistema_backend.dto.SedeDTO;

public interface SedeService {
    List<SedeDTO> listaSedes() throws Exception;

    Optional<SedeDTO> obtenerSedePorId(Long id) throws Exception;

    void guardarSede(SedeDTO sedeRequest) throws Exception;

    void eliminarSede(Long id) throws Exception;

    void actualizarSede(SedeDTO sedeRequest) throws Exception;

    List<SedeDTO> listarSedesPorMedico(MedicoDTO medicoDTO) throws Exception;
}

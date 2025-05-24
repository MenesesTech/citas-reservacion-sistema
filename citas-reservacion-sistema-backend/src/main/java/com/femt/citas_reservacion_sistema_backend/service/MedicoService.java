package com.femt.citas_reservacion_sistema_backend.service;

import com.femt.citas_reservacion_sistema_backend.dto.MedicoRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.MedicoResponseDTO;
import com.femt.citas_reservacion_sistema_backend.dto.UsuarioRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.UsuarioResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Medico;

import java.util.List;
import java.util.Optional;

public interface MedicoService {

    List<MedicoResponseDTO> listaMedicos() throws Exception;

    Optional<MedicoResponseDTO> obtenerMedicoPorId(Long id) throws Exception;

    void guardarMedico(MedicoRequestDTO medicoRequest) throws Exception;

    void eliminarMedico(Long id) throws Exception;

    Medico actualizarMedico(MedicoRequestDTO medicoRequest) throws Exception;
}

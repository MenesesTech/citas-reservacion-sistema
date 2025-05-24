package com.femt.citas_reservacion_sistema_backend.service;

import com.femt.citas_reservacion_sistema_backend.dto.request.MedicoRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.MedicoResponseDTO;

import java.util.List;
import java.util.Optional;

public interface MedicoService {
    List<MedicoResponseDTO> listarMedicos() throws Exception;
    Optional<MedicoResponseDTO> obtenerMedicoPorId(Long id) throws Exception;
    void registrarMedico(MedicoRequestDTO medicoRequestDTO) throws Exception;
    void eliminarMedico(Long id) throws Exception;
    MedicoResponseDTO actualizarMedico(Long id, MedicoRequestDTO medicoRequestDTO) throws Exception;
}

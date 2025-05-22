package com.femt.citas_reservacion_sistema_backend.service;

import com.femt.citas_reservacion_sistema_backend.dto.EspecialidadDTO;
import com.femt.citas_reservacion_sistema_backend.dto.MedicoDTO;

import java.util.List;

public interface MedicoService {
    List<MedicoDTO> listaMedico();

    MedicoDTO obtenerMedicoPorId(Long id);

    void guardarMedico(MedicoDTO medicoDTO);

    void actualizarMedico(MedicoDTO medicoDTO);

    void eliminarMedico(Long id);

    List<MedicoDTO> buscarMedicoPorNombre(String nombre);
}

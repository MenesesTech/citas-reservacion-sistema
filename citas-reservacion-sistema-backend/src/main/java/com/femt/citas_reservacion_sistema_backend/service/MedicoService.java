package com.femt.citas_reservacion_sistema_backend.service;

import java.util.List;
import java.util.Optional;

public interface MedicoService {

    List<MedicoDTO> listaMedicos() throws Exception;

    Optional<MedicoDTO> obtenerMedicoPorId(Long id) throws Exception;

    void guardarMedico(MedicoDTO medicoRequest) throws Exception;

    void eliminarMedico(Long id) throws Exception;

    void actualizarMedico(MedicoDTO medicoRequest) throws Exception;
}

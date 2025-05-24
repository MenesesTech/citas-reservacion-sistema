package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.dto.MedicoRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.MedicoResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Medico;
import com.femt.citas_reservacion_sistema_backend.mapper.MedicoMapper;
import com.femt.citas_reservacion_sistema_backend.repository.MedicoRepository;
import com.femt.citas_reservacion_sistema_backend.service.MedicoService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicoServiceImp implements MedicoService {

    private final MedicoRepository medicoRepository;
    private final MedicoMapper medicoMapper;

    public MedicoServiceImp(MedicoRepository medicoRepository, MedicoMapper medicoMapper) {
        this.medicoRepository = medicoRepository;
        this.medicoMapper = medicoMapper;
    }

    @Override
    public List<MedicoResponseDTO> listaMedicos() throws Exception {
        return this.medicoRepository.findAll().stream()
                .map(medicoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MedicoResponseDTO> obtenerMedicoPorId(Long id) throws Exception {
        return this.medicoRepository.findById(id)
                .map(medicoMapper::toDto);
    }

    @Override
    public void guardarMedico(MedicoRequestDTO medicoRequest) throws Exception {
        Medico medico = medicoMapper.toEntity(medicoRequest);
        this.medicoRepository.save(medico);
    }

    @Override
    public void eliminarMedico(Long id) throws Exception {
        if (!medicoRepository.existsById(id)) {
            throw new Exception("Médico no encontrado con ID: " + id);
        }
        this.medicoRepository.deleteById(id);
    }

    @Override
    public MedicoResponseDTO actualizarMedico(MedicoRequestDTO medicoRequest) throws Exception {
        if (medicoRequest == null) {
            throw new Exception("Datos del médico son nulos");
        }
        // Aquí debes obtener el ID del medicoRequest, pero tu DTO no tiene id
        // Debes agregar Long id a MedicoRequestDTO para actualizar correctamente
        if (medicoRequest.getId() == null || !medicoRepository.existsById(medicoRequest.getId())) {
            throw new Exception("Médico no encontrado para actualizar");
        }

        Medico medico = medicoMapper.toEntity(medicoRequest);
        Medico medicoActualizado = medicoRepository.save(medico);
        return medicoMapper.toDto(medicoActualizado);
    }
}

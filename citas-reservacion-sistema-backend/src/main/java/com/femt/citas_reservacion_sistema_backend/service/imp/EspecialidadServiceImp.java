package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.dto.request.EspecialidadRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.EspecialidadResponseDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.MedicoResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Especialidad;
import com.femt.citas_reservacion_sistema_backend.entity.Medico;
import com.femt.citas_reservacion_sistema_backend.mapper.EspecialidadMapper;
import com.femt.citas_reservacion_sistema_backend.mapper.MedicoMapper;
import com.femt.citas_reservacion_sistema_backend.repository.EspecialidadRepository;
import com.femt.citas_reservacion_sistema_backend.repository.MedicoRepository;
import com.femt.citas_reservacion_sistema_backend.service.EspecialidadService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EspecialidadServiceImp implements EspecialidadService {

    private final EspecialidadRepository especialidadRepository;
    private final MedicoRepository medicoRepository;
    private final EspecialidadMapper especialidadMapper;
    private final MedicoMapper medicoMapper;

    public EspecialidadServiceImp(EspecialidadRepository especialidadRepository,
                                  MedicoRepository medicoRepository,
                                  EspecialidadMapper especialidadMapper,
                                  MedicoMapper medicoMapper) {
        this.especialidadRepository = especialidadRepository;
        this.medicoRepository = medicoRepository;
        this.especialidadMapper = especialidadMapper;
        this.medicoMapper = medicoMapper;
    }

    @Override
    public List<EspecialidadResponseDTO> listarTodas() {
        List<Especialidad> especialidades = especialidadRepository.findAll();
        return especialidades.stream()
                .map(especialidadMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EspecialidadResponseDTO> obtenerPorId(Long id) throws Exception {
        Especialidad e = especialidadRepository.findById(id)
                .orElseThrow(() -> new Exception("Especialidad no encontrada con ID: " + id));
        return Optional.of(especialidadMapper.toResponseDTO(e));
    }

    @Override
    public EspecialidadResponseDTO registrarEspecialidad(EspecialidadRequestDTO dto) throws Exception {
        Especialidad e = especialidadMapper.toEntity(dto);
        e = especialidadRepository.save(e);
        return especialidadMapper.toResponseDTO(e);
    }

    @Override
    public EspecialidadResponseDTO actualizarEspecialidad(Long id, EspecialidadRequestDTO dto) throws Exception {
        Especialidad eExistente = especialidadRepository.findById(id)
                .orElseThrow(() -> new Exception("Especialidad no encontrada con ID: " + id));

        eExistente.setNombre(dto.getNombre());
        eExistente.setMonto(dto.getMonto());

        eExistente = especialidadRepository.save(eExistente);
        return especialidadMapper.toResponseDTO(eExistente);
    }

    @Override
    public void eliminarEspecialidad(Long id) throws Exception {
        if (!especialidadRepository.existsById(id)) {
            throw new Exception("Especialidad no encontrada con ID: " + id);
        }
        especialidadRepository.deleteById(id);
    }

    @Override
    public List<MedicoResponseDTO> listarMedicosPorEspecialidad(Long especialidadId) throws Exception {
        if (!especialidadRepository.existsById(especialidadId)) {
            throw new Exception("Especialidad no encontrada con ID: " + especialidadId);
        }

        List<Medico> medicos = medicoRepository.findByEspecialidadId(especialidadId);
        return medicos.stream()
                .map(medicoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}

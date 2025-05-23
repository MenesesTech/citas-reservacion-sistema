package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.dto.MedicoDTO;
import com.femt.citas_reservacion_sistema_backend.mapper.MedicoMapper;
import com.femt.citas_reservacion_sistema_backend.repository.MedicoEspecialidadRepository;
import com.femt.citas_reservacion_sistema_backend.repository.MedicoRepository;
import com.femt.citas_reservacion_sistema_backend.service.MedicoService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class MedicoServiceImp implements MedicoService {
    private final MedicoRepository medicoRepository;
    private final MedicoEspecialidadRepository medicoEspecialidadRepository;
    private final MedicoMapper medicoMapper;

    public MedicoServiceImp(MedicoRepository medicoRepository,
            MedicoEspecialidadRepository medicoEspecialidadRepository, MedicoMapper medicoMapper) {
        this.medicoRepository = medicoRepository;
        this.medicoEspecialidadRepository = medicoEspecialidadRepository;
        this.medicoMapper = medicoMapper;
    }

    @Override
    public List<MedicoDTO> listaMedicos() throws Exception {
        return this.medicoRepository.findAll().stream()
                .map(medicoMapper::toDTO).toList();
    }

    @Override
    public Optional<MedicoDTO> obtenerMedicoPorId(Long id) throws Exception {
        return this.medicoRepository.findById(id)
                .map(medicoMapper::toDTO);
    }

    @Override
    public void guardarMedico(MedicoDTO medicoRequest) throws Exception {
        this.medicoRepository.save(medicoMapper.toEntity(medicoRequest));
    }

    @Override
    public void eliminarMedico(Long id) throws Exception {
        this.medicoRepository.deleteById(id);
    }

    @Override
    public void actualizarMedico(MedicoDTO medicoRequest) throws Exception {
        this.medicoRepository.save(medicoMapper.toEntity(medicoRequest));
    }

    @Override
    public List<String> obtenerEspecialidadesMedico(Long medicoId) {
        return this.medicoEspecialidadRepository.findEspecialidadesPorMedico(medicoId);
    }

    @Override
    public Optional<MedicoDTO> obtenerMedicoConEspecialidades(Long medicoId) throws Exception {
        var optionalMedico = medicoRepository.findById(medicoId);
        if (optionalMedico.isEmpty())
            return Optional.empty();

        var medico = optionalMedico.get();
        var especialidades = medicoEspecialidadRepository.findEspecialidadesPorMedico(medicoId);

        MedicoDTO dto = medicoMapper.toDTO(medico);
        dto.setEspecialidades(especialidades);

        return Optional.of(dto);
    }
}

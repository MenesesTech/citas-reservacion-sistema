package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.dto.MedicoRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.MedicoResponseDTO;
import com.femt.citas_reservacion_sistema_backend.dto.UsuarioRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.UsuarioResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Medico;
import com.femt.citas_reservacion_sistema_backend.mapper.MedicoMapper;
import com.femt.citas_reservacion_sistema_backend.repository.EspecialidadRepository;
import com.femt.citas_reservacion_sistema_backend.repository.MedicoRepository;
import com.femt.citas_reservacion_sistema_backend.repository.SedeRepository;
import com.femt.citas_reservacion_sistema_backend.service.MedicoService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicoServiceImp implements MedicoService {

    private final MedicoRepository medicoRepository;
    private final MedicoMapper medicoMapper;
    private final EspecialidadRepository especialidadRepository;
    private final SedeRepository sedeRepository;

    public MedicoServiceImp(MedicoRepository medicoRepository, MedicoMapper medicoMapper, EspecialidadRepository especialidadRepository, SedeRepository sedeRepository) {
        this.medicoRepository = medicoRepository;
        this.medicoMapper = medicoMapper;
        this.especialidadRepository = especialidadRepository;
        this.sedeRepository = sedeRepository;
    }

    @Override
    public List<MedicoResponseDTO> listaMedicos() throws Exception {
        return medicoMapper.toDoList(medicoRepository.findAll());
    }

    @Override
    public Optional<MedicoResponseDTO> obtenerMedicoPorId(Long id) throws Exception {
        Optional<Medico> medico = medicoRepository.findById(id);
        return medico.map(medicoMapper::toDto);
    }

    @Override
    public void guardarMedico(MedicoRequestDTO medicoRequest) throws Exception {
        Medico medico = medicoMapper.toEntity(medicoRequest);

        medico.setEspecialidad(
                especialidadRepository.findById(medicoRequest.getEspecialidad_id())
                        .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"))
        );

        medico.setSede(
                sedeRepository.findById(medicoRequest.getSede_id())
                        .orElseThrow(() -> new RuntimeException("Sede no encontrada"))
        );

        medicoRepository.save(medico);
    }


    @Override
    public void eliminarMedico(Long id) throws Exception {
        if (!medicoRepository.existsById(id)) {
            throw new Exception("Médico no encontrado con ID: " + id);
        }
        medicoRepository.deleteById(id);
    }

    @Override
    public Medico actualizarMedico(MedicoRequestDTO medicoRequest) throws Exception {
        Medico medico = medicoRepository.findById(medicoRequest.getId())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        medico.setNombre(medicoRequest.getNombre());
        medico.setApellido(medicoRequest.getApellido());
        medico.setCorreo(medicoRequest.getCorreo());
        medico.setEspecialidad(
                especialidadRepository.findById(medicoRequest.getEspecialidad_id())
                        .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"))
        );
        medico.setSede(
                sedeRepository.findById(medicoRequest.getSede_id())
                        .orElseThrow(() -> new RuntimeException("Sede no encontrada"))
        );
        medico.setCorreo(medicoRequest.getCorreo());
        medico.setCelular(medicoRequest.getCelular());

        return medicoRepository.save(medico);
    }
}

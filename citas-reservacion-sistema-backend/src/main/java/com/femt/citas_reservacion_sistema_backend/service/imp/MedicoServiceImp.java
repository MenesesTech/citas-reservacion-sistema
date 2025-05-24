package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.dto.request.MedicoRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.MedicoResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Especialidad;
import com.femt.citas_reservacion_sistema_backend.entity.Medico;
import com.femt.citas_reservacion_sistema_backend.entity.Sede;
import com.femt.citas_reservacion_sistema_backend.entity.Usuario;
import com.femt.citas_reservacion_sistema_backend.mapper.MedicoMapper;
import com.femt.citas_reservacion_sistema_backend.repository.EspecialidadRepository;
import com.femt.citas_reservacion_sistema_backend.repository.MedicoRepository;
import com.femt.citas_reservacion_sistema_backend.repository.SedeRepository;
import com.femt.citas_reservacion_sistema_backend.repository.UsuarioRepository;
import com.femt.citas_reservacion_sistema_backend.service.MedicoService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicoServiceImp implements MedicoService {

    private final MedicoRepository medicoRepository;
    private final MedicoMapper medicoMapper;
    private final UsuarioRepository usuarioRepository;
    private final EspecialidadRepository especialidadRepository;
    private final SedeRepository sedeRepository;

    public MedicoServiceImp(MedicoRepository medicoRepository,
                            MedicoMapper medicoMapper,
                            UsuarioRepository usuarioRepository,
                            EspecialidadRepository especialidadRepository,
                            SedeRepository sedeRepository) {
        this.medicoRepository = medicoRepository;
        this.medicoMapper = medicoMapper;
        this.usuarioRepository = usuarioRepository;
        this.especialidadRepository = especialidadRepository;
        this.sedeRepository = sedeRepository;
    }

    @Override
    public List<MedicoResponseDTO> listarMedicos() {
        List<Medico> medicos = medicoRepository.findAll();
        return medicos.stream()
                .map(medicoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MedicoResponseDTO> obtenerMedicoPorId(Long id) throws Exception {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new Exception("Médico no encontrado con ID: " + id));
        return Optional.of(medicoMapper.toResponseDTO(medico));
    }

    @Override
    public void registrarMedico(MedicoRequestDTO dto) throws Exception {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new Exception("Usuario no encontrado con ID: " + dto.getUsuarioId()));
        Especialidad especialidad = especialidadRepository.findById(dto.getEspecialidadId())
                .orElseThrow(() -> new Exception("Especialidad no encontrada con ID: " + dto.getEspecialidadId()));
        Sede sede = sedeRepository.findById(dto.getSedeId())
                .orElseThrow(() -> new Exception("Sede no encontrada con ID: " + dto.getSedeId()));

        Medico medico = new Medico();
        medico.setUsuario(usuario);
        medico.setEspecialidad(especialidad);
        medico.setSede(sede);

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
    public MedicoResponseDTO actualizarMedico(Long id, MedicoRequestDTO dto) throws Exception {
        Medico medicoExistente = medicoRepository.findById(id)
                .orElseThrow(() -> new Exception("Médico no encontrado con ID: " + id));

        Especialidad especialidad = especialidadRepository.findById(dto.getEspecialidadId())
                .orElseThrow(() -> new Exception("Especialidad no encontrada con ID: " + dto.getEspecialidadId()));

        Sede sede = sedeRepository.findById(dto.getSedeId())
                .orElseThrow(() -> new Exception("Sede no encontrada con ID: " + dto.getSedeId()));

        medicoExistente.setEspecialidad(especialidad);
        medicoExistente.setSede(sede);

        medicoRepository.save(medicoExistente);
        return medicoMapper.toResponseDTO(medicoExistente);
    }
}

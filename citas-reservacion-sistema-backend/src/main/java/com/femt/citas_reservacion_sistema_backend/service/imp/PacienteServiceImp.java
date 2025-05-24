package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.dto.request.PacienteRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.PacienteResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Paciente;
import com.femt.citas_reservacion_sistema_backend.entity.Usuario;
import com.femt.citas_reservacion_sistema_backend.mapper.PacienteMapper;
import com.femt.citas_reservacion_sistema_backend.repository.PacienteRepository;
import com.femt.citas_reservacion_sistema_backend.repository.UsuarioRepository;
import com.femt.citas_reservacion_sistema_backend.service.PacienteService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteServiceImp implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;
    private final UsuarioRepository usuarioRepository;

    public PacienteServiceImp(PacienteRepository pacienteRepository,
                              PacienteMapper pacienteMapper,
                              UsuarioRepository usuarioRepository) {
        this.pacienteRepository = pacienteRepository;
        this.pacienteMapper = pacienteMapper;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<PacienteResponseDTO> listarPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream()
                .map(pacienteMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PacienteResponseDTO> obtenerPacientePorId(Long id) throws Exception {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new Exception("Paciente no encontrado con ID: " + id));
        return Optional.of(pacienteMapper.toResponseDTO(paciente));
    }

    @Override
    public void registrarPaciente(PacienteRequestDTO dto) throws Exception {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new Exception("Usuario no encontrado con ID: " + dto.getUsuarioId()));

        Paciente paciente = new Paciente();
        paciente.setUsuario(usuario);

        pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Long id) throws Exception {
        if (!pacienteRepository.existsById(id)) {
            throw new Exception("Paciente no encontrado con ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    @Override
    public PacienteResponseDTO actualizarPaciente(Long id, PacienteRequestDTO dto) throws Exception {
        Paciente pacienteExistente = pacienteRepository.findById(id)
                .orElseThrow(() -> new Exception("Paciente no encontrado con ID: " + id));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new Exception("Usuario no encontrado con ID: " + dto.getUsuarioId()));

        pacienteExistente.setUsuario(usuario);

        pacienteRepository.save(pacienteExistente);
        return pacienteMapper.toResponseDTO(pacienteExistente);
    }
}

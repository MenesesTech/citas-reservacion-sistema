package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.dto.request.LoginRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.UsuarioResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Paciente;
import com.femt.citas_reservacion_sistema_backend.entity.Usuario;
import com.femt.citas_reservacion_sistema_backend.mapper.UsuarioMapper;
import com.femt.citas_reservacion_sistema_backend.repository.MedicoRepository;
import com.femt.citas_reservacion_sistema_backend.repository.PacienteRepository;
import com.femt.citas_reservacion_sistema_backend.repository.UsuarioRepository;
import com.femt.citas_reservacion_sistema_backend.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final UsuarioMapper usuarioMapper;

    public AuthServiceImp(UsuarioRepository usuarioRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public UsuarioResponseDTO login(LoginRequestDTO loginRequest) throws Exception {
        Usuario usuario = usuarioRepository.findAll().stream()
                .filter(u -> u.getDni().equals(loginRequest.getDni()) &&
                        u.getPassword().equals(loginRequest.getPassword()))
                .findFirst()
                .orElseThrow(() -> new Exception("Credenciales inválidas"));

        UsuarioResponseDTO responseDTO = usuarioMapper.toResponseDTO(usuario);

        // Aquí puedes asignar un valor al campo tipoUsuario
        if (pacienteRepository.findByUsuarioId(usuario.getId()).isPresent()) {
            responseDTO.setTipoUsuario("Paciente");
        } else if (medicoRepository.findByUsuarioId(usuario.getId()).isPresent()) {
            responseDTO.setTipoUsuario("Medico");
        }

        return responseDTO;
    }

}

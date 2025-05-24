package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.dto.request.LoginRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.UsuarioResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Usuario;
import com.femt.citas_reservacion_sistema_backend.mapper.UsuarioMapper;
import com.femt.citas_reservacion_sistema_backend.repository.UsuarioRepository;
import com.femt.citas_reservacion_sistema_backend.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public AuthServiceImp(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }
    @Override
    public UsuarioResponseDTO login(LoginRequestDTO loginRequest) throws Exception {
        Usuario usuario = usuarioRepository.findAll().stream()
                .filter(u ->
                        u.getDni().equals(loginRequest.getDni()) &&
                        u.getPassword().equals(loginRequest.getPassword()))
                .findFirst()
                .orElseThrow(() -> new Exception("Credenciales inválidas"));

        return usuarioMapper.toResponseDTO(usuario);
    }
}

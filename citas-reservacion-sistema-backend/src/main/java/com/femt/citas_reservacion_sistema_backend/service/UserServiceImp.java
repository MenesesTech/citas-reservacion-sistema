package com.femt.citas_reservacion_sistema_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.femt.citas_reservacion_sistema_backend.dto.UsuarioDTO;
import com.femt.citas_reservacion_sistema_backend.mapper.UsuarioMapper;
import com.femt.citas_reservacion_sistema_backend.repository.UsuarioRepository;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private UsuarioMapper usuarioMapper;

    @Override
    public List<UsuarioDTO> listaUsuarios() {
        return this.usuarioRepository.findAll().stream().map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorId(Long id) {
        return this.usuarioRepository.findById(id).map(usuarioMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorEmail(String email) {
        return this.usuarioRepository.findByEmail(email).map(usuarioMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorDni(String dni) {
        return this.usuarioRepository.findByDni(dni).map(usuarioMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public void guardarUsuario(UsuarioDTO usuarioDTO) {
        this.usuarioRepository.save(usuarioMapper.toEntity(usuarioDTO));
    }

    @Override
    public void eliminarUsuario(Long id) {
        this.usuarioRepository.deleteById(id);
    }

    @Override
    public void actualizarUsuario(UsuarioDTO usuarioDTO) {
        this.usuarioRepository.save(usuarioMapper.toEntity(usuarioDTO));
    }
}

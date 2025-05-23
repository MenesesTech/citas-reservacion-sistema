package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.dto.UsuarioDTO;
import com.femt.citas_reservacion_sistema_backend.mapper.UsuarioMapper;
import com.femt.citas_reservacion_sistema_backend.repository.UsuarioRepository;
import com.femt.citas_reservacion_sistema_backend.service.UsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImp implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImp(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public List<UsuarioDTO> listaUsuarios() throws Exception {
        return this.usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<UsuarioDTO> obtenerUsuarioPorId(Long idUser) throws Exception {
        return this.usuarioRepository.findById(idUser)
                .map(usuarioMapper::toDTO);
    }

    @Override
    public UsuarioDTO guardarUsuario(UsuarioDTO usuarioRequest) throws Exception {
        return usuarioMapper.toDTO(
                usuarioRepository.save(usuarioMapper.toEntity(usuarioRequest))
        );
    }

    @Override
    public void eliminarUsuario(Long id) throws Exception {
        this.usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioRequest) throws Exception {
        return usuarioMapper.toDTO(
                usuarioRepository.save(usuarioMapper.toEntity(usuarioRequest))
        );
    }
}

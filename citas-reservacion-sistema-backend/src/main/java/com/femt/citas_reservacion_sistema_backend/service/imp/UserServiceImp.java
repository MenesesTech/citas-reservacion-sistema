package com.femt.citas_reservacion_sistema_backend.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.femt.citas_reservacion_sistema_backend.service.UserService;
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
    public List<UsuarioDTO> listaUsuarios() throws Exception {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<UsuarioDTO> obtenerUsuarioPorId(Long id) throws Exception {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDTO);
    }

    @Override
    public void guardarUsuario(UsuarioDTO usuarioRequest) throws Exception {
        try {
            this.usuarioRepository.save(usuarioMapper.toEntity(usuarioRequest));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void eliminarUsuario(Long id) throws Exception {
        this.usuarioRepository.deleteById(id);
    }

    @Override
    public void actualizarUsuario(UsuarioDTO usuarioRequest) throws Exception {
        try {
            this.usuarioRepository.save(usuarioMapper.toEntity(usuarioRequest));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

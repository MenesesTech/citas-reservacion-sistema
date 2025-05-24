package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.dto.UsuarioRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.UsuarioResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Usuario;
import com.femt.citas_reservacion_sistema_backend.mapper.UsuarioMapper;
import com.femt.citas_reservacion_sistema_backend.repository.UsuarioRepository;
import com.femt.citas_reservacion_sistema_backend.service.UsuarioService;

import java.util.List;
import java.util.Optional;

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
    public List<UsuarioResponseDTO> listaUsuarios() throws Exception {
        return usuarioMapper.toDoList(usuarioRepository.findAll());
    }

    @Override
    public Optional<UsuarioResponseDTO> obtenerUsuarioPorId(Long idUser) throws Exception {
        Optional<Usuario> usuario = usuarioRepository.findById(idUser);
        return usuario.map(usuarioMapper::toDto);
    }

    @Override
    public void guardarUsuario(UsuarioRequestDTO usuarioRequest) throws Exception {
        this.usuarioRepository.save(usuarioMapper.toEntity(usuarioRequest));
    }

    @Override
    public void eliminarUsuario(Long id) throws Exception {
        this.usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario actualizarUsuario(UsuarioRequestDTO usuarioRequest) throws Exception {
        Usuario usuario = usuarioRepository.findById(usuarioRequest.getId()).orElseThrow(() -> new RuntimeException("Product not found"));
        usuario.setDni(usuarioRequest.getDni());
        usuario.setPassword(usuarioRequest.getPassword());
        usuario.setNombres(usuarioRequest.getNombres());
        usuario.setApellidoPaterno(usuarioRequest.getApellidoPaterno());
        usuario.setApellidoMaterno(usuarioRequest.getApellidoMaterno());
        usuario.setFechaNacimiento(usuarioRequest.getFechaNacimiento());
        usuario.setCelular(usuarioRequest.getCelular());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setGenero(usuarioRequest.getGenero());
        usuario.setDepartamento(usuarioRequest.getDepartamento());
        usuario.setProvincia(usuarioRequest.getProvincia());
        usuario.setDistrito(usuarioRequest.getDistrito());
        usuario.setDireccion(usuarioRequest.getDireccion());
        return usuarioRepository.save(usuario);
    }
}

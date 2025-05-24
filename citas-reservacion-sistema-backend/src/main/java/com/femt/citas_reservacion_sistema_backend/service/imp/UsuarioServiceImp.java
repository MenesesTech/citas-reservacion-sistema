package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.dto.request.UsuarioRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.UsuarioResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Usuario;
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
    public List<UsuarioResponseDTO> listarUsuarios() throws Exception {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UsuarioResponseDTO> obtenerUsuarioPorId(Long id) throws Exception {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuario no encontrado con ID: " + id));
        return Optional.of(usuarioMapper.toResponseDTO(usuario));
    }

    @Override
    public void registrarUsuario(UsuarioRequestDTO usuarioRequestDTO) throws Exception {
        Usuario usuario = usuarioMapper.toEntity(usuarioRequestDTO);
        usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) throws Exception {
        if (!usuarioRepository.existsById(id)) {
            throw new Exception("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO usuarioRequestDTO) throws Exception {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuario no encontrado con ID: " + id));

        // Actualizamos los campos permitidos
        usuarioExistente.setDni(usuarioRequestDTO.getDni());
        usuarioExistente.setPassword(usuarioRequestDTO.getPassword());
        usuarioExistente.setNombre(usuarioRequestDTO.getNombre());
        usuarioExistente.setApellidoPaterno(usuarioRequestDTO.getApellidoPaterno());
        usuarioExistente.setApellidoMaterno(usuarioRequestDTO.getApellidoMaterno());
        usuarioExistente.setFechaNacimiento(usuarioRequestDTO.getFechaNacimiento());
        usuarioExistente.setTelefono(usuarioRequestDTO.getTelefono());
        usuarioExistente.setEmail(usuarioRequestDTO.getEmail());
        usuarioExistente.setGenero(usuarioRequestDTO.getGenero());
        usuarioExistente.setDepartamento(usuarioRequestDTO.getDepartamento());
        usuarioExistente.setProvincia(usuarioRequestDTO.getProvincia());
        usuarioExistente.setDistrito(usuarioRequestDTO.getDistrito());
        usuarioExistente.setDireccion(usuarioRequestDTO.getDireccion());

        usuarioRepository.save(usuarioExistente);
        return usuarioMapper.toResponseDTO(usuarioExistente);
    }
}

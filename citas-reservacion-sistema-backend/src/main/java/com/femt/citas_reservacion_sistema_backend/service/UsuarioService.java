package com.femt.citas_reservacion_sistema_backend.service;

import com.femt.citas_reservacion_sistema_backend.dto.request.UsuarioRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.UsuarioResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<UsuarioResponseDTO> listarUsuarios() throws Exception;
    Optional<UsuarioResponseDTO> obtenerUsuarioPorId(Long id) throws Exception;
    void registrarUsuario(UsuarioRequestDTO usuarioRequestDTO) throws Exception;
    void eliminarUsuario(Long id) throws Exception;
    UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO usuarioRequestDTO) throws Exception;
}

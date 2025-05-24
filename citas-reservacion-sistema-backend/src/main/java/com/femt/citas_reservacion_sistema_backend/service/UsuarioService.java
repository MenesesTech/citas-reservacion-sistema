package com.femt.citas_reservacion_sistema_backend.service;

import com.femt.citas_reservacion_sistema_backend.dto.UsuarioRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.UsuarioResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<UsuarioResponseDTO> listaUsuarios() throws Exception;
    Optional<UsuarioResponseDTO> obtenerUsuarioPorId(Long idUser) throws Exception;
    void guardarUsuario(UsuarioRequestDTO usuarioRequest) throws Exception;
    void eliminarUsuario(Long id) throws Exception;
    Usuario actualizarUsuario(UsuarioRequestDTO usuarioRequest) throws Exception;
}

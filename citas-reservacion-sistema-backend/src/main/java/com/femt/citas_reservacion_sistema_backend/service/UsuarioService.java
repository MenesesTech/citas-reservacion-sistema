package com.femt.citas_reservacion_sistema_backend.service;

import com.femt.citas_reservacion_sistema_backend.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<UsuarioDTO> listaUsuarios() throws Exception;
    Optional<UsuarioDTO> obtenerUsuarioPorId(Long id) throws Exception;
    UsuarioDTO guardarUsuario(UsuarioDTO usuarioRequest) throws Exception;
    void eliminarUsuario(Long id) throws Exception;
    UsuarioDTO actualizarUsuario(UsuarioDTO usuarioRequest) throws Exception;
}

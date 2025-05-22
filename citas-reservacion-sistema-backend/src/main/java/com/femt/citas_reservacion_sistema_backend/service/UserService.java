package com.femt.citas_reservacion_sistema_backend.service;

import java.util.List;
import java.util.Optional;

import com.femt.citas_reservacion_sistema_backend.dto.UsuarioDTO;

public interface UserService {

    List<UsuarioDTO> listaUsuarios() throws Exception;
    Optional<UsuarioDTO> obtenerUsuarioPorId(Long id) throws Exception;
    void guardarUsuario(UsuarioDTO usuarioRequest) throws Exception;
    void eliminarUsuario(Long id) throws Exception;
    void actualizarUsuario(UsuarioDTO usuarioRequest) throws Exception;
}

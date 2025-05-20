package com.femt.citas_reservacion_sistema_backend.service;

import java.util.List;

import com.femt.citas_reservacion_sistema_backend.dto.UsuarioDTO;

public interface UserService {

    List<UsuarioDTO> listaUsuarios();

    UsuarioDTO obtenerUsuarioPorId(Long id);

    UsuarioDTO obtenerUsuarioPorEmail(String email);

    UsuarioDTO obtenerUsuarioPorDni(String dni);

    void guardarUsuario(UsuarioDTO usuarioDTO);

    void eliminarUsuario(Long id);

    void actualizarUsuario(UsuarioDTO usuarioDTO);
}

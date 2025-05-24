package com.femt.citas_reservacion_sistema_backend.service;

import com.femt.citas_reservacion_sistema_backend.dto.request.LoginRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.UsuarioResponseDTO;

public interface AuthService {
    UsuarioResponseDTO login(LoginRequestDTO loginRequest) throws Exception;
}

package com.femt.citas_reservacion_sistema_backend.controller;

import com.femt.citas_reservacion_sistema_backend.dto.request.LoginRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.UsuarioResponseDTO;
import com.femt.citas_reservacion_sistema_backend.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Autenticación", description = "Endpoints relacionados con la autenticación de usuarios")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuario con DNI y contraseña")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticación exitosa"),
            @ApiResponse(responseCode = "400", description = "Solicitud mal formada o datos faltantes"),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas o error de autenticación")
    })
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            if (loginRequest.getDni() == null || loginRequest.getDni().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("El nombre de usuario es obligatorio");
            }
            if (loginRequest.getPassword() == null || loginRequest.getPassword().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("La contraseña es obligatoria");
            }

            UsuarioResponseDTO usuario = authService.login(loginRequest);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Error de autenticación: " + e.getMessage());
        }
    }
}

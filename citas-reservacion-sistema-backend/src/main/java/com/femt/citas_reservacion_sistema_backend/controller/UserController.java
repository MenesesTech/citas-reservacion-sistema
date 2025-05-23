package com.femt.citas_reservacion_sistema_backend.controller;

import com.femt.citas_reservacion_sistema_backend.dto.UsuarioDTO;
import com.femt.citas_reservacion_sistema_backend.service.imp.UsuarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UsuarioServiceImp userService;

    @GetMapping("/{idUser}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long idUser) {
        try {
            return userService.obtenerUsuarioPorId(idUser)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/registrar-usuario")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioDTO userDTO) {
        if (userDTO == null) {
            return ResponseEntity.badRequest().body("Error: la solicitud está vacía");
        }
        try {
            return ResponseEntity.ok(userService.guardarUsuario(userDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar usuario: " + e.getMessage());
        }
    }
}

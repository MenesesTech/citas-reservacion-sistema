package com.femt.citas_reservacion_sistema_backend.controller;

import com.femt.citas_reservacion_sistema_backend.dto.UsuarioRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.UsuarioResponseDTO;
import com.femt.citas_reservacion_sistema_backend.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UsuarioService userService;

    public UserController(UsuarioService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Listar todos los usuarios")
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() throws Exception{
        return ResponseEntity.ok(userService.listaUsuarios());
    }

    @Operation(summary = "Buscar usuario por Id")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuarioPorId(@PathVariable Long id) throws Exception{
        return userService.obtenerUsuarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un usuario")
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioRequestDTO userRequestDTO) {
        if (userRequestDTO == null) {
            return ResponseEntity.badRequest().body("Datos de usuario vacios");
        }else{
            try {
                userService.guardarUsuario(userRequestDTO);
                return ResponseEntity.ok("El usuario se registró correctamente");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error al registrar usuario: " + e.getMessage());
            }
        }

    }

    @Operation(summary = "Eliminar un usuario")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) throws Exception{
        try{
            userService.eliminarUsuario(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar el usuario: "+e.getMessage());
        }
    }

    @Operation(summary = "Actualizar un usuario")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuarioRequestDTO){
        if (usuarioRequestDTO == null){
            return ResponseEntity.badRequest().body("Datos de usuario vacios");
        }else{
            try{
                usuarioRequestDTO.setId(id);
                userService.actualizarUsuario(usuarioRequestDTO);
                return ResponseEntity.ok("Usuario actualizado correctamente");
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al obtener el usuario: "+e.getMessage());
            }
        }
    }
}

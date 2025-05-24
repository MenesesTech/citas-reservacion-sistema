package com.femt.citas_reservacion_sistema_backend.controller;

import com.femt.citas_reservacion_sistema_backend.dto.UsuarioDTO;
import com.femt.citas_reservacion_sistema_backend.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UsuarioService userService;

    @Operation(summary = "Listar todos los usuarios")
    @GetMapping("/all-usuarios")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios(){
        try{
            List<UsuarioDTO> usuarios = userService.listaUsuarios();
            if(usuarios.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Buscar usuario por Id")
    @GetMapping("/find/{idUser}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long idUser) {
        try {
            return userService.obtenerUsuarioPorId(idUser)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @Operation(summary = "Crear un usuario")
    @PostMapping("/create")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioDTO userDTO) {
        if (userDTO == null) {
            return ResponseEntity.badRequest().body("Error: la solicitud está vacía");
        }else{
            try {
                userService.guardarUsuario(userDTO);
                return ResponseEntity.ok("El usuario se registró correctamente");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error al registrar usuario: " + e.getMessage());
            }
        }

    }

    @Operation(summary = "Eliminar un usuario")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) throws Exception{
        try{
            userService.eliminarUsuario(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar el usuario: "+e.getMessage());
        }
    }

    @Operation(summary = "Actualizar un usuario")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
        if (usuarioDTO == null){
            return ResponseEntity.badRequest().body("Error: los datos estan vacios");
        }else{
            try{
                usuarioDTO.setId(id);
                userService.actualizarUsuario(usuarioDTO);
                return ResponseEntity.ok("Usuario actualizado correctamente");
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al obtener el usuario: "+e.getMessage());
            }
        }
    }
}

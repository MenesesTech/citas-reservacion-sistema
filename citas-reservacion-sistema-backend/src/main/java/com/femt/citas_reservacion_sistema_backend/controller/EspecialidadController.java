package com.femt.citas_reservacion_sistema_backend.controller;

import com.femt.citas_reservacion_sistema_backend.dto.EspecialidadDTO;
import com.femt.citas_reservacion_sistema_backend.service.EspecialidadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Especialidades", description = "Operaciones relacionadas con las especialidades médicas")
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/especialidad")
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    @Operation(summary = "Listar todas las especialidades")
    @GetMapping("/all-especialidades")
    public ResponseEntity<List<EspecialidadDTO>> listarEspecialidades() {
        try {
            List<EspecialidadDTO> especialidades = especialidadService.listarEspecialidades();
            if (especialidades.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(especialidades);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Buscar especialidad por ID")
    @GetMapping("/find/{id}")
    public ResponseEntity<?> obtenerEspecialidadPorId(@PathVariable Long id) {
        try {
            return especialidadService.obtenerEspecialidadPorId(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @Operation(summary = "Registrar una nueva especialidad")
    @PostMapping("/create")
    public ResponseEntity<?> registrarEspecialidad(@RequestBody EspecialidadDTO especialidadDTO) {
        if (especialidadDTO == null) {
            return ResponseEntity.badRequest().body("Error: los datos están vacíos");
        }
        try {
            especialidadService.guardarEspecialidad(especialidadDTO);
            return ResponseEntity.ok("Especialidad registrada correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar la especialidad: " + e.getMessage());
        }
    }

    @Operation(summary = "Actualizar una especialidad existente")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarEspecialidad(@PathVariable Long id, @RequestBody EspecialidadDTO especialidadDTO) {
        if (especialidadDTO == null) {
            return ResponseEntity.badRequest().body("Error: los datos están vacíos");
        }
        try {
            especialidadDTO.setId(id);
            especialidadService.actualizarEspecialidad(especialidadDTO);
            return ResponseEntity.ok("Especialidad actualizada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @Operation(summary = "Eliminar una especialidad por ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarEspecialidad(@PathVariable Long id) {
        try {
            especialidadService.eliminarEspecialidad(id);
            return ResponseEntity.ok("Especialidad eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}

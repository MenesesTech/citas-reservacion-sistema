package com.femt.citas_reservacion_sistema_backend.controller;

import com.femt.citas_reservacion_sistema_backend.dto.SedeDTO;
import com.femt.citas_reservacion_sistema_backend.service.SedeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Sedes", description = "Operaciones CRUD sobre las sedes de atención")
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/sedes")
public class SedeController {

    private final SedeService sedeService;

    public SedeController(SedeService sedeService) {
        this.sedeService = sedeService;
    }

    @Operation(summary = "Obtener todas las sedes registradas")
    @GetMapping
    public ResponseEntity<List<SedeDTO>> listarSedes() {
        try {
            List<SedeDTO> sedes = sedeService.listaSedes();
            return sedes.isEmpty()
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.ok(sedes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @Operation(summary = "Obtener una sede por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerSedePorId(@PathVariable Long id) {
        try {
            return sedeService.obtenerSedePorId(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar la sede: " + e.getMessage());
        }
    }

    @Operation(summary = "Registrar una nueva sede")
    @PostMapping
    public ResponseEntity<String> crearSede(@RequestBody SedeDTO sedeDTO) {
        if (sedeDTO == null) {
            return ResponseEntity.badRequest().body("Datos de sede vacíos");
        }
        try {
            sedeService.guardarSede(sedeDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Sede creada correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar sede: " + e.getMessage());
        }
    }

    @Operation(summary = "Eliminar una sede por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarSede(@PathVariable Long id) {
        try {
            sedeService.eliminarSede(id);
            return ResponseEntity.ok("Sede eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar la sede: " + e.getMessage());
        }
    }

    @Operation(summary = "Actualizar los datos de una sede existente")
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarSede(@PathVariable Long id, @RequestBody SedeDTO sedeDTO) {
        if (sedeDTO == null) {
            return ResponseEntity.badRequest().body("Datos de sede vacíos");
        }
        try {
            sedeDTO.setId(id);
            sedeService.actualizarSede(sedeDTO);
            return ResponseEntity.ok("Sede actualizada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la sede: " + e.getMessage());
        }
    }
}

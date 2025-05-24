package com.femt.citas_reservacion_sistema_backend.controller;

import com.femt.citas_reservacion_sistema_backend.dto.request.SedeRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.SedeResponseDTO;
import com.femt.citas_reservacion_sistema_backend.service.SedeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sedes")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Sede", description = "API para gestionar sedes")
public class SedeController {

    private final SedeService sedeService;

    public SedeController(SedeService sedeService) {
        this.sedeService = sedeService;
    }

    @GetMapping
    @Operation(summary = "Listar todas las sedes")
    public ResponseEntity<List<SedeResponseDTO>> listarSedes() {
        List<SedeResponseDTO> sedes = sedeService.listarSedes();
        return ResponseEntity.ok(sedes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una sede por su ID")
    public ResponseEntity<?> obtenerSedePorId(@PathVariable Long id) {
        Optional<SedeResponseDTO> sede = sedeService.obtenerSedePorId(id);
        if (sede.isPresent()) {
            return ResponseEntity.ok(sede.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Registrar una nueva sede")
    public ResponseEntity<?> registrarSede(@RequestBody SedeRequestDTO sedeRequest) {
        try {
            if (sedeRequest.getNombre() == null || sedeRequest.getNombre().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("El nombre de la sede es obligatorio");
            }
            sedeService.registrarSede(sedeRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Sede registrada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al registrar sede: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una sede existente")
    public ResponseEntity<?> actualizarSede(@PathVariable Long id,
                                            @RequestBody SedeRequestDTO sedeRequest) {
        try {
            if (sedeRequest.getNombre() == null || sedeRequest.getNombre().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("El nombre de la sede es obligatorio");
            }
            sedeService.actualizarSede(id, sedeRequest);
            return ResponseEntity.ok("Sede actualizada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar sede: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una sede por su ID")
    public ResponseEntity<?> eliminarSede(@PathVariable Long id) {
        try {
            sedeService.eliminarSede(id);
            return ResponseEntity.ok("Sede eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar sede: " + e.getMessage());
        }
    }
}

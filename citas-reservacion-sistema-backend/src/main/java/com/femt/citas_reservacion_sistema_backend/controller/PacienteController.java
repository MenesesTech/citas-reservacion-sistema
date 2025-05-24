package com.femt.citas_reservacion_sistema_backend.controller;

import com.femt.citas_reservacion_sistema_backend.dto.request.PacienteRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.PacienteResponseDTO;
import com.femt.citas_reservacion_sistema_backend.service.PacienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Paciente", description = "API para gestionar pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los pacientes")
    public ResponseEntity<?> listarPacientes() {
        try {
            List<PacienteResponseDTO> pacientes = pacienteService.listarPacientes();
            return ResponseEntity.ok(pacientes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar pacientes: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener paciente por ID")
    public ResponseEntity<?> obtenerPacientePorId(@PathVariable Long id) {
        try {
            Optional<PacienteResponseDTO> paciente = pacienteService.obtenerPacientePorId(id);
            if (paciente.isPresent()) {
                return ResponseEntity.ok(paciente.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener paciente: " + e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo paciente")
    public ResponseEntity<?> registrarPaciente(@RequestBody PacienteRequestDTO pacienteRequest) {
        try {
            if (pacienteRequest.getUsuarioId() == null) {
                return ResponseEntity.badRequest().body("El usuario es obligatorio");
            }

            pacienteService.registrarPaciente(pacienteRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Paciente registrado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al registrar paciente: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar paciente existente")
    public ResponseEntity<?> actualizarPaciente(@PathVariable Long id,
                                                @RequestBody PacienteRequestDTO pacienteRequest) {
        try {
            if (pacienteRequest.getUsuarioId() == null) {
                return ResponseEntity.badRequest().body("El usuario es obligatorio");
            }

            PacienteResponseDTO pacienteActualizado = pacienteService.actualizarPaciente(id, pacienteRequest);
            return ResponseEntity.ok(pacienteActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar paciente: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar paciente por ID")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) {
        try {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Paciente eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar paciente: " + e.getMessage());
        }
    }
}

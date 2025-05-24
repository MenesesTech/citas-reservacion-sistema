package com.femt.citas_reservacion_sistema_backend.controller;

import com.femt.citas_reservacion_sistema_backend.dto.request.MedicoRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.MedicoResponseDTO;
import com.femt.citas_reservacion_sistema_backend.service.MedicoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicos")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Médico", description = "API para gestionar médicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los médicos")
    public ResponseEntity<?> listarMedicos() {
        try {
            List<MedicoResponseDTO> medicos = medicoService.listarMedicos();
            return ResponseEntity.ok(medicos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar médicos: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un médico por su ID")
    public ResponseEntity<?> obtenerMedicoPorId(@PathVariable Long id) {
        try {
            Optional<MedicoResponseDTO> medico = medicoService.obtenerMedicoPorId(id);
            if (medico.isPresent()) {
                return ResponseEntity.ok(medico.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener médico: " + e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo médico")
    public ResponseEntity<?> registrarMedico(@RequestBody MedicoRequestDTO medicoRequest) {
        try {
            if (medicoRequest.getUsuarioId() == null) {
                return ResponseEntity.badRequest().body("El usuario es obligatorio");
            }
            if (medicoRequest.getEspecialidadId() == null) {
                return ResponseEntity.badRequest().body("La especialidad es obligatoria");
            }
            if (medicoRequest.getSedeId() == null) {
                return ResponseEntity.badRequest().body("La sede es obligatoria");
            }

            medicoService.registrarMedico(medicoRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Médico registrado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al registrar médico: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un médico existente")
    public ResponseEntity<?> actualizarMedico(@PathVariable Long id,
                                              @RequestBody MedicoRequestDTO medicoRequest) {
        try {
            if (medicoRequest.getUsuarioId() == null) {
                return ResponseEntity.badRequest().body("El usuario es obligatorio");
            }
            if (medicoRequest.getEspecialidadId() == null) {
                return ResponseEntity.badRequest().body("La especialidad es obligatoria");
            }
            if (medicoRequest.getSedeId() == null) {
                return ResponseEntity.badRequest().body("La sede es obligatoria");
            }

            MedicoResponseDTO medicoActualizado = medicoService.actualizarMedico(id, medicoRequest);
            return ResponseEntity.ok(medicoActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar médico: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un médico por su ID")
    public ResponseEntity<?> eliminarMedico(@PathVariable Long id) {
        try {
            medicoService.eliminarMedico(id);
            return ResponseEntity.ok("Médico eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar médico: " + e.getMessage());
        }
    }
}

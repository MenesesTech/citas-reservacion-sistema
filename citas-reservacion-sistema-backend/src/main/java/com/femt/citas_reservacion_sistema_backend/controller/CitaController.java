package com.femt.citas_reservacion_sistema_backend.controller;

import com.femt.citas_reservacion_sistema_backend.dto.request.CitaRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.CitaResponseDTO;
import com.femt.citas_reservacion_sistema_backend.service.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Citas", description = "Operaciones relacionadas con la gestión de citas médicas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping
    @Operation(summary = "Listar todas las citas", description = "Recupera todas las citas registradas en el sistema")
    public ResponseEntity<List<CitaResponseDTO>> listarCitas() {
        try {
            List<CitaResponseDTO> citas = citaService.listarCitas();
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una cita por ID", description = "Devuelve una cita específica si existe")
    public ResponseEntity<?> obtenerCitaPorId(@PathVariable Long id) {
        try {
            Optional<CitaResponseDTO> cita = citaService.obtenerCitaPorId(id);
            return cita.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener cita: " + e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Registrar una nueva cita", description = "Crea una nueva cita con los datos proporcionados")
    public ResponseEntity<?> registrarCita(@RequestBody CitaRequestDTO citaRequest) {
        try {
            if (citaRequest.getFechaHoraId() == null || citaRequest.getPacienteId() == null) {
                return ResponseEntity.badRequest().body("FechaHoraId y PacienteId son obligatorios");
            }

            citaService.registrarCita(citaRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cita registrada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al registrar cita: " + e.getMessage());
        }
    }

    @PutMapping
    @Operation(summary = "Actualizar una cita", description = "Modifica los datos de una cita existente")
    public ResponseEntity<?> actualizarCita(@RequestBody CitaRequestDTO citaRequest) {
        try {
            if (citaRequest.getFechaHoraId() == null || citaRequest.getPacienteId() == null) {
                return ResponseEntity.badRequest().body("FechaHoraId y PacienteId son obligatorios");
            }

            citaService.actualizarCita(citaRequest);
            return ResponseEntity.ok("Cita actualizada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar cita: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una cita", description = "Elimina una cita del sistema por su ID")
    public ResponseEntity<?> eliminarCita(@PathVariable Long id) {
        try {
            citaService.eliminarCita(id);
            return ResponseEntity.ok("Cita eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al eliminar cita: " + e.getMessage());
        }
    }
}

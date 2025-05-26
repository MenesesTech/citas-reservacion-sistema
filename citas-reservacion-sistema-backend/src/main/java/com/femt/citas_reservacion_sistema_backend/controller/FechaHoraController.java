package com.femt.citas_reservacion_sistema_backend.controller;

import com.femt.citas_reservacion_sistema_backend.dto.request.FechaHoraRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.FechaHoraResponseDTO;
import com.femt.citas_reservacion_sistema_backend.service.FechaHoraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fechas-horas")
@CrossOrigin(origins = "*")
@Tag(name = "Fecha y Hora", description = "Gestión de disponibilidad de fechas y horas para médicos")
public class FechaHoraController {

    private final FechaHoraService fechaHoraService;

    public FechaHoraController(FechaHoraService fechaHoraService) {
        this.fechaHoraService = fechaHoraService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los registros de fecha y hora", description = "Obtiene todas las fechas y horas registradas")
    public ResponseEntity<List<FechaHoraResponseDTO>> listarTodos() {
        try {
            return ResponseEntity.ok(fechaHoraService.listarTodos());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/medico/{id}")
    @Operation(summary = "Listar Fechas Disponibles por Medico")
    public ResponseEntity<?> listarFechasPorMedico(@PathVariable Long id){
        try {
            List<FechaHoraResponseDTO> fechas = fechaHoraService.listarFechasDisponiblesPorMedico(id);
            return ResponseEntity.ok(fechas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener fecha y hora por ID", description = "Devuelve una fecha y hora específica")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            Optional<FechaHoraResponseDTO> response = fechaHoraService.obtenerPorId(id);
            return response.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Registrar una nueva fecha y hora", description = "Crea un nuevo registro de disponibilidad para un médico")
    public ResponseEntity<?> registrarFechaHora(@RequestBody FechaHoraRequestDTO dto) {
        try {
            fechaHoraService.registrarFechaHora(dto);
            return ResponseEntity.status(201).body("Fecha y hora registrada correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una fecha y hora", description = "Modifica un registro de disponibilidad existente")
    public ResponseEntity<?> actualizarFechaHora(@PathVariable Long id, @RequestBody FechaHoraRequestDTO dto) {
        try {
            FechaHoraResponseDTO actualizado = fechaHoraService.actualizarFechaHora(id, dto);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una fecha y hora", description = "Elimina una fecha y hora del sistema")
    public ResponseEntity<?> eliminarFechaHora(@PathVariable Long id) {
        try {
            fechaHoraService.eliminarFechaHora(id);
            return ResponseEntity.ok("Fecha y hora eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/disponibles/medico/{medicoId}")
    @Operation(summary = "Listar horarios disponibles por médico", description = "Obtiene las fechas y horas disponibles para un médico específico")
    public ResponseEntity<?> listarDisponiblesPorMedico(@PathVariable Long medicoId) {
        try {
            List<FechaHoraResponseDTO> disponibles = fechaHoraService.listarDisponiblesPorMedico(medicoId);
            return ResponseEntity.ok(disponibles);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/disponibles/medico/{medicoId}/fecha/{fecha}")
    @Operation(summary = "Listar horarios disponibles por médico y fecha", description = "Obtiene la disponibilidad por médico y fecha")
    public ResponseEntity<?> listarDisponiblesPorMedicoYFecha(
            @PathVariable Long medicoId,
            @PathVariable LocalDate fecha) {
        try {
            List<FechaHoraResponseDTO> disponibles = fechaHoraService.listarDisponiblesPorMedicoYFecha(medicoId, fecha);
            return ResponseEntity.ok(disponibles);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}

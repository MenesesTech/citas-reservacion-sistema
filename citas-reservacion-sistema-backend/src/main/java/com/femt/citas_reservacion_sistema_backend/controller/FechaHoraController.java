package com.femt.citas_reservacion_sistema_backend.controller;

import com.femt.citas_reservacion_sistema_backend.dto.FechaHoraRequestDTO;
import com.femt.citas_reservacion_sistema_backend.service.FechaHoraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Horarios", description = "Operaciones relacionadas con la disponibilidad de médicos")
@RestController
@RequestMapping("/api/v1/horarios")
@CrossOrigin(origins = "*")
public class FechaHoraController {

    @Autowired
    private FechaHoraService fechaHoraService;

    @Operation(summary = "Listar todos los horarios")
    @GetMapping
    public ResponseEntity<List<FechaHoraRequestDTO>> obtenerTodos() {
        return ResponseEntity.ok(fechaHoraService.obtenerTodos());
    }

    @Operation(summary = "Listar horarios por ID de médico")
    @GetMapping("/medico/{id}")
    public ResponseEntity<List<FechaHoraRequestDTO>> obtenerPorMedico(@PathVariable Long id) {
        return ResponseEntity.ok(fechaHoraService.obtenerPorMedico(id));
    }

    @Operation(summary = "Listar horarios disponibles por ID de médico")
    @GetMapping("/disponibles/{id}")
    public ResponseEntity<List<FechaHoraRequestDTO>> obtenerDisponiblesPorMedico(@PathVariable Long id) {
        return ResponseEntity.ok(fechaHoraService.obtenerDisponiblesPorMedico(id));
    }

    @Operation(summary = "Listar horarios por médico y fecha")
    @GetMapping("/medico/{id}/fecha/{fecha}")
    public ResponseEntity<List<FechaHoraRequestDTO>> obtenerPorMedicoYFecha(
            @PathVariable Long id,
            @PathVariable String fecha) {
        return ResponseEntity.ok(
                fechaHoraService.obtenerPorMedicoYFecha(id, LocalDate.parse(fecha))
        );
    }

    @Operation(summary = "Listar horarios disponibles desde una fecha específica")
    @GetMapping("/disponibles-desde")
    public ResponseEntity<List<FechaHoraRequestDTO>> obtenerDisponiblesDesde(
            @RequestParam Long medicoId,
            @RequestParam String fechaInicio) {
        return ResponseEntity.ok(
                fechaHoraService.obtenerDisponiblesDesde(medicoId, LocalDate.parse(fechaInicio))
        );
    }

    @Operation(summary = "Buscar un horario específico por médico, fecha y hora")
    @GetMapping("/buscar")
    public ResponseEntity<?> obtenerPorMedicoFechaHora(
            @RequestParam Long medicoId,
            @RequestParam String fecha,
            @RequestParam String hora) {
        return fechaHoraService.obtenerPorMedicoFechaHora(medicoId, LocalDate.parse(fecha), hora)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo horario")
    @PostMapping
    public ResponseEntity<FechaHoraRequestDTO> crearHorario(@RequestBody FechaHoraRequestDTO requestDTO) {
        return ResponseEntity.ok(fechaHoraService.crearHorario(requestDTO));
    }

    @Operation(summary = "Eliminar un horario por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarHorario(@PathVariable Long id) {
        fechaHoraService.eliminarHorario(id);
        return ResponseEntity.ok("Horario eliminado correctamente");
    }
}

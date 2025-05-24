package com.femt.citas_reservacion_sistema_backend.controller;

import com.femt.citas_reservacion_sistema_backend.service.FechaHoraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Fechas y Horarios", description = "Operaciones relacionadas con fechas y horas disponibles")
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/horarios")
public class FechaHoraController {

    @Autowired
    private FechaHoraService fechaHoraService;

    @Operation(summary = "Listar todos los horarios")
    @GetMapping("/all")
    public ResponseEntity<List<FechaHoraDTO>> listarHorarios() {
        try {
            List<FechaHoraDTO> horarios = fechaHoraService.listaHorarios();
            if (horarios.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(horarios);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Obtener un horario por ID")
    @GetMapping("/find/{id}")
    public ResponseEntity<?> obtenerHorarioPorId(@PathVariable Long id) {
        try {
            return fechaHoraService.obtenerHorarioPorId(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @Operation(summary = "Crear un nuevo horario")
    @PostMapping("/create")
    public ResponseEntity<?> crearHorario(@RequestBody FechaHoraDTO horarioDTO) {
        if (horarioDTO == null) {
            return ResponseEntity.badRequest().body("Error: la solicitud está vacía");
        } else {
            try {
                FechaHoraDTO creado = fechaHoraService.guardarHorario(horarioDTO);
                return ResponseEntity.ok(creado);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error al crear horario: " + e.getMessage());
            }
        }
    }

    @Operation(summary = "Eliminar un horario por ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarHorario(@PathVariable Long id) {
        try {
            fechaHoraService.eliminarHorario(id);
            return ResponseEntity.ok("Horario eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar horario: " + e.getMessage());
        }
    }
}

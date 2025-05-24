package com.femt.citas_reservacion_sistema_backend.controller;

import com.femt.citas_reservacion_sistema_backend.service.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Citas", description = "Operaciones relacionadas con las citas médicas")
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Operation(summary = "Listar todas las citas")
    @GetMapping("/all-citas")
    public ResponseEntity<List<CitaDTO>> listarCitas() {
        try {
            List<CitaDTO> citas = citaService.listaCitas();
            if (citas.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Obtener una cita por ID")
    @GetMapping("/find/{idCita}")
    public ResponseEntity<?> obtenerCitaPorId(@PathVariable Long idCita) {
        try {
            return citaService.obtenerCitaPorId(idCita)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @Operation(summary = "Crear una nueva cita")
    @PostMapping("/create")
    public ResponseEntity<?> crearCita(@RequestBody CitaDTO citaDTO) {
        if (citaDTO == null) {
            return ResponseEntity.badRequest().body("Error: la solicitud está vacía");
        }else{
            try {
                CitaDTO citaCreada = citaService.guardarCita(citaDTO);
                return ResponseEntity.ok("Cita registrada correctamente");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error al crear cita: " + e.getMessage());
            }
        }
    }

    @Operation(summary = "Actualizar una cita")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarCita(@PathVariable Long id, @RequestBody CitaDTO citaDTO) {
        if (citaDTO == null) {
            return ResponseEntity.badRequest().body("Error: la solicitud está vacía");
        }else{
            try {
                citaDTO.setId(id);
                CitaDTO citaActualizada = citaService.actualizarCita(citaDTO);
                return ResponseEntity.ok(citaActualizada);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error al actualizar cita: " + e.getMessage());
            }
        }
    }

    @Operation(summary = "Eliminar una cita")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarCita(@PathVariable Long id) {
        try {
            citaService.eliminarCita(id);
            return ResponseEntity.ok("Cita eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar cita: " + e.getMessage());
        }
    }

    @Operation(summary = "Buscar citas por ID del médico")
    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<?> obtenerCitasPorMedico(@PathVariable Long medicoId) {
        try {
            List<CitaDTO> citas = citaService.obtenerCitasPorMedico(medicoId);
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @Operation(summary = "Buscar citas por ID del usuario")
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> obtenerCitasPorUsuario(@PathVariable Long usuarioId) {
        try {
            List<CitaDTO> citas = citaService.obtenerCitasPorUsuario(usuarioId);
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @Operation(summary = "Buscar citas por ID de la sede")
    @GetMapping("/sede/{sedeId}")
    public ResponseEntity<?> obtenerCitasPorSede(@PathVariable Long sedeId) {
        try {
            List<CitaDTO> citas = citaService.obtenerCitasPorSede(sedeId);
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @Operation(summary = "Buscar citas por fecha")
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<?> obtenerCitasPorFecha(@PathVariable String fecha) {
        try {
            LocalDate fechaLocalDate = LocalDate.parse(fecha);
            List<CitaDTO> citas = citaService.obtenerCitasPorFecha(fechaLocalDate);
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @Operation(summary = "Buscar citas por estado")
    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> obtenerCitasPorEstado(@PathVariable String estado) {
        try {
            EstadoDeCita estadoCita = EstadoDeCita.valueOf(estado.toUpperCase());
            List<CitaDTO> citas = citaService.obtenerCitasPorEstado(estadoCita);
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
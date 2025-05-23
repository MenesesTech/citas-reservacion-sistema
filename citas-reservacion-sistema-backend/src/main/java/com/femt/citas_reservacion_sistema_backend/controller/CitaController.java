package com.femt.citas_reservacion_sistema_backend.controller;

import com.femt.citas_reservacion_sistema_backend.dto.CitaDTO;
import com.femt.citas_reservacion_sistema_backend.entity.EstadoDeCita;
import com.femt.citas_reservacion_sistema_backend.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public ResponseEntity<?> listarCitas() {
        try {
            List<CitaDTO> citas = citaService.listaCitas();
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener citas: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCitaPorId(@PathVariable Long id) {
        try {
            return citaService.obtenerCitaPorId(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> crearCita(@RequestBody CitaDTO citaDTO) {
        if (citaDTO == null) {
            return ResponseEntity.badRequest().body("Error: la solicitud está vacía");
        }
        try {
            CitaDTO citaCreada = citaService.guardarCita(citaDTO);
            return ResponseEntity.ok(citaCreada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear cita: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCita(@PathVariable Long id, @RequestBody CitaDTO citaDTO) {
        if (citaDTO == null) {
            return ResponseEntity.badRequest().body("Error: la solicitud está vacía");
        }
        try {
            citaDTO.setId(id);
            CitaDTO citaActualizada = citaService.actualizarCita(citaDTO);
            return ResponseEntity.ok(citaActualizada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar cita: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCita(@PathVariable Long id) {
        try {
            citaService.eliminarCita(id);
            return ResponseEntity.ok("Cita eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar cita: " + e.getMessage());
        }
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<?> obtenerCitasPorMedico(@PathVariable Long medicoId) {
        try {
            List<CitaDTO> citas = citaService.obtenerCitasPorMedico(medicoId);
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> obtenerCitasPorUsuario(@PathVariable Long usuarioId) {
        try {
            List<CitaDTO> citas = citaService.obtenerCitasPorUsuario(usuarioId);
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/sede/{sedeId}")
    public ResponseEntity<?> obtenerCitasPorSede(@PathVariable Long sedeId) {
        try {
            List<CitaDTO> citas = citaService.obtenerCitasPorSede(sedeId);
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/especialidad/{especialidadId}")
    public ResponseEntity<?> obtenerCitasPorEspecialidad(@PathVariable Long especialidadId) {
        try {
            List<CitaDTO> citas = citaService.obtenerCitasPorEspecialidad(especialidadId);
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

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
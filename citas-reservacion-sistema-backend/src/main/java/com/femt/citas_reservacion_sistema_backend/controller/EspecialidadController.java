package com.femt.citas_reservacion_sistema_backend.controller;

import com.femt.citas_reservacion_sistema_backend.dto.request.EspecialidadRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.EspecialidadResponseDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.MedicoResponseDTO;
import com.femt.citas_reservacion_sistema_backend.service.EspecialidadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/especialidades")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Especialidad", description = "Operaciones relacionadas con especialidades médicas")
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    public EspecialidadController(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @GetMapping
    @Operation(summary = "Listar todas las especialidades")
    public ResponseEntity<List<EspecialidadResponseDTO>> listarEspecialidades() {
        List<EspecialidadResponseDTO> especialidades = especialidadService.listarTodas();
        return ResponseEntity.ok(especialidades);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una especialidad por su ID")
    public ResponseEntity<?> obtenerEspecialidadPorId(@PathVariable Long id) {
        try {
            Optional<EspecialidadResponseDTO> especialidad = especialidadService.obtenerPorId(id);
            if (especialidad.isPresent()) {
                return ResponseEntity.ok(especialidad.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener especialidad: " + e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Registrar una nueva especialidad")
    public ResponseEntity<?> registrarEspecialidad(@RequestBody EspecialidadRequestDTO especialidadRequest) {
        try {
            if (especialidadRequest.getNombre() == null || especialidadRequest.getNombre().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("El nombre de la especialidad es obligatorio");
            }

            EspecialidadResponseDTO especialidad = especialidadService.registrarEspecialidad(especialidadRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(especialidad);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al registrar especialidad: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una especialidad existente")
    public ResponseEntity<?> actualizarEspecialidad(@PathVariable Long id,
                                                    @RequestBody EspecialidadRequestDTO especialidadRequest) {
        try {
            if (especialidadRequest.getNombre() == null || especialidadRequest.getNombre().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("El nombre de la especialidad es obligatorio");
            }

            EspecialidadResponseDTO especialidadActualizada =
                    especialidadService.actualizarEspecialidad(id, especialidadRequest);
            return ResponseEntity.ok(especialidadActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar especialidad: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una especialidad por su ID")
    public ResponseEntity<?> eliminarEspecialidad(@PathVariable Long id) {
        try {
            especialidadService.eliminarEspecialidad(id);
            return ResponseEntity.ok("Especialidad eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar especialidad: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/medicos")
    @Operation(summary = "Listar médicos asociados a una especialidad")
    public ResponseEntity<?> listarMedicosPorEspecialidad(@PathVariable Long id) {
        try {
            List<MedicoResponseDTO> medicos = especialidadService.listarMedicosPorEspecialidad(id);
            return ResponseEntity.ok(medicos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al obtener médicos por especialidad: " + e.getMessage());
        }
    }
}

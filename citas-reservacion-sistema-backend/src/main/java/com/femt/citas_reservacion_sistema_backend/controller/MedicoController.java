package com.femt.citas_reservacion_sistema_backend.controller;

import com.femt.citas_reservacion_sistema_backend.dto.MedicoRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.MedicoResponseDTO;
import com.femt.citas_reservacion_sistema_backend.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Médicos", description = "Operaciones relacionadas con los médicos del sistema")
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Operation(summary = "Listar todos los médicos")
    @GetMapping
    public ResponseEntity<List<MedicoResponseDTO>> listarMedicos() {
        try {
            List<MedicoResponseDTO> medicos = medicoService.listaMedicos();
            if (medicos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(medicos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Obtener un médico por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerMedicoPorId(@PathVariable Long id) {
        try {
            return medicoService.obtenerMedicoPorId(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @Operation(summary = "Registrar un nuevo médico")
    @PostMapping
    public ResponseEntity<?> crearMedico(@RequestBody MedicoRequestDTO medicoDTO) {
        if (medicoDTO == null) {
            return ResponseEntity.badRequest().body("Error: la solicitud está vacía");
        }
        try {
            medicoService.guardarMedico(medicoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Médico registrado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear médico: " + e.getMessage());
        }
    }

    @Operation(summary = "Actualizar un médico existente")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarMedico(@PathVariable Long id, @RequestBody MedicoRequestDTO medicoDTO) {
        if (medicoDTO == null) {
            return ResponseEntity.badRequest().body("Error: la solicitud está vacía");
        }
        try {
            medicoDTO.setId(id);
            medicoService.actualizarMedico(medicoDTO);
            return ResponseEntity.ok("Médico actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar médico: " + e.getMessage());
        }
    }

    @Operation(summary = "Eliminar un médico por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMedico(@PathVariable Long id) {
        try {
            medicoService.eliminarMedico(id);
            return ResponseEntity.ok("Médico eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar médico: " + e.getMessage());
        }
    }
}

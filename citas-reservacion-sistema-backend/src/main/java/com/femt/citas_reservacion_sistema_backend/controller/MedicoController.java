package com.femt.citas_reservacion_sistema_backend.controller;

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
    @GetMapping("/all-medicos")
    public ResponseEntity<List<MedicoDTO>> listarMedicos() {
        try {
            List<MedicoDTO> medicos = medicoService.listaMedicos();
            if (medicos.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(medicos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Obtener un médico por ID")
    @GetMapping("/find/{idMedico}")
    public ResponseEntity<?> obtenerMedicoPorId(@PathVariable Long idMedico) {
        try {
            return medicoService.obtenerMedicoPorId(idMedico)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @Operation(summary = "Registrar un nuevo médico")
    @PostMapping("/create")
    public ResponseEntity<?> crearMedico(@RequestBody MedicoDTO medicoDTO) {
        if (medicoDTO == null) {
            return ResponseEntity.badRequest().body("Error: la solicitud está vacía");
        } else {
            try {
                medicoService.guardarMedico(medicoDTO);
                return ResponseEntity.ok("Médico registrado correctamente");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error al crear médico: " + e.getMessage());
            }
        }
    }

    @Operation(summary = "Actualizar un médico existente")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarMedico(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO) {
        if (medicoDTO == null) {
            return ResponseEntity.badRequest().body("Error: la solicitud está vacía");
        } else {
            try {
                medicoDTO.setId(id);
                medicoService.actualizarMedico(medicoDTO);
                return ResponseEntity.ok("Médico actualizado correctamente");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error al actualizar médico: " + e.getMessage());
            }
        }
    }

    @Operation(summary = "Eliminar un médico por ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarMedico(@PathVariable Long id) {
        try {
            medicoService.eliminarMedico(id);
            return ResponseEntity.ok("Médico eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar médico: " + e.getMessage());
        }
    }
}

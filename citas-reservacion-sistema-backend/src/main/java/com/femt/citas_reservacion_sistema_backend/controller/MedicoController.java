package com.femt.citas_reservacion_sistema_backend.controller;

import com.femt.citas_reservacion_sistema_backend.dto.MedicoDTO;
import com.femt.citas_reservacion_sistema_backend.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<?> listarMedicos() {
        try {
            List<MedicoDTO> medicos = medicoService.listaMedicos();
            return ResponseEntity.ok(medicos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener médicos: " + e.getMessage());
        }
    }

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

    @GetMapping("/{id}/especialidades")
    public ResponseEntity<?> obtenerMedicoConEspecialidades(@PathVariable Long id) {
        try {
            return medicoService.obtenerMedicoConEspecialidades(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> crearMedico(@RequestBody MedicoDTO medicoDTO) {
        if (medicoDTO == null) {
            return ResponseEntity.badRequest().body("Error: la solicitud está vacía");
        }
        try {
            medicoService.guardarMedico(medicoDTO);
            return ResponseEntity.ok("Médico creado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear médico: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarMedico(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO) {
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
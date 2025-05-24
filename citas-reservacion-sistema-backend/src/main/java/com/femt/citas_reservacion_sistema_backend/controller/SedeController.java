package com.femt.citas_reservacion_sistema_backend.controller;

import com.femt.citas_reservacion_sistema_backend.dto.SedeDTO;
import com.femt.citas_reservacion_sistema_backend.service.SedeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Sedes", description = "Operaciones relacionadas con las sedes de atención")
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/sede")
public class SedeController {

    @Autowired
    private SedeService sedeService;

    @Operation(summary = "Listar todas las sedes")
    @GetMapping("/all-sedes")
    public ResponseEntity<List<SedeDTO>> listarSedes() {
        try {
            List<SedeDTO> sedes = sedeService.listaSedes();
            if (sedes.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(sedes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Buscar una sede por ID")
    @GetMapping("/find/{id}")
    public ResponseEntity<?> obtenerSedePorId(@PathVariable Long id) {
        try {
            SedeDTO sede = sedeService.obtenerSedePorId(id);
            return ResponseEntity.ok(sede);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    @Operation(summary = "Crear una nueva sede")
    @PostMapping("/create")
    public ResponseEntity<?> crearSede(@RequestBody SedeDTO sedeDTO) {
        if (sedeDTO == null) {
            return ResponseEntity.badRequest().body("Error: la solicitud está vacía");
        }
        try {
            sedeService.guardarSede(sedeDTO);
            return ResponseEntity.ok("La sede se registró correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar sede: " + e.getMessage());
        }
    }

    @Operation(summary = "Eliminar una sede por ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarSede(@PathVariable Long id) {
        try {
            sedeService.eliminarSede(id);
            return ResponseEntity.ok("Sede eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar la sede: " + e.getMessage());
        }
    }

    @Operation(summary = "Actualizar una sede existente")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarSede(@PathVariable Long id, @RequestBody SedeDTO sedeDTO) {
        if (sedeDTO == null) {
            return ResponseEntity.badRequest().body("Error: los datos están vacíos");
        }
        try {
            sedeDTO.setId(id);
            sedeService.actualizarSede(sedeDTO);
            return ResponseEntity.ok("Sede actualizada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la sede: " + e.getMessage());
        }
    }
}

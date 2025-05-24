package com.femt.citas_reservacion_sistema_backend.controller;

import com.femt.citas_reservacion_sistema_backend.dto.request.PagoRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.PagoResponseDTO;
import com.femt.citas_reservacion_sistema_backend.service.PagoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "*")
@Tag(name = "Pagos", description = "Endpoints para gestionar pagos de citas médicas")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @Operation(summary = "Listar todos los pagos", description = "Obtiene la lista completa de pagos registrados")
    @GetMapping
    public ResponseEntity<List<PagoResponseDTO>> listarPagos() throws Exception {
        List<PagoResponseDTO> pagos = pagoService.listarPagos();
        return ResponseEntity.ok(pagos);
    }

    @Operation(summary = "Obtener pago por ID", description = "Busca un pago específico por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<PagoResponseDTO> obtenerPagoPorId(@PathVariable Long id) throws Exception {
        return pagoService.obtenerPagoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener pagos por ID de cita", description = "Lista todos los pagos asociados a una cita médica")
    @GetMapping("/cita/{citaId}")
    public ResponseEntity<List<PagoResponseDTO>> obtenerPagosPorCita(@PathVariable Long citaId) throws Exception {
        List<PagoResponseDTO> pagos = pagoService.obtenerPagosPorCita(citaId);
        return ResponseEntity.ok(pagos);
    }

    @Operation(summary = "Obtener pago por ID de transacción", description = "Busca un pago por el identificador de la transacción en la pasarela de pago")
    @GetMapping("/transaccion/{idTransaccion}")
    public ResponseEntity<PagoResponseDTO> obtenerPagoPorTransaccion(@PathVariable String idTransaccion) throws Exception {
        return pagoService.obtenerPagoPorTransaccion(idTransaccion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar un nuevo pago", description = "Guarda un pago relacionado a una cita médica")
    @PostMapping
    public ResponseEntity<PagoResponseDTO> guardarPago(@RequestBody PagoRequestDTO pagoRequest) throws Exception {
        PagoResponseDTO nuevoPago = pagoService.guardarPago(pagoRequest);
        return ResponseEntity.ok(nuevoPago);
    }

    @Operation(summary = "Actualizar un pago existente", description = "Actualiza la información de un pago ya registrado")
    @PutMapping
    public ResponseEntity<PagoResponseDTO> actualizarPago(@RequestBody PagoRequestDTO pagoRequest) throws Exception {
        PagoResponseDTO actualizado = pagoService.actualizarPago(pagoRequest);
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Eliminar un pago", description = "Elimina un pago a partir de su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) throws Exception {
        pagoService.eliminarPago(id);
        return ResponseEntity.noContent().build();
    }
}

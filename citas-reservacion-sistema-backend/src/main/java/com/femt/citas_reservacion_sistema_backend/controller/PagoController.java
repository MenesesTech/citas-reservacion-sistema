package com.femt.citas_reservacion_sistema_backend.controller;

import com.femt.citas_reservacion_sistema_backend.dto.request.PagoRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.PagoResponseDTO;
import com.femt.citas_reservacion_sistema_backend.service.PagoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "*") // Habilita CORS si es necesario
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public ResponseEntity<List<PagoResponseDTO>> listarPagos() throws Exception {
        List<PagoResponseDTO> pagos = pagoService.listarPagos();
        return ResponseEntity.ok(pagos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoResponseDTO> obtenerPagoPorId(@PathVariable Long id) throws Exception {
        return pagoService.obtenerPagoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cita/{citaId}")
    public ResponseEntity<List<PagoResponseDTO>> obtenerPagosPorCita(@PathVariable Long citaId) throws Exception {
        List<PagoResponseDTO> pagos = pagoService.obtenerPagosPorCita(citaId);
        return ResponseEntity.ok(pagos);
    }

    @GetMapping("/transaccion/{idTransaccion}")
    public ResponseEntity<PagoResponseDTO> obtenerPagoPorTransaccion(@PathVariable String idTransaccion) throws Exception {
        return pagoService.obtenerPagoPorTransaccion(idTransaccion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PagoResponseDTO> guardarPago(@RequestBody PagoRequestDTO pagoRequest) throws Exception {
        PagoResponseDTO nuevoPago = pagoService.guardarPago(pagoRequest);
        return ResponseEntity.ok(nuevoPago);
    }

    @PutMapping
    public ResponseEntity<PagoResponseDTO> actualizarPago(@RequestBody PagoRequestDTO pagoRequest) throws Exception {
        PagoResponseDTO actualizado = pagoService.actualizarPago(pagoRequest);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) throws Exception {
        pagoService.eliminarPago(id);
        return ResponseEntity.noContent().build();
    }
}

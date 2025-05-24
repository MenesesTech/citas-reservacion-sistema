package com.femt.citas_reservacion_sistema_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoRequestDTO {
    private String idTransaccionPasarela;
    private String proveedor_pasarela;
    private Long citaId;
    private LocalDateTime fechaPago = LocalDateTime.now();
}

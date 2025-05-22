package com.femt.citas_reservacion_sistema_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {
    private Long id;
    private BigDecimal monto;
    private String idTransaccionPasarela;
    private String proveedor_pasarela;
    private LocalDateTime fechaPago;
    private Long citaId;
}

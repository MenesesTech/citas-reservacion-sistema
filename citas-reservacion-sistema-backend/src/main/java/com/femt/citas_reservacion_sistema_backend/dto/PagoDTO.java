package com.femt.citas_reservacion_sistema_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagoDTO {
    private Long id;
    private BigDecimal monto;
    private String id_transaccion_pasarela;
    private String proveedor_pasarela;
    private LocalDateTime fechaPago;
    private Long citaId;
}

package com.femt.citas_reservacion_sistema_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoResponseDTO {
    private Long id;
    private String idTransaccionPasarela;
    private String proveedorPasarela;
    private LocalDateTime fechaPago;
    private Long citaId;
}

package com.femt.citas_reservacion_sistema_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EspecialidadResponseDTO {
    private Long id;
    private String nombre;
    private double monto;
}

package com.femt.citas_reservacion_sistema_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteResponseDTO {
    private Long id;
    private Long usuarioId;
    private String nombreCompleto;
    private String dni;
}


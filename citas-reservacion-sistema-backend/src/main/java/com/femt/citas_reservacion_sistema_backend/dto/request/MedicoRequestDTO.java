package com.femt.citas_reservacion_sistema_backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoRequestDTO {
    private Long usuarioId;
    private Long especialidadId;
    private Long sedeId;
}

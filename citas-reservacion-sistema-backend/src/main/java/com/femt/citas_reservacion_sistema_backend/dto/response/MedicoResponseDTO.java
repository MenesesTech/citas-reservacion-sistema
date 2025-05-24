package com.femt.citas_reservacion_sistema_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoResponseDTO {
    private Long id;
    private Long usuarioId;
    private String nombreCompleto;
    private Long especialidadId;
    private String nombreEspecialidad;
    private Long sedeId;
    private String nombreSede;
}

package com.femt.citas_reservacion_sistema_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoDTO {
    private Long id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String celular;
    private Boolean activo;
}

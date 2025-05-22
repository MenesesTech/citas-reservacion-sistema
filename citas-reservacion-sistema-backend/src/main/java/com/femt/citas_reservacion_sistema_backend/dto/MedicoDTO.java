package com.femt.citas_reservacion_sistema_backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String celular;
    private String sede;
    private List<EspecialidadDTO> especialidades;
}

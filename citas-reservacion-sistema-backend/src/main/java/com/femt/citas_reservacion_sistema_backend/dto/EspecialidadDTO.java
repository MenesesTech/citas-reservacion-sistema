package com.femt.citas_reservacion_sistema_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EspecialidadDTO {
    private Long id = null;
    private String nombre;

    public Long getId() {
        return id;
    }
}

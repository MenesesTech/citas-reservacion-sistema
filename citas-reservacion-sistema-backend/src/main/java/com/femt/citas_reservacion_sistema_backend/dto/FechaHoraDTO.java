package com.femt.citas_reservacion_sistema_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FechaHoraDTO {
    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private Boolean disponible;
    // Informacion del medico
    private String nombreMedico;
    private String apellidoMedico;
}

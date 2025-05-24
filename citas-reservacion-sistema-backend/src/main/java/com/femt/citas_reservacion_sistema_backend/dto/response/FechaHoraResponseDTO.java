package com.femt.citas_reservacion_sistema_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FechaHoraResponseDTO {
    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private Boolean disponible;
    private Long medicoId;
    private String nombreMedico;
}

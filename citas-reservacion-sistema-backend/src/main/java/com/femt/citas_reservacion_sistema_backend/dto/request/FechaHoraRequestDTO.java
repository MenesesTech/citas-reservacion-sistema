package com.femt.citas_reservacion_sistema_backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FechaHoraRequestDTO {
    private LocalDate fecha;
    private LocalTime hora;
    private Boolean disponible;
    private Long medicoId;
}

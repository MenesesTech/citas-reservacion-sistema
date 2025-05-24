package com.femt.citas_reservacion_sistema_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaResponseDTO {
    private String especialidadMedico;
    private String nombreMedico;
    private String tipoPaciente;
    private LocalDate fecha;
    private LocalTime hora;
    private double monto;
    private String estado;
}

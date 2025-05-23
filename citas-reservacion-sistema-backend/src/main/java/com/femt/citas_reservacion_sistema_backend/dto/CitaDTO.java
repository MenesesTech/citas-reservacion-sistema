package com.femt.citas_reservacion_sistema_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaDTO {
    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private BigDecimal monto;
    private String estado;
    private String tipoPaciente;
    // Datos del médico asignado
    private String nombreMedico;
    private String apellidoMedico;
    // Especialidad seleccionada para esta cita
    private String especialidad;
    // Nombre completo del paciente
    private String nombrePaciente;
    private String apellidoPaciente;
}

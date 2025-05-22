package com.femt.citas_reservacion_sistema_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class CitaDTO {
    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private BigDecimal monto;
    private String estado;
    private String medicoNombre;
    private String especialidadNombre;
    private String tipoPacienteNombre;
}

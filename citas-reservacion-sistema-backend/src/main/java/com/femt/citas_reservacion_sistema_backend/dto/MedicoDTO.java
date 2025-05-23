package com.femt.citas_reservacion_sistema_backend.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoDTO {
    private Long id;
    // Nombre del médico
    private String nombre;
    // Apellidos del médico
    private String apellido;
    // Correo del médico
    private String correo;
    // Teléfono del médico
    private String celular;
    // Nombre de la sede donde trabaja el médico
    private SedeDTO sede;
    // Nombres de las especialidades asociadas al médico
    private List<String> especialidades;
    // Informacion de disponibilidad
    private LocalDate fechaDisponible;
    private LocalTime horaDisponible;
    private Boolean disponible;
}

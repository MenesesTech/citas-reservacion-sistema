package com.femt.citas_reservacion_sistema_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "horarios_disponibles")
public class FechaHora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Fecha disponible del medico
    private LocalDate fecha;
    // Horario disponible del medico
    private LocalTime hora;
    // Estado de disponibilidad
    private Boolean disponible = true;
    /**
     * Relación muchos-a-uno para permitir múltiples horarios por médico
     */
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
}

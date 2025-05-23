package com.femt.citas_reservacion_sistema_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "citas")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Identificador de la cita
    private Long id;
    // Fecha programada de la cita
    private LocalDate fecha;
    // Horario programada para la cita
    private LocalTime hora;
    // Monto a pagar por la cita
    private BigDecimal monto;
    // Cita dirigido a un tipo de paciente (SEGURO, PARTICULAR Y OTROS)
    private String tipoPaciente;

    // Estado de la cita (PAGADA, CANCELADA, ATENDIDA)
    @Enumerated(EnumType.STRING)
    private EstadoDeCita estado;

    /**
     * Médico asignado a la cita.
     */
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    /**
     * Usuario (paciente) que reserva la cita.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    /**
     * Pago asociado a la cita.
     */
    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL)
    private Pago pago;

    /**
     * Relación entre el médico y la especialidad asociada
     * a esta cita
     */
    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL)
    private MedicoEspecialidad medicoEspecialidad;
}

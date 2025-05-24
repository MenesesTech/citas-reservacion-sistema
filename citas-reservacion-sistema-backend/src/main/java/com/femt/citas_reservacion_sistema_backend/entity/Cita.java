package com.femt.citas_reservacion_sistema_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "citas")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Estado de la cita (PAGADA, EN PROCESO, ATENDIDA)
    private String estado;

    /**
     * Fecha y hora reservada con relación indirecta al médico.
     */
    @ManyToOne
    @JoinColumn(name = "fecha_hora_id", nullable = false)
    private FechaHora fechaHora;

    /**
     * Paciente que reserva la cita.
     */
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    /**
     * Pago asociado a la cita.
     */
    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL)
    private Pago pago;
}

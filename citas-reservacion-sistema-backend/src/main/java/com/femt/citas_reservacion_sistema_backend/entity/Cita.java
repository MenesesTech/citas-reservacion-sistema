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
    private Long id;

    private LocalDate fecha;
    private LocalTime hora;
    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    private EstadoDeCita estado;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tipo_paciente_id")
    private TipoPaciente tipoPaciente;

    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL)
    private Pago pago;
}


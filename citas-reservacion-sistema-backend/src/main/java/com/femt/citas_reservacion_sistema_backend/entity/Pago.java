package com.femt.citas_reservacion_sistema_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pagos")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Identificador de la transacción proporcionado por la pasarela de pagos
    private String idTransaccionPasarela;
    // Nombre o identificador del proveedor de pasarela de pago
    private String proveedor_pasarela;
    //  Fecha y hora exactas en que se realizó el pago
    private LocalDateTime fechaPago = LocalDateTime.now();
    /**
     * Relación uno-a-uno con la entidad Cita
     * Cada pago está vinculado a una única cita médica
     */
    @OneToOne
    @JoinColumn(name = "cita_id")
    private Cita cita;
}

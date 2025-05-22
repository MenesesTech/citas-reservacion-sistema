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

    private BigDecimal monto;
    private String idTransaccionPasarela;
    private String proveedor_pasarela;
    private LocalDateTime fechaPago = LocalDateTime.now();

    @OneToOne
    @JoinColumn(name = "cita_id")
    private Cita cita;
}

package com.femt.citas_reservacion_sistema_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medicos")
public class Medico {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String correo;
    private String celular;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sede_id")
    private Sede sede;

    @OneToMany(mappedBy = "medico")
    private List<Especialidad> especialidad;

    @OneToOne(mappedBy = "medico", cascade = CascadeType.ALL)
    private FechaHora fechaHora;

    @OneToMany(mappedBy = "medico")
    private List<Cita> citas;
}

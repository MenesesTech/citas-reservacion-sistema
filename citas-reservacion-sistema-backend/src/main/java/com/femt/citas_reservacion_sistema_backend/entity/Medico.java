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
    /**
     * Identificador único del médico
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Nombre del médico
    private String nombre;
    // Apellidos del médico
    private String apellido;
    // Correo del médico
    private String correo;
    // Teléfono del médico
    private String celular;
    // Especialidad del medico
    @ManyToOne
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;

    /**
     * Sede en la que el médico está asignado
     * Relación de muchos-a-uno con la entidad Sede
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sede_id")
    private Sede sede;

    /**
     * Disponibilidad del médico
     * Relación de uno-a-muchos con la entidad FechaHora
     */
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<FechaHora> horariosDisponibles;

    /**
     * Lista de citas asociadas a este medico
     * Relacion uno-a-muchos con la entidad Citas
     */
    @OneToMany(mappedBy = "medico")
    private List<Cita> citas;
}

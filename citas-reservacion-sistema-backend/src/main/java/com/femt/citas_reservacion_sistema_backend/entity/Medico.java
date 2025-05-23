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

    /**
     * Sede en la que el médico está asignado
     * Relación de muchos-a-uno con la entidad Sede
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sede_id")
    private Sede sede;

    /**
     * Lista de especialidades que posee el medico
     * Relación muchos-a-muchos con la entidad Especialidades
     */
    @ManyToMany
    @JoinTable(name = "medico_especialidad", joinColumns = @JoinColumn(name = "medico_id"), inverseJoinColumns = @JoinColumn(name = "especialidad_id"))
    private List<Especialidad> especialidades;

    /**
     * Disponibilidad del médico
     * Relación de uno-a-uno con la entidad FechaHora
     */
    @OneToOne(mappedBy = "medico", cascade = CascadeType.ALL)
    private FechaHora fechaHora;

    /**
     * Lista de citas asociadas a este medico
     * Relacion uno-a-muchos con la entidad Citas
     */
    @OneToMany(mappedBy = "medico")
    private List<Cita> citas;
}

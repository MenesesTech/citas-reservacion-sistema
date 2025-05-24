package com.femt.citas_reservacion_sistema_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "especialidades")
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de la especialidad
    @Column(unique = false)
    private String nombre;

    /**
     * Relación uno-a-muchos con la entidad MedicoEspecialidad.
     * Esta tabla intermedia permite asociar múltiples médicos
     * con múltiples especialidades.
     */
    @OneToMany(mappedBy = "especialidad", cascade = CascadeType.ALL)
    private List<MedicoEspecialidad> medicoEspecialidad;

    public Especialidad(Long id) {
        this.id = id;
    }

}

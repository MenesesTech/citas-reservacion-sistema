package com.femt.citas_reservacion_sistema_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dni", unique = true, nullable = false)
    private String dni;
    private String password;
    private String nombres;
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    private String celular;
    private String correo;
    private String genero;
    private String departamento;
    private String provincia;
    private String distrito;
    private String direccion;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Cita> citas;
}

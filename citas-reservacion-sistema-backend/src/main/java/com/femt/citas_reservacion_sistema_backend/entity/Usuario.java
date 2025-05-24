package com.femt.citas_reservacion_sistema_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 15)
    private String dni;

    @Column(nullable = false)
    private String password;

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    private LocalDate fechaNacimiento;
    private String telefono;

    @Column(nullable = false, unique = true)
    private String email;

    private String genero;
    private String departamento;
    private String provincia;
    private String distrito;
    private String direccion;

    private LocalDate created = LocalDate.now();

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Paciente paciente;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Medico medico;
}

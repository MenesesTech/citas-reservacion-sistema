package com.femt.citas_reservacion_sistema_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {
    private Long id;
    private String dni;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String email;
    private String genero;
    private String departamento;
    private String provincia;
    private String distrito;
    private String direccion;
    private String tipoUsuario;
    private LocalDate created;
}

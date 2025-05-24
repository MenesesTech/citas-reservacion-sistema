package com.femt.citas_reservacion_sistema_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaResponseDTO {
    private Long id;
    private String estado;

    // Info de fecha y hora
    private Long fechaHoraId;
    private String fecha;
    private String hora;
    private Boolean disponible;

    // Info del paciente
    private Long pacienteId;
    private String nombrePaciente;
    private String dniPaciente;

    // Info del pago
    private Long pagoId;
    private String proveedorPasarela;
    private String idTransaccionPasarela;
}

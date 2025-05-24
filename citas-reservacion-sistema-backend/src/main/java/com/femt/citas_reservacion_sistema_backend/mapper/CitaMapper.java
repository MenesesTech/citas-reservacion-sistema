package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.request.CitaRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.CitaResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Cita;
import com.femt.citas_reservacion_sistema_backend.entity.FechaHora;
import com.femt.citas_reservacion_sistema_backend.entity.Paciente;
import com.femt.citas_reservacion_sistema_backend.entity.Pago;
import org.springframework.stereotype.Component;

@Component
public class CitaMapper {

    public CitaResponseDTO toDTO(Cita cita) {
        CitaResponseDTO dto = new CitaResponseDTO();
        dto.setId(cita.getId());
        dto.setEstado(cita.getEstado());

        // FechaHora
        FechaHora fh = cita.getFechaHora();
        // No esten vacios
        if (fh != null) {
            dto.setFechaHoraId(fh.getId());
            dto.setFecha(fh.getFecha().toString());
            dto.setHora(fh.getHora().toString());
            dto.setDisponible(fh.getDisponible());
        }

        // Paciente
        Paciente paciente = cita.getPaciente();
        if (paciente != null && paciente.getUsuario() != null) {
            dto.setPacienteId(paciente.getId());
            dto.setNombrePaciente(paciente.getUsuario().getNombre() + " " +
                    paciente.getUsuario().getApellidoPaterno());
            dto.setDniPaciente(paciente.getUsuario().getDni());
        }

        // Pago
        Pago pago = cita.getPago();
        if (pago != null) {
            dto.setPagoId(pago.getId());
            dto.setProveedorPasarela(pago.getProveedor_pasarela());
            dto.setIdTransaccionPasarela(pago.getIdTransaccionPasarela());
        }

        return dto;
    }

    public Cita toEntity(CitaRequestDTO dto) {
        Cita cita = new Cita();
        cita.setEstado(dto.getEstado());

        // Relacionar por ID solamente. El controlador o servicio debe cargar las entidades completas.
        FechaHora fechaHora = new FechaHora();
        fechaHora.setId(dto.getFechaHoraId());
        cita.setFechaHora(fechaHora);

        Paciente paciente = new Paciente();
        paciente.setId(dto.getPacienteId());
        cita.setPaciente(paciente);

        return cita;
    }
}

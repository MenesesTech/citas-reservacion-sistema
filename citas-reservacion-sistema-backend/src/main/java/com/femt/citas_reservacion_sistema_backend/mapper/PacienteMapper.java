package com.femt.citas_reservacion_sistema_backend.mapper;
import com.femt.citas_reservacion_sistema_backend.dto.request.PacienteRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.PacienteResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Paciente;
import com.femt.citas_reservacion_sistema_backend.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PacienteMapper {
    @Autowired
    private ModelMapper modelMapper;

    public PacienteResponseDTO toResponseDTO(Paciente paciente) {
        PacienteResponseDTO dto = new PacienteResponseDTO();
        dto.setId(paciente.getId());
        dto.setUsuarioId(paciente.getUsuario().getId());
        dto.setNombreCompleto(paciente.getUsuario().getNombre() + " " + paciente.getUsuario().getApellidoPaterno());
        dto.setDni(paciente.getUsuario().getDni());
        return dto;
    }

    public Paciente toEntity(PacienteRequestDTO dto) {
        Paciente paciente = new Paciente();
        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId());
        paciente.setUsuario(usuario);
        return paciente;
    }
}

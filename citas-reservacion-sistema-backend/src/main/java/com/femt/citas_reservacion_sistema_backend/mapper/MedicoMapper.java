package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.request.MedicoRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.MedicoResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Especialidad;
import com.femt.citas_reservacion_sistema_backend.entity.Medico;
import com.femt.citas_reservacion_sistema_backend.entity.Sede;
import com.femt.citas_reservacion_sistema_backend.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicoMapper {
    @Autowired
    private ModelMapper modelMapper;

    public MedicoResponseDTO toResponseDTO(Medico medico) {
        MedicoResponseDTO dto = new MedicoResponseDTO();
        dto.setId(medico.getId());
        dto.setUsuarioId(medico.getUsuario().getId());
        dto.setNombreCompleto(medico.getUsuario().getNombre() + " " + medico.getUsuario().getApellidoPaterno());
        dto.setEspecialidadId(medico.getEspecialidad().getId());
        dto.setNombreEspecialidad(medico.getEspecialidad().getNombre());
        dto.setSedeId(medico.getSede().getId());
        dto.setNombreSede(medico.getSede().getNombre());
        return dto;
    }

    public Medico toEntity(MedicoRequestDTO dto) {
        Medico medico = new Medico();
        Usuario usuario = new Usuario(); usuario.setId(dto.getUsuarioId());
        Especialidad esp = new Especialidad(); esp.setId(dto.getEspecialidadId());
        Sede sede = new Sede(); sede.setId(dto.getSedeId());
        medico.setUsuario(usuario);
        medico.setEspecialidad(esp);
        medico.setSede(sede);
        return medico;
    }
}


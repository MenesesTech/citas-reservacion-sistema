package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.CitaDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Cita;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    @Mapping(source = "medico.nombre", target = "nombreMedico")
    @Mapping(source = "medico.apellido", target = "apellidoMedico")
    @Mapping(source = "usuario.nombres", target = "apellidoPaciente")
    @Mapping(source = "medicoEspecialidad.especialidad.nombre", target = "especialidad")
    CitaDTO toDTO(Cita cita);

    @Mapping(target = "medico", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "medicoEspecialidad", ignore = true)
    @Mapping(target = "pago", ignore = true)
    Cita toEntity(CitaDTO dto);
}


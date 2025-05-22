package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.CitaDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Cita;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CitaMapper {
    CitaDTO toDTO(Cita cita);

    Cita toEntity(CitaDTO dto);

    @AfterMapping
    default void afterMapping(Cita cita, @MappingTarget CitaDTO dto) {
        if (cita.getEspecialidad() != null) {
            dto.setEspecialidadNombre(cita.getEspecialidad().getNombre());
            dto.setEspecialidadId(String.valueOf(cita.getEspecialidad().getId()));
        }
    }
}

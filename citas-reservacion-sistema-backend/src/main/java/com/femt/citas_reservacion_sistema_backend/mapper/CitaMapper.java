package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.CitaDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Cita;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CitaMapper {
    CitaDTO toDTO(Cita cita);
    Cita toEntity(CitaDTO dto);
}

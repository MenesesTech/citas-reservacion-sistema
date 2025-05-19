package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.HorarioDisponibleDTO;
import com.femt.citas_reservacion_sistema_backend.entity.HorarioDisponible;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HorarioDisponibleMapper {
    HorarioDisponibleDTO toDTO(HorarioDisponible horario);
    HorarioDisponible toEntity(HorarioDisponibleDTO dto);
}

package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.FechaHoraDTO;
import com.femt.citas_reservacion_sistema_backend.entity.FechaHora;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FechaHoraMapper {
    FechaHoraDTO toDTO(FechaHora horario);
    FechaHora toEntity(FechaHoraDTO dto);
}

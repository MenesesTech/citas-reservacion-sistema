package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.SedeDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Sede;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SedeMapper {
    SedeDTO toDTO(Sede sede);
    Sede toEntity(SedeDTO dto);
}
package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.FechaHoraDTO;
import com.femt.citas_reservacion_sistema_backend.entity.FechaHora;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FechaHoraMapper {
    @Mapping(source = "medico.nombre", target = "nombreMedico")
    @Mapping(source = "medico.apellido", target = "apellidoMedico")
    FechaHoraDTO toDTO(FechaHora horario);

    @Mapping(target = "medico", ignore = true)
    FechaHora toEntity(FechaHoraDTO dto);
}

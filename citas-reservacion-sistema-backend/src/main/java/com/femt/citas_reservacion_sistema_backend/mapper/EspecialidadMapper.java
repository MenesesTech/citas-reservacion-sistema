package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.EspecialidadDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Especialidad;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EspecialidadMapper {
    EspecialidadDTO toDTO(Especialidad especialidad);
    Especialidad toEntity(EspecialidadDTO dto);
}

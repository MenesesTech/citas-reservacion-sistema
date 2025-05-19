package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.MedicoEspecialidadDTO;
import com.femt.citas_reservacion_sistema_backend.entity.MedicoEspecialidad;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicoEspecialidadMapper {
    MedicoEspecialidadDTO toDTO(MedicoEspecialidad me);
    MedicoEspecialidad toEntity(MedicoEspecialidadDTO dto);
}

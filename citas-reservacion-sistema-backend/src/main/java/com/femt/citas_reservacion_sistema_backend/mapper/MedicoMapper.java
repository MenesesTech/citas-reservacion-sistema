package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.MedicoDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Medico;

import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface MedicoMapper {
    MedicoDTO toDTO(Medico medico);
    Medico toEntity(MedicoDTO dto);
}

package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.TipoPacienteDTO;
import com.femt.citas_reservacion_sistema_backend.entity.TipoPaciente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoPacienteMapper {
    TipoPacienteDTO toDTO(TipoPaciente tipo);
    TipoPaciente toEntity(TipoPacienteDTO dto);
}

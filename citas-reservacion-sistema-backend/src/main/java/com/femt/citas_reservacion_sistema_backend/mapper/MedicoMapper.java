package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.MedicoDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Medico;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring", uses = EspecialidadMapper.class)
public interface MedicoMapper {
    MedicoDTO toDTO(Medico medico);

    Medico toEntity(MedicoDTO dto);

    List<MedicoDTO> toDTOList(List<Medico> medicos);
}

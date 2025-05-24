package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.MedicoDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Medico;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface MedicoMapper {
    @Mapping(target = "especialidades", source = "medicoEspecialidades")
    @Mapping(target = "fechaDisponible", ignore = true)
    @Mapping(target = "horaDisponible", ignore = true)
    @Mapping(target = "disponible", ignore = true)
    MedicoDTO toDTO(Medico medico);

    @Mapping(target = "medicoEspecialidades", ignore = true)
    @Mapping(target = "horariosDisponibles", ignore = true)
    @Mapping(target = "citas", ignore = true)
    Medico toEntity(MedicoDTO dto);
}

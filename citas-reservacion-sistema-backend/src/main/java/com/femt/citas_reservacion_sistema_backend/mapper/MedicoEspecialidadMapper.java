package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.MedicoEspecialidadDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Especialidad;
import com.femt.citas_reservacion_sistema_backend.entity.Medico;
import com.femt.citas_reservacion_sistema_backend.entity.MedicoEspecialidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "Spring")
public interface MedicoEspecialidadMapper {
    @Mapping(target = "medicoId", source = "medico", qualifiedByName = "mapMedicoToId")
    @Mapping(target = "especialidadId", source = "especialidad", qualifiedByName = "mapEspecialidadToId")
    MedicoEspecialidadDTO toDTO(MedicoEspecialidad medicoEspecialidad);
    @Mapping(target = "medico", ignore = true)
    @Mapping(target = "especialidad", ignore = true)
    @Mapping(target = "cita", ignore = true)
    MedicoEspecialidad toEntity(MedicoEspecialidadDTO medicoEspecialidadDTO);
    @Named("mapMedicoToId")
    static Long mapMedicoToId(Medico medico) {
        return medico != null ? medico.getId() : null;
    }
    @Named("mapEspecialidadToId")
    static Long mapEspecialidadToId(Especialidad especialidad) {
        return especialidad != null ? especialidad.getId() : null;
    }
}

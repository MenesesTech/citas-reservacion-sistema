package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.request.EspecialidadRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.EspecialidadResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Especialidad;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EspecialidadMapper {
    @Autowired
    private ModelMapper modelMapper;

    public EspecialidadResponseDTO toResponseDTO(Especialidad especialidad) {
        return modelMapper.map(especialidad, EspecialidadResponseDTO.class);
    }

    public Especialidad toEntity(EspecialidadRequestDTO dto) {
        return modelMapper.map(dto, Especialidad.class);
    }

    public List<EspecialidadResponseDTO> toResponseDTOList(List<Especialidad> especialidades) {
        return especialidades.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
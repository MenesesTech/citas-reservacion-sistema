package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.request.SedeRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.SedeResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Sede;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SedeMapper {
    @Autowired
    private ModelMapper modelMapper;

    public SedeResponseDTO toResponseDTO(Sede sede){
        return modelMapper.map(sede, SedeResponseDTO.class);
    }
    public List<SedeResponseDTO> toResponseList(List<Sede> sedes){
        return sedes.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Sede toEntity(SedeRequestDTO dto){
        return modelMapper.map(dto, Sede.class);
    }
}

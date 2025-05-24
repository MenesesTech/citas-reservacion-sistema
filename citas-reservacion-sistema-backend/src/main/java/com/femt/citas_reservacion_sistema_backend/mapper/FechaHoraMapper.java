package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.FechaHoraRequestDTO;
import com.femt.citas_reservacion_sistema_backend.entity.FechaHora;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FechaHoraMapper {
    @Autowired
    private ModelMapper modelMapper;

    public FechaHoraRequestDTO toDTO(FechaHora fechaHora) {
        return modelMapper.map(fechaHora, FechaHoraRequestDTO.class);
    }

    public List<FechaHoraRequestDTO> toDTOList(List<FechaHora> fechaHoras) {
        return fechaHoras.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public FechaHora toEntity(FechaHoraRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, FechaHora.class);
    }
}
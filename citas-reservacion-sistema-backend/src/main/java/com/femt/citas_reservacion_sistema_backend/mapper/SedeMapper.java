package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.SedeDTO;
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

    public SedeDTO toDTo(Sede sede){
        return modelMapper.map(sede, SedeDTO.class);
    }

    public List<SedeDTO> toDoList(List<Sede> sedes){
        return sedes.stream()
                .map(this::toDTo)
                .collect(Collectors.toList());
    }

    public Sede toEntity(SedeDTO sedeDTO){
        return modelMapper.map(sedeDTO, Sede.class);
    }
}

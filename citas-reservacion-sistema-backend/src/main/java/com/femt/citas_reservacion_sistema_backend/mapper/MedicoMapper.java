package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.MedicoRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.MedicoResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Medico;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicoMapper {
    @Autowired
    private ModelMapper modelMapper;

    public MedicoResponseDTO toDto(Medico medico){
        return modelMapper.map(medico,MedicoResponseDTO.class);
    }

    public List<MedicoResponseDTO> toDoList(List<Medico> medicos){
        return medicos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Medico toEntity(MedicoRequestDTO medicoRequestDTO) {
        return modelMapper.map(medicoRequestDTO, Medico.class);
    }
}

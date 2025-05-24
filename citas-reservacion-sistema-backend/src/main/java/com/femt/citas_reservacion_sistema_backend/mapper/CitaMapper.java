package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.CitaRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.CitaResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Cita;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CitaMapper {
    @Autowired
    private ModelMapper modelMapper;

    public CitaResponseDTO toResponseDTO(Cita cita) {
        CitaResponseDTO responseDTO = new CitaResponseDTO();
        responseDTO.setEspecialidadMedico(cita.getMedico().getEspecialidad().getNombre());
        responseDTO.setNombreMedico(cita.getMedico().getNombre() + " " + cita.getMedico().getApellido());
        responseDTO.setTipoPaciente(cita.getTipoPaciente());
        responseDTO.setMonto(cita.getMedico().getEspecialidad().getMonto());
        responseDTO.setEstado(cita.getEstado());
        // Nota: fecha y hora deben obtenerse de la relación con FechaHora
        return responseDTO;
    }

    public List<CitaResponseDTO> toResponseDTOList(List<Cita> citas) {
        return citas.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Cita toEntity(CitaRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, Cita.class);
    }
}
package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.request.FechaHoraRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.FechaHoraResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.FechaHora;
import com.femt.citas_reservacion_sistema_backend.entity.Medico;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FechaHoraMapper {
    @Autowired
    private ModelMapper modelMapper;

    public FechaHoraResponseDTO toResponseDTO(FechaHora fh) {
        FechaHoraResponseDTO dto = modelMapper.map(fh, FechaHoraResponseDTO.class);
        dto.setMedicoId(fh.getMedico().getId());
        dto.setNombreMedico(fh.getMedico().getUsuario().getNombre() + " " + fh.getMedico().getUsuario().getApellidoPaterno());
        return dto;
    }

    public FechaHora toEntity(FechaHoraRequestDTO dto) {
        FechaHora fh = new FechaHora();
        fh.setFecha(dto.getFecha());
        fh.setHora(dto.getHora());
        Medico m = new Medico();
        m.setId(dto.getMedicoId());
        fh.setMedico(m);
        return fh;
    }
}
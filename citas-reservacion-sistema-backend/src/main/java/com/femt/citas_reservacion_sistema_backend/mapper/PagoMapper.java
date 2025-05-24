package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.PagoRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.PagoResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Pago;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PagoMapper {
    @Autowired
    private ModelMapper modelMapper;

    public PagoResponseDTO toResponseDTO(Pago pago) {
        return modelMapper.map(pago, PagoResponseDTO.class);
    }

    public List<PagoResponseDTO> toResponseDTOList(List<Pago> pagos) {
        return pagos.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Pago toEntity(PagoRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, Pago.class);
    }
}
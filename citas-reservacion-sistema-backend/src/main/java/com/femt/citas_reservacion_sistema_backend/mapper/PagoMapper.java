package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.request.PagoRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.PagoResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Cita;
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
        PagoResponseDTO dto = modelMapper.map(pago, PagoResponseDTO.class);
        dto.setCitaId(pago.getCita().getId());
        return dto;
    }

    public Pago toEntity(PagoRequestDTO dto) {
        Pago pago = new Pago();
        pago.setIdTransaccionPasarela(dto.getIdTransaccionPasarela());
        pago.setProveedor_pasarela(dto.getProveedorPasarela());
        pago.setFechaPago(dto.getFechaPago());
        Cita cita = new Cita(); cita.setId(dto.getCitaId());
        pago.setCita(cita);
        return pago;
    }
    public List<PagoResponseDTO> toResponseDTOList(List<Pago> pagos) {
        return pagos.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
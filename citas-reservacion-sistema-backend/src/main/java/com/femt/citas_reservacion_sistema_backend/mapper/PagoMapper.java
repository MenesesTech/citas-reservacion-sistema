package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.PagoDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Pago;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PagoMapper {
    PagoDTO toDTO(Pago pago);
    Pago toEntity(PagoDTO dto);
}

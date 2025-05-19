package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.UsuarioDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDTO toDTO(Usuario usuario);
    Usuario toEntity(UsuarioDTO dto);
}

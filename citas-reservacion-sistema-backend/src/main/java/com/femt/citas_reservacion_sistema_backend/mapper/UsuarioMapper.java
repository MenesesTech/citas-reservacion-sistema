package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.request.UsuarioRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.UsuarioResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UsuarioMapper {
    @Autowired
    private ModelMapper modelMapper;

    public UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

    public Usuario toEntity(UsuarioRequestDTO dto) {
        return modelMapper.map(dto, Usuario.class);
    }
}

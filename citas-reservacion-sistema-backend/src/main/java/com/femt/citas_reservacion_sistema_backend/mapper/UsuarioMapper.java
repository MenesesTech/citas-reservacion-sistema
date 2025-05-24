package com.femt.citas_reservacion_sistema_backend.mapper;

import com.femt.citas_reservacion_sistema_backend.dto.UsuarioRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.UsuarioResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {
    @Autowired
    private ModelMapper modelMapper;

    public UsuarioResponseDTO toDto(Usuario usuario){
        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

    public List<UsuarioResponseDTO> toDoList(List<Usuario> usuarios){
        return usuarios.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Usuario toEntity(UsuarioRequestDTO usuarioRequestDTO){
        return modelMapper.map(usuarioRequestDTO, Usuario.class);
    }
}

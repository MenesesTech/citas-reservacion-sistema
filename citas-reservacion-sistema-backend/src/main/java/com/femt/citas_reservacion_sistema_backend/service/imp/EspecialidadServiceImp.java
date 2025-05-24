package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.mapper.EspecialidadMapper;
import com.femt.citas_reservacion_sistema_backend.repository.EspecialidadRepository;
import com.femt.citas_reservacion_sistema_backend.service.EspecialidadService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EspecialidadServiceImp implements EspecialidadService {

    private final EspecialidadRepository especialidadRepository;
    private final EspecialidadMapper especialidadMapper;

    public EspecialidadServiceImp(EspecialidadRepository especialidadRepository, EspecialidadMapper especialidadMapper) {
        this.especialidadRepository = especialidadRepository;
        this.especialidadMapper = especialidadMapper;
    }

    @Override
    public List<EspecialidadDTO> listarEspecialidades() throws Exception {
        return this.especialidadRepository.findAll().stream()
                .map(especialidadMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<EspecialidadDTO> obtenerEspecialidadPorId(Long idEspecialidad) throws Exception {
        return this.especialidadRepository.findById(idEspecialidad)
                .map(especialidadMapper::toDTO);
    }

    @Override
    public EspecialidadDTO guardarEspecialidad(EspecialidadDTO especialidadDTO) throws Exception {
        return especialidadMapper.toDTO(especialidadRepository.save(especialidadMapper.toEntity(especialidadDTO)));
    }

    @Override
    public void eliminarEspecialidad(Long id) throws Exception {
        this.especialidadRepository.deleteById(id);
    }

    @Override
    public EspecialidadDTO actualizarEspecialidad(EspecialidadDTO especialidadDTO) throws Exception {
        return especialidadMapper.toDTO(especialidadRepository.save(especialidadMapper.toEntity(especialidadDTO)));
    }
}

package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.dto.EspecialidadRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.EspecialidadResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Especialidad;
import com.femt.citas_reservacion_sistema_backend.mapper.EspecialidadMapper;
import com.femt.citas_reservacion_sistema_backend.repository.EspecialidadRepository;
import com.femt.citas_reservacion_sistema_backend.service.EspecialidadService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadServiceImp implements EspecialidadService {

    private final EspecialidadRepository especialidadRepository;
    private final EspecialidadMapper especialidadMapper;

    public EspecialidadServiceImp(EspecialidadRepository especialidadRepository, EspecialidadMapper especialidadMapper) {
        this.especialidadRepository = especialidadRepository;
        this.especialidadMapper = especialidadMapper;
    }

    @Override
    public List<EspecialidadResponseDTO> listarEspecialidades() throws Exception {
        return especialidadMapper.toResponseDTOList(especialidadRepository.findAll());
    }

    @Override
    public Optional<EspecialidadResponseDTO> obtenerEspecialidadPorId(Long idEspecialidad) throws Exception {
        return especialidadRepository.findById(idEspecialidad)
                .map(especialidadMapper::toResponseDTO);
    }

    @Override
    public EspecialidadResponseDTO guardarEspecialidad(EspecialidadRequestDTO especialidadRequest) throws Exception {
        Especialidad especialidad = especialidadMapper.toEntity(especialidadRequest);
        Especialidad especialidadGuardada = especialidadRepository.save(especialidad);
        return especialidadMapper.toResponseDTO(especialidadGuardada);
    }

    @Override
    public void eliminarEspecialidad(Long id) throws Exception {
        if (!especialidadRepository.existsById(id)) {
            throw new Exception("Especialidad no encontrada con ID: " + id);
        }
        especialidadRepository.deleteById(id);
    }

    @Override
    public EspecialidadResponseDTO actualizarEspecialidad(EspecialidadRequestDTO especialidadRequest) throws Exception {
        // Buscar la especialidad existente (necesitaríamos el ID en el DTO)
        Especialidad especialidad = especialidadMapper.toEntity(especialidadRequest);
        Especialidad especialidadActualizada = especialidadRepository.save(especialidad);
        return especialidadMapper.toResponseDTO(especialidadActualizada);
    }
}
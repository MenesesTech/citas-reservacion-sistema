package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.dto.EspecialidadDTO;
import com.femt.citas_reservacion_sistema_backend.dto.MedicoDTO;
import com.femt.citas_reservacion_sistema_backend.mapper.EspecialidadMapper;
import com.femt.citas_reservacion_sistema_backend.repository.EspecialidadRepository;
import com.femt.citas_reservacion_sistema_backend.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EspecialidadServiceImp implements EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;
    @Autowired
    private EspecialidadMapper especialidadMapper;

    @Override
    public List<EspecialidadDTO> listaEspecialidad() throws Exception {
        return this.especialidadRepository.findAll().stream()
                .map(especialidadMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<EspecialidadDTO> obtenerEspecialidadPorId(Long id) throws Exception {
        return especialidadRepository.findById(id).map(especialidadMapper::toDTO);
    }

    @Override
    public List<EspecialidadDTO> listarEspecialidadPorMedico(MedicoDTO medicoDTO) {
        return especialidadRepository.findByMedicoId(medicoDTO.getId())
                .stream()
                .map(especialidadMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void guardarEspecialidad(EspecialidadDTO especialidadRequest) throws Exception {
        try {
            this.especialidadRepository.save(especialidadMapper.toEntity(especialidadRequest));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void eliminarEspecialidad(Long id) throws Exception {
        this.especialidadRepository.deleteById(id);
    }

    @Override
    public void actualizarEspecialidad(EspecialidadDTO especialidadRequest) throws Exception {
        try {
            this.especialidadRepository.save(especialidadMapper.toEntity(especialidadRequest));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.dto.MedicoDTO;
import com.femt.citas_reservacion_sistema_backend.mapper.MedicoMapper;
import com.femt.citas_reservacion_sistema_backend.repository.MedicoRepository;
import com.femt.citas_reservacion_sistema_backend.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicoServiceImp implements MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private MedicoMapper medicoMapper;

    @Override
    public List<MedicoDTO> obtenerMedicosPorEspecialidad(Long especialidadId) {
        return medicoMapper.toDTOList(medicoRepository.findByEspecialidadId(especialidadId));
    }

    @Override
    public List<MedicoDTO> listaMedicos() throws Exception {
        return medicoRepository.findAll().stream()
                .map(medicoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MedicoDTO> obtenerMedicoPorId(Long id) throws Exception {
        return medicoRepository.findById(id).map(medicoMapper::toDTO);
    }

    @Override
    public void guardarMedico(MedicoDTO medicoRequest) throws Exception {
        try {
            this.medicoRepository.save(medicoMapper.toEntity(medicoRequest));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void eliminarMedico(Long id) throws Exception {
        try {
            this.medicoRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("No se puede eliminar al medico: " + e.getMessage());
        }
    }

    @Override
    public void actualizarMedico(MedicoDTO medicoRequest) throws Exception {
        try {
            this.medicoRepository.save(medicoMapper.toEntity(medicoRequest));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

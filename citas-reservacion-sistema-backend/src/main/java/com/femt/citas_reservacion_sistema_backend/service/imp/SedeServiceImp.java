package com.femt.citas_reservacion_sistema_backend.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.femt.citas_reservacion_sistema_backend.dto.MedicoDTO;
import com.femt.citas_reservacion_sistema_backend.dto.SedeDTO;
import com.femt.citas_reservacion_sistema_backend.mapper.SedeMapper;
import com.femt.citas_reservacion_sistema_backend.repository.SedeRepository;
import com.femt.citas_reservacion_sistema_backend.service.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SedeServiceImp implements SedeService {

    @Autowired
    private SedeRepository sedeRepository;
    @Autowired
    private SedeMapper sedeMapper;

    @Override
    public List<SedeDTO> listaSedes() throws Exception {
        return sedeRepository.findAll().stream()
                .map(sedeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SedeDTO> obtenerSedePorId(Long id) throws Exception {
        return sedeRepository.findById(id).map(sedeMapper::toDTO);
    }

    @Override
    public void guardarSede(SedeDTO sedeRequest) throws Exception {
        try {
            sedeRepository.save(sedeMapper.toEntity(sedeRequest));
        } catch (Exception e) {
            throw new Exception("Error al guardar sede: " + e.getMessage());
        }
    }

    @Override
    public void eliminarSede(Long id) throws Exception {
        try {
            sedeRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Error al eliminar sede: " + e.getMessage());
        }
    }

    @Override
    public void actualizarSede(SedeDTO sedeRequest) throws Exception {
        try {
            sedeRepository.save(sedeMapper.toEntity(sedeRequest));
        } catch (Exception e) {
            throw new Exception("Error al actualizar sede: " + e.getMessage());
        }
    }

    @Override
    public List<SedeDTO> listarSedesPorMedico(MedicoDTO medicoDTO) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarSedesPorMedico'");
    }

}

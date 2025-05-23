package com.femt.citas_reservacion_sistema_backend.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.femt.citas_reservacion_sistema_backend.dto.SedeDTO;
import com.femt.citas_reservacion_sistema_backend.mapper.SedeMapper;
import com.femt.citas_reservacion_sistema_backend.repository.SedeRepository;
import com.femt.citas_reservacion_sistema_backend.service.SedeService;

@Service
public class SedeServiceImp implements SedeService {

    private final SedeRepository sedeRepository;
    private final SedeMapper sedeMapper;

    public SedeServiceImp(SedeRepository sedeRepository, SedeMapper sedeMapper) {
        this.sedeRepository = sedeRepository;
        this.sedeMapper = sedeMapper;
    }

    @Override
    public List<SedeDTO> listaSedes() throws Exception {
        return sedeRepository.findAll().stream()
                .map(sedeMapper::toDTO)
                .toList();
    }

    @Override
    public SedeDTO obtenerSedePorId(Long id) throws Exception {
        return sedeRepository.findById(id)
                .map(sedeMapper::toDTO)
                .orElseThrow(() -> new Exception("Sede no encontrada"));
    }

    @Override
    public void guardarSede(SedeDTO sedeRequest) throws Exception {
        this.sedeRepository.save(sedeMapper.toEntity(sedeRequest));
    }

    @Override
    public void eliminarSede(Long id) throws Exception {
        this.sedeRepository.deleteById(id);
    }

    @Override
    public void actualizarSede(SedeDTO sedeRequest) throws Exception {
        this.sedeRepository.save(sedeMapper.toEntity(sedeRequest));
    }

}

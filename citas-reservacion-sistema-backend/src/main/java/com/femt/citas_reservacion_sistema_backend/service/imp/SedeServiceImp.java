package com.femt.citas_reservacion_sistema_backend.service.imp;

import java.util.List;
import java.util.Optional;

import com.femt.citas_reservacion_sistema_backend.dto.SedeDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Sede;
import org.springframework.stereotype.Service;

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
        return sedeMapper.toDoList(sedeRepository.findAll());
    }

    @Override
    public Optional<SedeDTO> obtenerSedePorId(Long idSede) throws Exception {
        return sedeRepository.findById(idSede).map(sedeMapper::toDTo);
    }

    @Override
    public void guardarSede(SedeDTO sedeRequest) throws Exception {
        sedeRepository.save(sedeMapper.toEntity(sedeRequest));
    }

    @Override
    public void eliminarSede(Long id) throws Exception {
        sedeRepository.deleteById(id);
    }

    @Override
    public Sede actualizarSede(SedeDTO sedeRequest) throws Exception {
        Sede sede = sedeRepository.findById(sedeRequest.getId())
                .orElseThrow(() -> new RuntimeException("Sede no encontrada con id: " + sedeRequest.getId()));
        sede.setId(sedeRequest.getId());
        sede.setNombre(sedeRequest.getNombre());
        return sedeRepository.save(sede);
    }
}

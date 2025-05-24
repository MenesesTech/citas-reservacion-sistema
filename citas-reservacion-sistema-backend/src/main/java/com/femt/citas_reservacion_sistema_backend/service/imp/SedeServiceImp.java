package com.femt.citas_reservacion_sistema_backend.service.imp;

import java.util.List;
import java.util.Optional;

import com.femt.citas_reservacion_sistema_backend.dto.request.SedeRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.SedeResponseDTO;
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
    public List<SedeResponseDTO> listarSedes() {
        List<Sede> sedes = sedeRepository.findAll();
        return sedeMapper.toResponseList(sedes);
    }

    @Override
    public Optional<SedeResponseDTO> obtenerSedePorId(Long id) {
        return sedeRepository.findById(id)
                .map(sedeMapper::toResponseDTO);
    }

    @Override
    public void registrarSede(SedeRequestDTO dto) {
        Sede sede = sedeMapper.toEntity(dto);
        sedeRepository.save(sede);
    }

    @Override
    public void actualizarSede(Long id, SedeRequestDTO dto) throws Exception {
        Sede sede = sedeRepository.findById(id)
                .orElseThrow(() -> new Exception("Sede no encontrada"));
        sede.setNombre(dto.getNombre());
        sedeRepository.save(sede);
    }

    @Override
    public void eliminarSede(Long id) throws Exception {
        if (!sedeRepository.existsById(id)) {
            throw new Exception("Sede no encontrada");
        }
        sedeRepository.deleteById(id);
    }
}

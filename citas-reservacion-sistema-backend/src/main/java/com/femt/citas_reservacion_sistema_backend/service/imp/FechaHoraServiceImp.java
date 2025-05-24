package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.dto.FechaHoraDTO;
import com.femt.citas_reservacion_sistema_backend.mapper.FechaHoraMapper;
import com.femt.citas_reservacion_sistema_backend.repository.FechaHoraRepository;
import com.femt.citas_reservacion_sistema_backend.service.FechaHoraService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FechaHoraServiceImp implements FechaHoraService {

    private final FechaHoraRepository fechaHoraRepository;
    private final FechaHoraMapper fechaHoraMapper;

    public FechaHoraServiceImp(FechaHoraRepository fechaHoraRepository, FechaHoraMapper fechaHoraMapper) {
        this.fechaHoraRepository = fechaHoraRepository;
        this.fechaHoraMapper = fechaHoraMapper;
    }
    @Override
    public List<FechaHoraDTO> listaHorarios() throws Exception {
        return fechaHoraRepository.findAll().stream()
                .map(fechaHoraMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<FechaHoraDTO> obtenerHorarioPorId(Long id) throws Exception {
        return fechaHoraRepository.findById(id)
                .map(fechaHoraMapper::toDTO);
    }

    @Override
    public FechaHoraDTO guardarHorario(FechaHoraDTO horarioRequest) throws Exception {
        var horario = fechaHoraMapper.toEntity(horarioRequest);
        var horarioGuardado = fechaHoraRepository.save(horario);
        return fechaHoraMapper.toDTO(horarioGuardado);
    }

    @Override
    public void eliminarHorario(Long id) throws Exception {
        if (!fechaHoraRepository.existsById(id)) {
            throw new Exception("Horario no encontrado con ID: " + id);
        }
        fechaHoraRepository.deleteById(id);
    }
}

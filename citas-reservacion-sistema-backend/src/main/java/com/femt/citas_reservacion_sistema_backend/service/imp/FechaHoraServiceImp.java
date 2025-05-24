package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.dto.FechaHoraRequestDTO;
import com.femt.citas_reservacion_sistema_backend.entity.FechaHora;
import com.femt.citas_reservacion_sistema_backend.mapper.FechaHoraMapper;
import com.femt.citas_reservacion_sistema_backend.repository.FechaHoraRepository;
import com.femt.citas_reservacion_sistema_backend.service.FechaHoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class FechaHoraServiceImp implements FechaHoraService {

    @Autowired
    private FechaHoraRepository fechaHoraRepository;

    @Autowired
    private FechaHoraMapper fechaHoraMapper;

    @Override
    public List<FechaHoraRequestDTO> obtenerTodos() {
        return fechaHoraMapper.toDTOList(fechaHoraRepository.findAll());
    }

    @Override
    public List<FechaHoraRequestDTO> obtenerPorMedico(Long medicoId) {
        return fechaHoraMapper.toDTOList(fechaHoraRepository.findByMedicoId(medicoId));
    }

    @Override
    public List<FechaHoraRequestDTO> obtenerDisponiblesPorMedico(Long medicoId) {
        return fechaHoraMapper.toDTOList(fechaHoraRepository.findByMedicoIdAndDisponible(medicoId, true));
    }

    @Override
    public List<FechaHoraRequestDTO> obtenerPorMedicoYFecha(Long medicoId, LocalDate fecha) {
        return fechaHoraMapper.toDTOList(fechaHoraRepository.findByMedicoIdAndFecha(medicoId, fecha));
    }

    @Override
    public List<FechaHoraRequestDTO> obtenerDisponiblesDesde(Long medicoId, LocalDate fechaInicio) {
        return fechaHoraMapper.toDTOList(fechaHoraRepository.findHorariosDisponiblesDesde(medicoId, fechaInicio));
    }

    @Override
    public Optional<FechaHoraRequestDTO> obtenerPorMedicoFechaHora(Long medicoId, LocalDate fecha, String hora) {
        LocalTime time = LocalTime.parse(hora);
        return fechaHoraRepository.findByMedicoIdAndFechaAndHora(medicoId, fecha, time)
                .map(fechaHoraMapper::toDTO);
    }

    @Override
    public FechaHoraRequestDTO crearHorario(FechaHoraRequestDTO requestDTO) {
        FechaHora fechaHora = fechaHoraMapper.toEntity(requestDTO);
        return fechaHoraMapper.toDTO(fechaHoraRepository.save(fechaHora));
    }

    @Override
    public void eliminarHorario(Long id) {
        fechaHoraRepository.deleteById(id);
    }
}

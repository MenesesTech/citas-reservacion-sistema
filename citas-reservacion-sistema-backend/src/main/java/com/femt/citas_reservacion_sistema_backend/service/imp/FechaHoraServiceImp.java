package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.dto.request.FechaHoraRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.FechaHoraResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.FechaHora;
import com.femt.citas_reservacion_sistema_backend.entity.Medico;
import com.femt.citas_reservacion_sistema_backend.repository.FechaHoraRepository;
import com.femt.citas_reservacion_sistema_backend.repository.MedicoRepository;
import com.femt.citas_reservacion_sistema_backend.service.FechaHoraService;
import com.femt.citas_reservacion_sistema_backend.mapper.FechaHoraMapper;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FechaHoraServiceImp implements FechaHoraService {

    private final FechaHoraRepository fechaHoraRepository;
    private final MedicoRepository medicoRepository;
    private final FechaHoraMapper fechaHoraMapper;

    public FechaHoraServiceImp(FechaHoraRepository fechaHoraRepository,
                               MedicoRepository medicoRepository,
                               FechaHoraMapper fechaHoraMapper) {
        this.fechaHoraRepository = fechaHoraRepository;
        this.medicoRepository = medicoRepository;
        this.fechaHoraMapper = fechaHoraMapper;
    }

    @Override
    public List<FechaHoraResponseDTO> listarTodos() {
        List<FechaHora> fechasHoras = fechaHoraRepository.findAll();
        return fechasHoras.stream()
                .map(fechaHoraMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FechaHoraResponseDTO> obtenerPorId(Long id) throws Exception {
        FechaHora fh = fechaHoraRepository.findById(id)
                .orElseThrow(() -> new Exception("FechaHora no encontrada con ID: " + id));
        return Optional.of(fechaHoraMapper.toResponseDTO(fh));
    }

    @Override
    public void registrarFechaHora(FechaHoraRequestDTO dto) throws Exception {
        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new Exception("Médico no encontrado con ID: " + dto.getMedicoId()));

        FechaHora fh = fechaHoraMapper.toEntity(dto);
        fh.setMedico(medico);

        if (fh.getDisponible() == null) {
            fh.setDisponible(true);
        }

        fechaHoraRepository.save(fh);
    }

    @Override
    public void eliminarFechaHora(Long id) throws Exception {
        if (!fechaHoraRepository.existsById(id)) {
            throw new Exception("FechaHora no encontrada con ID: " + id);
        }
        fechaHoraRepository.deleteById(id);
    }

    @Override
    public FechaHoraResponseDTO actualizarFechaHora(Long id, FechaHoraRequestDTO dto) throws Exception {
        FechaHora fhExistente = fechaHoraRepository.findById(id)
                .orElseThrow(() -> new Exception("FechaHora no encontrada con ID: " + id));

        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new Exception("Médico no encontrado con ID: " + dto.getMedicoId()));

        fhExistente.setFecha(dto.getFecha());
        fhExistente.setHora(dto.getHora());
        fhExistente.setDisponible(dto.getDisponible());
        fhExistente.setMedico(medico);

        fechaHoraRepository.save(fhExistente);

        return fechaHoraMapper.toResponseDTO(fhExistente);
    }

    @Override
    public List<FechaHoraResponseDTO> listarDisponiblesPorMedico(Long medicoId) throws Exception {
        if (!medicoRepository.existsById(medicoId)) {
            throw new Exception("Médico no encontrado con ID: " + medicoId);
        }
        List<FechaHora> disponibles = fechaHoraRepository.findByMedicoIdAndDisponibleTrue(medicoId);
        return disponibles.stream()
                .map(fechaHoraMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FechaHoraResponseDTO> listarDisponiblesPorMedicoYFecha(Long medicoId, LocalDate fecha) throws Exception {
        if (!medicoRepository.existsById(medicoId)) {
            throw new Exception("Médico no encontrado con ID: " + medicoId);
        }
        List<FechaHora> disponibles = fechaHoraRepository.findByMedicoIdAndFechaAndDisponibleTrue(medicoId, fecha);
        return disponibles.stream()
                .map(fechaHoraMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}

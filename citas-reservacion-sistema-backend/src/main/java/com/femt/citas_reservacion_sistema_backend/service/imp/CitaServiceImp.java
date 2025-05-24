package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.dto.request.CitaRequestDTO;
import com.femt.citas_reservacion_sistema_backend.dto.response.CitaResponseDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Cita;
import com.femt.citas_reservacion_sistema_backend.entity.FechaHora;
import com.femt.citas_reservacion_sistema_backend.entity.Paciente;
import com.femt.citas_reservacion_sistema_backend.mapper.CitaMapper;
import com.femt.citas_reservacion_sistema_backend.repository.CitaRepository;
import com.femt.citas_reservacion_sistema_backend.repository.FechaHoraRepository;
import com.femt.citas_reservacion_sistema_backend.repository.PacienteRepository;
import com.femt.citas_reservacion_sistema_backend.service.CitaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaServiceImp implements CitaService {

    private final CitaRepository citaRepository;
    private final CitaMapper citaMapper;
    private final FechaHoraRepository fechaHoraRepository;
    private final PacienteRepository pacienteRepository;

    public CitaServiceImp(CitaRepository citaRepository,
                          CitaMapper citaMapper,
                          FechaHoraRepository fechaHoraRepository,
                          PacienteRepository pacienteRepository) {
        this.citaRepository = citaRepository;
        this.citaMapper = citaMapper;
        this.fechaHoraRepository = fechaHoraRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public List<CitaResponseDTO> listarCitas() {
        return citaRepository.findAll()
                .stream()
                .map(citaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CitaResponseDTO> obtenerCitaPorId(Long id) {
        return citaRepository.findById(id).map(citaMapper::toDTO);
    }

    @Override
    public void registrarCita(CitaRequestDTO citaRequest) throws Exception {
        Cita cita = new Cita();

        // Seteamos el estado
        cita.setEstado(citaRequest.getEstado());

        // Buscamos y seteamos fechaHora
        FechaHora fechaHora = fechaHoraRepository.findById(citaRequest.getFechaHoraId())
                .orElseThrow(() -> new Exception("FechaHora no encontrada"));
        cita.setFechaHora(fechaHora);

        // Buscamos y seteamos paciente
        Paciente paciente = pacienteRepository.findById(citaRequest.getPacienteId())
                .orElseThrow(() -> new Exception("Paciente no encontrado"));
        cita.setPaciente(paciente);

        // Guardamos la cita
        citaRepository.save(cita);
    }

    @Override
    public void eliminarCita(Long id) throws Exception {
        if (!citaRepository.existsById(id)) {
            throw new Exception("Cita no encontrada con ID: " + id);
        }
        citaRepository.deleteById(id);
    }

    @Override
    public Cita actualizarCita(CitaRequestDTO citaRequest) throws Exception {
        Cita cita = citaRepository.findById(citaRequest.getFechaHoraId())
                .orElseThrow(() -> new Exception("Cita no encontrada"));

        // Actualizamos campos
        cita.setEstado(citaRequest.getEstado());

        FechaHora fechaHora = fechaHoraRepository.findById(citaRequest.getFechaHoraId())
                .orElseThrow(() -> new Exception("FechaHora no encontrada"));
        cita.setFechaHora(fechaHora);

        Paciente paciente = pacienteRepository.findById(citaRequest.getPacienteId())
                .orElseThrow(() -> new Exception("Paciente no encontrado"));
        cita.setPaciente(paciente);

        return citaRepository.save(cita);
    }
}

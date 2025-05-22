package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.entity.FechaHora;
import com.femt.citas_reservacion_sistema_backend.repository.CitaRepository;
import com.femt.citas_reservacion_sistema_backend.repository.FechaHoraRepository;
import com.femt.citas_reservacion_sistema_backend.repository.MedicoRepository;
import com.femt.citas_reservacion_sistema_backend.repository.UsuarioRepository;
import com.femt.citas_reservacion_sistema_backend.service.CitaService;
import com.femt.citas_reservacion_sistema_backend.dto.CitaDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Cita;
import com.femt.citas_reservacion_sistema_backend.entity.EstadoDeCita;
import com.femt.citas_reservacion_sistema_backend.repository.TipoPacienteRepository;
import com.femt.citas_reservacion_sistema_backend.mapper.CitaMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitaServiceImp implements CitaService {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TipoPacienteRepository tipoPacienteRepository; // AGREGAR
    @Autowired
    private FechaHoraRepository fechaHoraRepository;
    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private CitaMapper citaMapper;

    @Override
    public void guardarCita(Long idMedico, Long idPaciente, Long idTipoPaciente, Long idFechaHora, Long idEspecialidad)
            throws Exception {
        var medico = medicoRepository.findById(idMedico)
                .orElseThrow(() -> new Exception("Medico no encontrado"));
        var usuario = usuarioRepository.findById(idPaciente)
                .orElseThrow(() -> new Exception("Paciente no encontrado"));
        var tipoPaciente = tipoPacienteRepository.findById(idTipoPaciente) // CORREGIR REPOSITORIO
                .orElseThrow(() -> new Exception("Tipo de paciente no encontrado"));
        var fechaHora = fechaHoraRepository.findById(idFechaHora)
                .orElseThrow(() -> new Exception("Horario no encontrado"));
        var especialidad = medico.getEspecialidad().stream()
                .filter(e -> e.getId().equals(idEspecialidad))
                .findFirst()
                .orElseThrow(() -> new Exception("Especialidad no encontrada"));

        if (!fechaHora.getDisponible()) {
            throw new Exception("El horario ya está ocupado.");
        }
        try {
            Cita cita = new Cita();
            cita.setFecha(fechaHora.getFecha());
            cita.setHora(fechaHora.getHora());
            cita.setEstado(EstadoDeCita.CANCELADA);
            cita.setMonto(new BigDecimal("90.00"));
            cita.setMedico(medico);
            cita.setUsuario(usuario);
            cita.setTipoPaciente(tipoPaciente);
            cita.setEspecialidad(especialidad);

            // GUARDAR LA CITA
            citaRepository.save(cita);

            // MARCAR HORARIO COMO NO DISPONIBLE
            fechaHora.setDisponible(false);
            fechaHoraRepository.save(fechaHora);

        } catch (Exception e) {
            throw new Exception("Error al guardar la cita: " + e.getMessage());
        }
    }

    @Override
    public void eliminarCita(Long id) throws Exception {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new Exception("Cita no encontrada"));

        // Liberar el horario
        FechaHora fechaHora = fechaHoraRepository.findByMedicoIdAndFechaAndHora(
                cita.getMedico().getId(), cita.getFecha(), cita.getHora());
        if (fechaHora != null) {
            fechaHora.setDisponible(true);
            fechaHoraRepository.save(fechaHora);
        }
        citaRepository.deleteById(id);
    }

    @Override
    public List<CitaDTO> listaCitas() throws Exception {
        return citaRepository.findAll().stream()
                .map(cita -> {
                    CitaDTO dto = citaMapper.toDTO(cita);
                    dto.setMedicoNombre(cita.getMedico().getNombre() + " " + cita.getMedico().getApellido());
                    dto.setEspecialidadNombre(cita.getEspecialidad().getNombre());
                    dto.setTipoPacienteNombre(cita.getTipoPaciente().getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CitaDTO obtenerCitaPorId(Long id) throws Exception {
        return citaRepository.findById(id)
                .map(cita -> {
                    CitaDTO dto = citaMapper.toDTO(cita);
                    dto.setMedicoNombre(cita.getMedico().getNombre() + " " + cita.getMedico().getApellido());
                    dto.setEspecialidadNombre(cita.getEspecialidad().getNombre());
                    dto.setTipoPacienteNombre(cita.getTipoPaciente().getNombre());
                    return dto;
                })
                .orElseThrow(() -> new Exception("Cita no encontrada"));
    }

    @Override
    public List<CitaDTO> obtenerCitasPorMedicoId(Long medicoId) throws Exception {
        List<Cita> citas = citaRepository.findByMedicoId(medicoId);
        return citas.stream()
                .map(cita -> {
                    CitaDTO dto = citaMapper.toDTO(cita);
                    dto.setMedicoNombre(cita.getMedico().getNombre() + " " + cita.getMedico().getApellido());
                    dto.setTipoPacienteNombre(cita.getTipoPaciente().getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaDTO> obtenerCitasPorPacienteId(Long pacienteId) throws Exception {
        List<Cita> citas = citaRepository.findByUsuarioId(pacienteId);
        return citas.stream()
                .map(cita -> {
                    CitaDTO dto = citaMapper.toDTO(cita);
                    dto.setMedicoNombre(cita.getMedico().getNombre() + " " + cita.getMedico().getApellido());
                    dto.setTipoPacienteNombre(cita.getTipoPaciente().getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void actualizarCita(Long id, Long idMedico, Long idPaciente, Long idTipoPaciente, Long idFechaHora) {
        try {
            Cita cita = citaRepository.findById(id)
                    .orElseThrow(() -> new Exception("Cita no encontrada"));

            var medico = medicoRepository.findById(idMedico)
                    .orElseThrow(() -> new Exception("Medico no encontrado"));
            var usuario = usuarioRepository.findById(idPaciente)
                    .orElseThrow(() -> new Exception("Paciente no encontrado"));
            var tipoPaciente = tipoPacienteRepository.findById(idTipoPaciente) // CORREGIR REPOSITORIO
                    .orElseThrow(() -> new Exception("Tipo de paciente no encontrado"));
            var fechaHora = fechaHoraRepository.findById(idFechaHora)
                    .orElseThrow(() -> new Exception("Horario no encontrado"));

            if (!fechaHora.getDisponible()) {
                throw new Exception("El horario ya está ocupado.");
            }

            cita.setMedico(medico);
            cita.setUsuario(usuario);
            cita.setTipoPaciente(tipoPaciente);
            cita.setFecha(fechaHora.getFecha());
            cita.setHora(fechaHora.getHora());

            // GUARDAR LA CITA
            citaRepository.save(cita);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

package com.femt.citas_reservacion_sistema_backend.service.imp;

import com.femt.citas_reservacion_sistema_backend.repository.CitaRepository;
import com.femt.citas_reservacion_sistema_backend.repository.FechaHoraRepository;
import com.femt.citas_reservacion_sistema_backend.repository.MedicoRepository;
import com.femt.citas_reservacion_sistema_backend.repository.UsuarioRepository;
import com.femt.citas_reservacion_sistema_backend.service.CitaService;
import com.femt.citas_reservacion_sistema_backend.dto.CitaDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Cita;
import com.femt.citas_reservacion_sistema_backend.entity.EstadoDeCita;
import com.femt.citas_reservacion_sistema_backend.entity.Usuario;
import com.femt.citas_reservacion_sistema_backend.mapper.CitaMapper;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class CitaServiceImp implements CitaService {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private FechaHoraRepository fechaHoraRepository;
    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private CitaMapper citaMapper;

    @Override
    public void guardarCita(Long idMedico, Long idPaciente, Long idTipoPaciente, Long idFechaHora) throws Exception {
        var medico = medicoRepository.findById(idMedico)
                .orElseThrow(() -> new Exception("Medico no encontrado"));
        var usuario = usuarioRepository.findById(idPaciente)
                .orElseThrow(() -> new Exception("Paciente no encontrado"));
        var tipoPaciente = usuarioRepository.findById(idTipoPaciente)
                .orElseThrow(() -> new Exception("Tipo de paciente no encontrado con ID: " + idTipoPaciente));
        var fechaHora = fechaHoraRepository.findById(idFechaHora)
                .orElseThrow(() -> new Exception("Horario no encontrado con ID: " + idFechaHora));

        if (!fechaHora.getDisponible()) {
            throw new Exception("El horario ya está ocupado.");
        }
        try {
            Cita cita = new Cita();
            cita.setFecha(fechaHora.getFecha());
            cita.setHora(fechaHora.getHora());
            cita.setEstado(EstadoDeCita.CANCELADA); // Enum definido en tu entidad
            cita.setMonto(new BigDecimal("0.00")); // Aquí podrías usar una lógica más precisa
            cita.setMedico(medico);
            cita.setUsuario(usuario);
            cita.setTipoPaciente(tipoPaciente);
        } catch (Exception e) {
            throw new Exception("Error al guardar la cita: " + e.getMessage());
        }
    }

    @Override
    public void eliminarCita(Long id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarCita'");
    }

    @Override
    public void actualizarCita(Long id, Long idMedico, Long idPaciente, Long idTipoPaciente, Long idFechaHora)
            throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarCita'");
    }

    @Override
    public List<CitaDTO> listaCitas() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listaCitas'");
    }

    @Override
    public CitaDTO obtenerCitaPorId(Long id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerCitaPorId'");
    }

    @Override
    public List<CitaDTO> obtenerCitasPorMedicoId(Long medicoId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerCitasPorMedicoId'");
    }

    @Override
    public List<CitaDTO> obtenerCitasPorPacienteId(Long pacienteId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerCitasPorPacienteId'");
    }

}

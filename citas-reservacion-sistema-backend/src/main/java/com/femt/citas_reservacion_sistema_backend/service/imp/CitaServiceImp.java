package com.femt.citas_reservacion_sistema_backend.service.imp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.femt.citas_reservacion_sistema_backend.dto.CitaDTO;
import com.femt.citas_reservacion_sistema_backend.entity.Cita;
import com.femt.citas_reservacion_sistema_backend.entity.EstadoDeCita;
import com.femt.citas_reservacion_sistema_backend.mapper.CitaMapper;
import com.femt.citas_reservacion_sistema_backend.repository.CitaRepository;
import com.femt.citas_reservacion_sistema_backend.service.CitaService;
import org.springframework.stereotype.Service;

@Service
public class CitaServiceImp implements CitaService {

    private final CitaRepository citaRepository;
    private final CitaMapper citaMapper;

    public CitaServiceImp(CitaRepository citaRepository, CitaMapper citaMapper) {
        this.citaRepository = citaRepository;
        this.citaMapper = citaMapper;
    }

    @Override
    public List<CitaDTO> listaCitas() throws Exception {
        return this.citaRepository.findAll().stream()
                .map(citaMapper::toDTO).toList();
    }

    @Override
    public Optional<CitaDTO> obtenerCitaPorId(Long id) throws Exception {
        return this.citaRepository.findById(id)
                .map(citaMapper::toDTO);
    }

    @Override
    public CitaDTO guardarCita(CitaDTO citaRequest) throws Exception {
        var cita = citaMapper.toEntity(citaRequest);
        var citaGuardada = this.citaRepository.save(cita);
        return citaMapper.toDTO(citaGuardada);
    }

    @Override
    public void eliminarCita(Long id) throws Exception {
        if (!citaRepository.existsById(id)) {
            throw new Exception("Cita no encontrada con ID: " + id);
        }
        this.citaRepository.deleteById(id);
    }

    @Override
    public CitaDTO actualizarCita(CitaDTO citaRequest) throws Exception {
        if (citaRequest.getId() == null || !citaRepository.existsById(citaRequest.getId())) {
            throw new Exception("Cita no encontrada para actualizar");
        }
        var cita = citaMapper.toEntity(citaRequest);
        var citaActualizada = this.citaRepository.save(cita);
        return citaMapper.toDTO(citaActualizada);
    }

    @Override
    public List<CitaDTO> obtenerCitasPorMedico(Long medicoId) throws Exception {
        return this.citaRepository.findByMedicoId(medicoId).stream()
                .map(citaMapper::toDTO).toList();
    }

    @Override
    public List<CitaDTO> obtenerCitasPorUsuario(Long usuarioId) throws Exception {
        return this.citaRepository.findByUsuarioId(usuarioId).stream()
                .map(citaMapper::toDTO).toList();
    }

    @Override
    public List<CitaDTO> obtenerCitasPorSede(Long sedeId) throws Exception {
        return this.citaRepository.findBySedeId(sedeId).stream()
                .map(citaMapper::toDTO).toList();
    }

    @Override
    public List<CitaDTO> obtenerCitasPorEspecialidad(Long especialidadId) throws Exception {
        return this.citaRepository.findByEspecialidadId(especialidadId).stream()
                .map(citaMapper::toDTO).toList();
    }

    @Override
    public List<CitaDTO> obtenerCitasPorFecha(LocalDate fecha) throws Exception {
        return this.citaRepository.findByFecha(fecha).stream()
                .map(citaMapper::toDTO).toList();
    }

    @Override
    public List<CitaDTO> obtenerCitasPorEstado(EstadoDeCita estado) throws Exception {
        return this.citaRepository.findByEstado(estado).stream()
                .map(citaMapper::toDTO).toList();
    }
}

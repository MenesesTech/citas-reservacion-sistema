package com.femt.citas_reservacion_sistema_backend.service.imp;

import java.util.List;

import com.femt.citas_reservacion_sistema_backend.dto.CitaDTO;
import com.femt.citas_reservacion_sistema_backend.mapper.CitaMapper;
import com.femt.citas_reservacion_sistema_backend.repository.CitaRepository;
import com.femt.citas_reservacion_sistema_backend.service.CitaService;

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
    public CitaDTO obtenerCitaPorId(Long id) throws Exception {
        return this.citaRepository.findById(id)
                .map(citaMapper::toDTO)
                .orElseThrow(() -> new Exception("Cita no encontrada"));
    }

    @Override
    public void guardarCita(CitaDTO citaRequest) throws Exception {
        this.citaRepository.save(citaMapper.toEntity(citaRequest));
    }

    @Override
    public void eliminarCita(Long id) throws Exception {
        this.citaRepository.deleteById(id);
    }

    @Override
    public void actualizarCita(CitaDTO citaRequest) throws Exception {
        this.citaRepository.save(citaMapper.toEntity(citaRequest));
    }

    @Override
    public List<CitaDTO> obtenerCitasPorMedico(Long medicoId) throws Exception {
        return this.citaRepository.findByMedicoId(medicoId).stream()
                .map(citaMapper::toDTO).toList();
    }

    @Override
    public List<CitaDTO> obtenerCitasPorPaciente(Long pacienteId) throws Exception {
        return this.citaRepository.findByPacienteId(pacienteId).stream()
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
    public List<CitaDTO> obtenerCitasPorFecha(String fecha) throws Exception {
        return this.citaRepository.findByFecha(fecha).stream()
                .map(citaMapper::toDTO).toList();
    }

    @Override
    public List<CitaDTO> obtenerCitasPorEstado(String estado) throws Exception {
        return this.citaRepository.findByEstado(estado).stream()
                .map(citaMapper::toDTO).toList();
    }

}

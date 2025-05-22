package com.femt.citas_reservacion_sistema_backend.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.femt.citas_reservacion_sistema_backend.dto.TipoPacienteDTO;
import com.femt.citas_reservacion_sistema_backend.mapper.TipoPacienteMapper;
import com.femt.citas_reservacion_sistema_backend.repository.TipoPacienteRepository;
import com.femt.citas_reservacion_sistema_backend.service.TipoPacienteService;
import org.springframework.stereotype.Service;

@Service
public class TipoPacienteServiceImp implements TipoPacienteService {
    @Autowired
    private TipoPacienteRepository tipoPacienteRepository;
    @Autowired
    private TipoPacienteMapper tipoPacienteMapper;

    @Override
    public List<TipoPacienteDTO> listaTipoPacientes() throws Exception {
        return this.tipoPacienteRepository.findAll().stream()
                .map(tipoPacienteMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public TipoPacienteDTO obtenerTipoPacientePorId(Long id) throws Exception {
        return this.tipoPacienteRepository.findById(id).map(tipoPacienteMapper::toDTO).orElse(null);
    }

    @Override
    public void guardarTipoPaciente(TipoPacienteDTO tipoPacienteRequest) throws Exception {
        try {
            this.tipoPacienteRepository.save(tipoPacienteMapper.toEntity(tipoPacienteRequest));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void eliminarTipoPaciente(Long id) throws Exception {
        try {
            this.tipoPacienteRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("No se puede eliminar el tipo de paciente: " + e.getMessage());
        }
    }

    @Override
    public void actualizarTipoPaciente(TipoPacienteDTO tipoPacienteRequest) throws Exception {
        try {
            this.tipoPacienteRepository.save(tipoPacienteMapper.toEntity(tipoPacienteRequest));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}

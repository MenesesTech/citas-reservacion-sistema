package com.femt.citas_reservacion_sistema_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.femt.citas_reservacion_sistema_backend.entity.Medico;
import org.springframework.beans.factory.annotation.Autowired;

import com.femt.citas_reservacion_sistema_backend.dto.MedicoDTO;
import com.femt.citas_reservacion_sistema_backend.mapper.MedicoMapper;
import com.femt.citas_reservacion_sistema_backend.repository.MedicoRepository;

public class MedicoServiceImp implements MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;
    private MedicoMapper medicoMapper;

    @Override
    public List<MedicoDTO> listaMedico() {
        return this.medicoRepository.findAll().stream().map(medicoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicoDTO obtenerMedicoPorId(Long id) {
        return this.medicoRepository.findById(id).map(medicoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Medico no encontrado"));
    }

    @Override
    public void guardarMedico(MedicoDTO medicoDTO) {
        this.medicoRepository.save(medicoMapper.toEntity(medicoDTO));
    }

    @Override
    public void actualizarMedico(MedicoDTO medicoDTO) {
        this.medicoRepository.save(medicoMapper.toEntity(medicoDTO));
    }

    @Override
    public void eliminarMedico(Long id) {
        this.medicoRepository.deleteById(id);
    }

    @Override
    public List<MedicoDTO> buscarMedicoPorNombre(String nombre) {
        Optional<Medico> medicos = medicoRepository.findByName(nombre);
        if (medicos.isEmpty()){
            throw new RuntimeException("Nombre de medico encontrado");
        }
        return medicos.stream()
                .map(medicoMapper::toDTO)
                .collect(Collectors.toList());
    }
}

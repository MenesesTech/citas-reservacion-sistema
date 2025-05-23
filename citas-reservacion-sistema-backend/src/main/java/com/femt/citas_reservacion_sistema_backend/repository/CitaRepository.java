package com.femt.citas_reservacion_sistema_backend.repository;

import com.femt.citas_reservacion_sistema_backend.entity.Cita;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByMedicoId(Long medicoId);

    List<Cita> findByPacienteId(Long pacienteId);

    List<Cita> findBySedeId(Long sedeId);

    List<Cita> findByEspecialidadId(Long especialidadId);

    List<Cita> findByFecha(String fecha);

    List<Cita> findByEstado(String estado);
}

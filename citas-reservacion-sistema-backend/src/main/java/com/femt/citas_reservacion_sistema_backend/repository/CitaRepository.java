package com.femt.citas_reservacion_sistema_backend.repository;

import com.femt.citas_reservacion_sistema_backend.entity.Cita;

import java.time.LocalDate;
import java.util.List;

import com.femt.citas_reservacion_sistema_backend.entity.EstadoDeCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByMedicoId(Long medicoId);

    List<Cita> findByUsuarioId(Long medicoId);

    @Query("SELECT c FROM Cita c WHERE c.medico.sede.id = :sedeId")
    List<Cita> findBySedeId(@Param("sedeId") Long sedeId);

    @Query("SELECT c FROM Cita c WHERE c.medicoEspecialidad.especialidad.id = :especialidadId")
    List<Cita> findByEspecialidadId(@Param("especialidadId") Long especialidadId);

    List<Cita> findByFecha(LocalDate fecha);

    List<Cita> findByEstado(EstadoDeCita estado);
}

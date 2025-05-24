package com.femt.citas_reservacion_sistema_backend.repository;

import com.femt.citas_reservacion_sistema_backend.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByMedicoId(Long medicoId);

    List<Cita> findByUsuarioId(Long usuarioId);

    @Query("SELECT c FROM Cita c WHERE c.medico.sede.id = :sedeId")
    List<Cita> findBySedeId(@Param("sedeId") Long sedeId);

    List<Cita> findByEstado(String estado);

    @Query("SELECT c FROM Cita c JOIN c.medico m JOIN m.horariosDisponibles h WHERE h.fecha = :fecha")
    List<Cita> findByFecha(@Param("fecha") LocalDate fecha);
}
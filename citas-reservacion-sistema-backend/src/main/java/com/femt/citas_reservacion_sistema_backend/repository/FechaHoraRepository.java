package com.femt.citas_reservacion_sistema_backend.repository;

import com.femt.citas_reservacion_sistema_backend.entity.FechaHora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FechaHoraRepository extends JpaRepository<FechaHora, Long> {

    // Buscar horarios disponibles de un médico
    List<FechaHora> findByMedicoIdAndDisponibleTrue(Long medicoId);

    // Buscar horarios disponibles por médico y fecha
    List<FechaHora> findByMedicoIdAndFechaAndDisponibleTrue(Long medicoId, LocalDate fecha);

    // Buscar todos los horarios por médico (independientemente de disponibilidad)
    List<FechaHora> findByMedicoId(Long medicoId);

    @Query("SELECT f FROM FechaHora f WHERE f.medico.id = :medicoId")
    List<FechaHora> findByFechaByMedicoId(@Param("medicoId") Long medicoId);
}

package com.femt.citas_reservacion_sistema_backend.repository;

import com.femt.citas_reservacion_sistema_backend.entity.FechaHora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FechaHoraRepository extends JpaRepository<FechaHora, Long> {

    List<FechaHora> findByMedicoId(Long medicoId);

    List<FechaHora> findByMedicoIdAndDisponible(Long medicoId, Boolean disponible);

    List<FechaHora> findByFecha(LocalDate fecha);

    List<FechaHora> findByMedicoIdAndFecha(Long medicoId, LocalDate fecha);

    Optional<FechaHora> findByMedicoIdAndFechaAndHora(Long medicoId, LocalDate fecha, LocalTime hora);

    @Query("SELECT f FROM FechaHora f WHERE f.medico.id = :medicoId AND f.fecha >= :fechaInicio AND f.disponible = true")
    List<FechaHora> findHorariosDisponiblesDesde(@Param("medicoId") Long medicoId, @Param("fechaInicio") LocalDate fechaInicio);
}
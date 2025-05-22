package com.femt.citas_reservacion_sistema_backend.repository;

import com.femt.citas_reservacion_sistema_backend.entity.FechaHora;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FechaHoraRepository extends JpaRepository<FechaHora, Long> {
    List<FechaHora> findByMedicoIdAndFechaAndHoraAndDisponibleTrue(Long medicoId, LocalDate fecha, LocalTime hora,
            Boolean disponible);

    // AGREGAR METODO PARA BUSCAR POR MEDICO, FECHA Y HORA
    FechaHora findByMedicoIdAndFechaAndHora(Long medicoId, LocalDate fecha, LocalTime hora);

    // AGREGAR METODO PARA OBTENER HORARIOS DISPONIBLES POR MÉDICO
    List<FechaHora> findByMedicoIdAndDisponibleTrue(Long medicoId);
}

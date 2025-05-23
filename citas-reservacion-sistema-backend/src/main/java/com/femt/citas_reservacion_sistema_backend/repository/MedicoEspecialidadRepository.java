package com.femt.citas_reservacion_sistema_backend.repository;

import com.femt.citas_reservacion_sistema_backend.entity.MedicoEspecialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicoEspecialidadRepository extends JpaRepository<MedicoEspecialidad, Long> {
    @Query(value = "SELECT e.nombre FROM especialidades as e\n" +
            "JOIN medico_especialidad as me\n" +
            "ON e.id = me.especialidad_id\n" +
            "WHERE me.medico_id = :medicoId", nativeQuery = true)
    List<String> findEspecialidadesPorMedico(@Param("medicoId") Long medicoId);
}

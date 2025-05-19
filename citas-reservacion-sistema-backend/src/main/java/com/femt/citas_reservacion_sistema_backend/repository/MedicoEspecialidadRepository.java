package com.femt.citas_reservacion_sistema_backend.repository;

import com.femt.citas_reservacion_sistema_backend.entity.MedicoEspecialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoEspecialidadRepository extends JpaRepository<MedicoEspecialidad, Long> {
}


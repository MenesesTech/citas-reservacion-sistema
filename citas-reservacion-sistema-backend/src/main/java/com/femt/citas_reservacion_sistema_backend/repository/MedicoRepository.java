package com.femt.citas_reservacion_sistema_backend.repository;

import com.femt.citas_reservacion_sistema_backend.entity.Medico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    @Query(value = "SELECT * FROM id FROM medicos WHERE id = :id", nativeQuery = true)
    Optional<Medico> findById(@Param("id") Long id);
}

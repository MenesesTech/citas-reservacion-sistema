package com.femt.citas_reservacion_sistema_backend.repository;

import com.femt.citas_reservacion_sistema_backend.entity.TipoPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPacienteRepository extends JpaRepository<TipoPaciente, Long> {
}

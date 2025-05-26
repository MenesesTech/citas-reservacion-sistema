package com.femt.citas_reservacion_sistema_backend.repository;

import com.femt.citas_reservacion_sistema_backend.entity.Medico;
import com.femt.citas_reservacion_sistema_backend.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findByEspecialidadId(Long especialidadId);
    @Query("SELECT m FROM Medico m WHERE m.usuario.id = :usuarioId")
    Optional<Medico> findByUsuarioId(@Param("usuarioId") Long usuarioId);
}

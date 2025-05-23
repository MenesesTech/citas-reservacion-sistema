package com.femt.citas_reservacion_sistema_backend.repository;

import com.femt.citas_reservacion_sistema_backend.entity.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findById(Long idUser);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByDni(String dni);
}

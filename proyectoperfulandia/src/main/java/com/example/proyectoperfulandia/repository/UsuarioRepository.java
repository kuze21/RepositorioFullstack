package com.example.proyectoperfulandia.repository;

import com.example.proyectoperfulandia.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repositorio JPA para la entidad Usuario
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Método personalizado para buscar al usuario mediante correo y contraseña
    Optional<Usuario> findByEmailAndPassword(String email, String password);
}
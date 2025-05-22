package com.example.proyectoperfulandia.repository;

import com.example.proyectoperfulandia.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositorio JPA para la entidad Administrador
@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
}

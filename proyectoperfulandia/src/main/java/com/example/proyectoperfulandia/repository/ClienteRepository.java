package com.example.proyectoperfulandia.repository;

import com.example.proyectoperfulandia.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositorio JPA para la entidad Cliente
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}

package com.example.proyectoperfulandia.repository;

import com.example.proyectoperfulandia.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositorio JPA para la entidad Empleado
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
}

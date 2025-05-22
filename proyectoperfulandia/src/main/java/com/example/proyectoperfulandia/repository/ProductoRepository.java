package com.example.proyectoperfulandia.repository;

import com.example.proyectoperfulandia.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositorio JPA para la entidad Producto
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}

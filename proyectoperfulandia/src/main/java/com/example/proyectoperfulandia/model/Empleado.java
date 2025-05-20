package com.example.proyectoperfulandia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass

@Entity
public class Empleado extends Usuario {
    private String sucursalId;
}

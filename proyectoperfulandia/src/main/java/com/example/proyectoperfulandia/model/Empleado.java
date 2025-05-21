package com.example.proyectoperfulandia.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
@Table(name = "empleados")
public class Empleado extends Usuario {
    private String sucursalId;
}

package com.example.proyectoperfulandia.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "empleados")
public class Empleado extends Usuario {

    // Establece los roles válidos para este usuario
    private static final Set<EnumRol> ROLES_VALIDOS = EnumSet.of(
            EnumRol.EMPLEADO
    );
    // Verifica que el usuario empleado solo pueda obtener el rol EMPLEADO
    public void setRol(EnumRol rol) {
        if (!ROLES_VALIDOS.contains(rol)) {
            throw new IllegalArgumentException("Rol no permitido para el empleado.");
        } else {
            super.setRol(rol);
        }
    }

    // Relación varios a uno con Sucursal
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sucursal_id", nullable = false)
    private Sucursal sucursal;

}

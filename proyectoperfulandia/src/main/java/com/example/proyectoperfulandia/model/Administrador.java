package com.example.proyectoperfulandia.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.EnumSet;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "administradores")
public class Administrador extends Usuario {

    private static final Set<EnumRol> ROLES_VALIDOS = EnumSet.of(
            EnumRol.ADMIN,
            EnumRol.GERENTE
    );

    public void setRol(EnumRol rol) {
        if (!ROLES_VALIDOS.contains(rol)) {
            throw new IllegalArgumentException("Rol no permitido para el administrador.");
        } else {
            super.setRol(rol);
        }
    }
}

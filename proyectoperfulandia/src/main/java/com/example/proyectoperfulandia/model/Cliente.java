package com.example.proyectoperfulandia.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "clientes")
public class Cliente extends Usuario {

    // Establece los roles válidos para este usuario
    private static final Set<EnumRol> ROLES_VALIDOS = EnumSet.of(
            EnumRol.CLIENTE
    );

    // Verifica que el usuario cliente solo pueda obtener el rol CLIENTE
    public void setRol(EnumRol rol) {
        if (!ROLES_VALIDOS.contains(rol)) {
            throw new IllegalArgumentException("Rol no permitido para el cliente.");
        } else {
            super.setRol(rol);
        }
    }

    private String direccion;
    private String telefono;

    // Relación uno a varios con Pedido
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

}


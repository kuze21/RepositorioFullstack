package com.example.proyectoperfulandia.model;

import jakarta.persistence.Inheritance;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
// revisar @Inheritance con el gemini
public class Usuario {
    private int id;
    private String rut;
    private String nombre;

    @Enumerated(EnumType.STRING)
    private EnumRol rol;

    private String email;
    private String password;
}

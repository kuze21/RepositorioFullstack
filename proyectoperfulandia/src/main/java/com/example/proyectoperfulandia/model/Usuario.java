package com.example.proyectoperfulandia.model;

import jakarta.persistence.Inheritance;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
// revisar @Inheritance con el gemini
public class Usuario {
    private int id;
    private String rut;
    private String nombre;
    private EnumRol rol;
    private String email;
    private String password;
}

package com.example.proyectoperfulandia.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {
    private int id;
    private String rut;
    private String nombre;
    private EnumRol rol;
    private String email;
    private String password;
}

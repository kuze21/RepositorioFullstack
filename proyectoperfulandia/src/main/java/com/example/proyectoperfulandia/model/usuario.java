package com.example.proyectoperfulandia.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class usuario {
    private Integer id;
    private String rut;
    private String nombre;
    private EnumRol rol;
    private String email;
    private String password;
}

package com.example.proyectoperfulandia.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuarios")
public class Usuario {

    // Generación automática de IDs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String rut;
    private String nombre;

    // Ocupando la clase de enumeración para definir los roles
    @Enumerated(EnumType.STRING)
    private EnumRol rol;

    private String email;
    private String password;

}

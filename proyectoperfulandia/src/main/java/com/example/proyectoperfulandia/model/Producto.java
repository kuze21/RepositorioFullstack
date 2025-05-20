package com.example.proyectoperfulandia.model;

import lombok.*;

@Data
@AllArgsConstructor

public class Producto {
    private String id;
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;
}

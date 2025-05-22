package com.example.proyectoperfulandia.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(unique = true, length = 10, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = true)
    private int stock;

    @Column(nullable = true)
    private String categoria;

    @ManyToMany(mappedBy = "listaProductos")
    private List<Pedido> pedidos = new ArrayList<>();

    @ManyToMany(mappedBy = "inventario")
    private List<Sucursal> inventario = new ArrayList<>();

}

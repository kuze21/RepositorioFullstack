package com.example.proyectoperfulandia.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sucursales")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String direccion;
    private String horario;

    @ManyToMany
    @JoinTable(
            name = "sucursal_producto",
            joinColumns = @JoinColumn(name = "sucursal_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> inventario = new ArrayList<>();

    @OneToMany(mappedBy = "sucursal")
    private List<Empleado> empleados = new ArrayList<>();

}

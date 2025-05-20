package com.example.proyectoperfulandia.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@AllArgsConstructor

public class Pedido {
    private String id;
    private Date fecha;

    @Enumerated(EnumType.STRING)
    private EnumEstado estado;

    private double total;
    private List<Producto> productos = new ArrayList<>();
}

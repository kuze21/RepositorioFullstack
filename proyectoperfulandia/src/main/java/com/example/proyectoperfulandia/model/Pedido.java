package com.example.proyectoperfulandia.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Data
@AllArgsConstructor

public class Pedido {
    private String id;
    private Date fecha;
    private EnumEstado estado;
    private double total;
    private List<Producto> productos = new ArrayList<>();
}

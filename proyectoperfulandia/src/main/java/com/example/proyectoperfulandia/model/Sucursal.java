package com.example.proyectoperfulandia.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor

public class Sucursal {
    private String id;
    private String direccion;
    private String horario;
    private List<Producto> inventario = new ArrayList<>();
}

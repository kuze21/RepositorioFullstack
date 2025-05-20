package com.example.proyectoperfulandia.model;

import lombok.*;

@Data
@AllArgsConstructor

public class Factura {
    private String numero;
    private double subtotal;
    private double iva;
    private double total;
}

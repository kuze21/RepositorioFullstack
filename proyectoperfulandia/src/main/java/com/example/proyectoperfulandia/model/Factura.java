package com.example.proyectoperfulandia.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "facturas")
public class Factura {

    // Generación automática de IDs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numero;

    private double subtotal;
    private double iva;
    private double total;
}

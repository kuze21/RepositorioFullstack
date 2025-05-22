package com.example.proyectoperfulandia.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Logistica {

    // Generación automática de IDs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pedidoId;

    // Ocupando la clase de enumeración para definir estados de envío
    @Enumerated(EnumType.STRING)
    private EnumEstadoEnvio estadoEnvio;
    private String ruta;

    // Relacion Uno a uno con Pedido
    @OneToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

}

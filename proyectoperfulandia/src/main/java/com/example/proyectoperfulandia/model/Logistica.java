package com.example.proyectoperfulandia.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Logistica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pedidoId;

    @Enumerated(EnumType.STRING)
    private EnumEstadoEnvio estadoEnvio;
    private String ruta;

    @OneToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

}

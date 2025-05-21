package com.example.proyectoperfulandia.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@Entity
public class Logistica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pedidoId;

    @Enumerated(EnumType.STRING)
    private EnumEstadoEnvio estadoEnvio;
    private String ruta;
}

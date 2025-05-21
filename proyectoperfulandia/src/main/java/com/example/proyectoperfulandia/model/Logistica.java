package com.example.proyectoperfulandia.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
public class Logistica {
    private int pedidoId;

    @Enumerated(EnumType.STRING)
    private EnumEstadoEnvio estadoEnvio;
    private String ruta;
}

package com.example.proyectoperfulandia.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@AllArgsConstructor

public class Logistica {
    private String pedidoId;

    @Enumerated(EnumType.STRING)
    private EnumEstadoEnvio estadoEnvio;
    private String ruta;
}

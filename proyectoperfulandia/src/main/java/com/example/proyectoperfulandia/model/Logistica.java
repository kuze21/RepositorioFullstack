package com.example.proyectoperfulandia.model;

import lombok.*;

@Data
@AllArgsConstructor

public class Logistica {
    private String pedidoId;
    private EnumEstadoEnvio estadoEnvio;
    private String ruta;
}

package com.example.proyectoperfulandia.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Empleado extends Usuario {
    private String sucursalId;
}

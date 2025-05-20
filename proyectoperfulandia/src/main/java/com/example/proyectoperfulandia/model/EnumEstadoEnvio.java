package com.example.proyectoperfulandia.model;


import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Data
@AllArgsConstructor

public class EnumEstadoEnvio {
    private List<String> estado = new ArrayList<>();

    public EnumEstadoEnvio() {
        estado = new ArrayList<>();
        estado.add("PREPARACION");
        estado.add("EN TRANSITO");
        estado.add("ENTREGADO");
        estado.add("DEVUELTO");
    }
}

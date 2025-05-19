package com.example.proyectoperfulandia.model;


import java.util.ArrayList;
import java.util.List;


public class EnumEstado {
    private List<String> estado = new ArrayList<>();

    public EnumEstado() {
        estado = new ArrayList<>();
        estado.add("PENDIENTE");
        estado.add("PROCESO");
        estado.add("ENVIADO");
        estado.add("ENTREGADO");
        estado.add("CANCELADO");
    }
}

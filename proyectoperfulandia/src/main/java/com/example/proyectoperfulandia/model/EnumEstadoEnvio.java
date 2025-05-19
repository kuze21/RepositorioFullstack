package com.example.proyectoperfulandia.model;


import java.util.ArrayList;
import java.util.List;


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

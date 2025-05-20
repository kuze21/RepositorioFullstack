package com.example.proyectoperfulandia.model;

public enum EnumEstado {
    PENDIENTE,
    PROCESO,
    ENVIADO,
    ENTREGADO,
    CANCELADO;

    public String getDisplayName() {
        return this.name();
    }
}
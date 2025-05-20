package com.example.proyectoperfulandia.model;

public enum EnumEstadoEnvio {
    PREPARACION,
    EN_TRANSITO,
    ENTREGADO,
    DEVUELTO;

    public String getDisplayName() {
        return this.name();
    }
}
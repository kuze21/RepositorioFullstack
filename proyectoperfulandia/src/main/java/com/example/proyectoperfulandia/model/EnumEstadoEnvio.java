package com.example.proyectoperfulandia.model;

// Clase de enumeración para establecer los estados de envío en relación a la logística
public enum EnumEstadoEnvio {
    PREPARACION,
    EN_TRANSITO,
    ENTREGADO,
    DEVUELTO;

    public String getDisplayName() {
        return this.name();
    }
}
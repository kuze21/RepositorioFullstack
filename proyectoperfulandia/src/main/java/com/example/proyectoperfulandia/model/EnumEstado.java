package com.example.proyectoperfulandia.model;

// Clase de enumeración para establecer estados de envío del pedido
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
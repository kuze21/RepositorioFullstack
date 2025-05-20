package com.example.proyectoperfulandia.model;

public enum EnumRol {
    CLIENTE,
    EMPLEADO,
    GERENTE,
    ADMIN;

    public String getDisplayName() {
        return this.name();
    }
}
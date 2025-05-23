package com.example.proyectoperfulandia.model;

// Clase de enumeración para establecer roles
public enum EnumRol {
    CLIENTE,
    EMPLEADO,
    GERENTE,
    ADMIN;

    public String getDisplayName() {
        return this.name();
    }

}
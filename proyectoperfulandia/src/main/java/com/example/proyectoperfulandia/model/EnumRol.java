package com.example.proyectoperfulandia.model;


import java.util.List;
import java.util.ArrayList;
import lombok.*;

@Data
@AllArgsConstructor

public class EnumRol {
    private List<String> roles = new ArrayList<>();

    public EnumRol() {
        roles = new ArrayList<>();
        roles.add("CLIENTE");
        roles.add("EMPLEADO");
        roles.add("GERENTE");
        roles.add("ADMIN");
    }
}

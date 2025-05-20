package com.example.proyectoperfulandia.repository;

import java.util.*;
import com.example.proyectoperfulandia.model.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {
    private List<Usuario> Usuarios = new ArrayList<Usuario>();
    public UsuarioRepository() {

    }

    public String addUsuario(Usuario usuario) {
        Usuarios.add(usuario);
        return "Agregado con éxito";
    }

    public String getUsuarios() {
        String output = "";
        for (Usuario usuario : Usuarios) {
            output += "ID: " + usuario.getId() + "\n";
            output += "ID: " + usuario.getId() + "\n";
            output += "ID: " + usuario.getId() + "\n";
            output += "ID: " + usuario.getId() + "\n";
        }
    }

}
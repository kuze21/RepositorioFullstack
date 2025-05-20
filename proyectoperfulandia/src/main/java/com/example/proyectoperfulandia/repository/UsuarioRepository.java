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
            output += "Nombre: " + usuario.getNombre() + "\n";
            output += "Rol: " + usuario.getRol() + "\n";
            output += "Correo: " + usuario.getEmail() + "\n";
        }

        if (output.length() > 0){
            return output;
        } else {
            return "No se encuentran usuarios";
        }

    }

    public String getUsuario(int Id) {
        for (Usuario usuario : Usuarios) {
            if (usuario.getId() == Id) {
                return usuario.toString();
            }
        }
        return "No existe el usuario con ese nombre";
    }

    public String removeUsuario(int Id) {
        for (Usuario usuario : Usuarios) {
            if (usuario.getId() == Id) {
                Usuarios.remove(usuario);
                return "Eliminado con éxito";
            }
        }
        return "No existe el usuario con ese nombre";
    }

    public String updateUsuario(int Id, Usuario usuario) {
        int index = 0;
        for (Usuario temp : Usuarios) {
            if (temp.getId() == Id) {
                index = Usuarios.indexOf(temp);
                break;
            }
        }
        if (index != 1) {
            Usuarios.set(index, usuario);
            return "Actualizado con éxito";
        } else {
            return "No existe el usuario con ese nombre";
        }
    }
}
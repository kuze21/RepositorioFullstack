package com.example.proyectoperfulandia.services;

import com.example.proyectoperfulandia.model.Usuario;
import com.example.proyectoperfulandia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Añadir un usuario
    public String addUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return "Agregado con éxito";
    }

    // Obtener todos los usuarios
    public String getUsuarios() {
        String output = "";
        for (Usuario usuario : usuarioRepository.findAll()) {
            output += "ID: " + usuario.getId() + "\n";
            output += "Nombre: " + usuario.getNombre() + "\n";
            output += "Rol: " + usuario.getRol() + "\n";
            output += "Correo: " + usuario.getEmail() + "\n";
        }
        if (output.isEmpty()) {
            return "No se encuentran usuarios";
        } else {
            return output;
        }

    }

    // Obtener un usuario mediante ID
    public String getUsuario(int id) {
        String output = "";
        if (usuarioRepository.existsById(id)) {
            Usuario user = usuarioRepository.findById(id).get();
            output += "ID: " + user.getId() + "\n";
            output += "Nombre: " + user.getNombre() + "\n";
            output += "Rol: " + user.getRol() + "\n";
            output += "Correo: " + user.getEmail() + "\n\n";
            return output;
        } else {
            return "No se encontraron usuarios.";
        }
    }
    // Eliminar un usuario mediante ID
    public String removeUsuario(int id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return "Usuario eliminado correctamente.";
        } else {
            return "No se encontraron usuarios.";
        }
    }

    // Actualizar un usuario mediante ID
    public String updateUsuario(int id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            Usuario usuarioActual = usuarioRepository.findById(id).get();
            usuarioActual.setNombre(usuario.getNombre());
            usuarioActual.setEmail(usuario.getEmail());
            usuarioRepository.save(usuarioActual);
            return "Usuario actualizado correctamente.";
        } else {
            return "No se encontraron usuarios.";
        }
    }
    // Auntenticar un usuario mediante correo y contraseña
    public Usuario autenticarUsuario(String email, String password) {
        return usuarioRepository.findByEmailAndPassword(email, password).orElse(null);
    }

}

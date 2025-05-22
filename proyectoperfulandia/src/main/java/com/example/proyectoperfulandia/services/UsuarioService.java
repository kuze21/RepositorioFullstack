package com.example.proyectoperfulandia.services;

import com.example.proyectoperfulandia.model.Administrador;
import com.example.proyectoperfulandia.model.Usuario;
import com.example.proyectoperfulandia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String addUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return "Agregado con éxito";
    }

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

    public String removeUsuario(int id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return "Administrados eliminado correctamente.";
        } else {
            return "No se encontraron administradores.";
        }
    }

    public String updateUsuario(int id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            Administrador adminActual = usuarioRepository.findById(id).get();
            adminActual.setNombre(usuario.getNombre());
            adminActual.setEmail(usuario.getEmail());
            usuarioRepository.save(adminActual);
            return "Cliente actualizado correctamente.";
        } else {
            return "No se encontraron clientes.";
        }
    }

    public Usuario autenticarUsuario(String email, String password) {
        // Suponiendo que el repositorio tiene un método personalizado:
        return usuarioRepository.findByEmailAndPassword(email, password).orElse(null);
    }



    /*
    @Autowired
    UsuarioRepository usuarioRepository;

    public String getUsuarios(){
        return usuarioRepository.getUsuarios();
    }

    public String addUsuario(Usuario usuario){
        return usuarioRepository.addUsuario(usuario);
    }

    public String getUsuario(int id){
        return usuarioRepository.getUsuario(id);
    }

    public String removeUsuario(int id){
        return usuarioRepository.removeUsuario(id);
    }

    public String updateUsuario(int id, Usuario usuario){
        return usuarioRepository.updateUsuario(id,usuario);
    }

    public Usuario autenticarUsuario(String email, String password) { return usuarioRepository.autenticarUsuario(email, password); }

     */

}

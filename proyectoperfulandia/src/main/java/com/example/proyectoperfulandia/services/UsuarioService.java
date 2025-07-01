package com.example.proyectoperfulandia.services;

import com.example.proyectoperfulandia.model.Usuario;
import com.example.proyectoperfulandia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Añadir un usuario
    public void addUsuario(Usuario user) {
        usuarioRepository.save(user);
    }

    // Obtener todos los usuarios
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    // Obtener un usuario mediante ID
    public Optional<Usuario> getUsuario(int id) {
        return usuarioRepository.findById(id);
    }

    // Eliminar un usuario mediante ID
    public void removeUsuario(int id) {
        usuarioRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return usuarioRepository.existsById(id);
    }

    // Actualizar un usuario mediante ID
    public void updateUsuario(int id, Usuario user) {
        Usuario us = usuarioRepository.findById(id).get();
        us.setRut(user.getRut());
        us.setNombre(user.getNombre());
        us.setEmail(user.getEmail());
        us.setPassword(user.getPassword());
        us.setRol(user.getRol());
        usuarioRepository.save(us);
    }

    // Auntenticar un usuario mediante correo y contraseña
    public Usuario autenticarUsuario(String email, String password) {
        return usuarioRepository.findByEmailAndPassword(email, password).orElse(null);
    }
}
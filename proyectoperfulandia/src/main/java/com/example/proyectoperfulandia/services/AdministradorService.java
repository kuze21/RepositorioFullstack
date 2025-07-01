package com.example.proyectoperfulandia.services;

import com.example.proyectoperfulandia.model.Administrador;
import com.example.proyectoperfulandia.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {

    @Autowired
    AdministradorRepository administradorRepository;

    // Obtener solo los usuarios con el rol ADMIN y GERENTE
    public List<Administrador> getAdmins() {
        return administradorRepository.findAll();
    }

    // Añadir un usuario ADMIN
    public void addAdmin(Administrador user) {
        administradorRepository.save(user);
    }

    // Obtener un usuario ADMIN mediante ID
    public Optional<Administrador> getAdmin(int id) {
        return administradorRepository.findById(id);
    }

    // Eliminar un usuario ADMIN mediante ID
    public void removeAdmin(int id) {
        administradorRepository.deleteById(id);
    }
    public boolean existsById(int id) {
        return administradorRepository.existsById(id);
    }

    // Actualizar un usuario ADMIN mediante ID
    public void updateAdmin(int id, Administrador user) {
        Administrador us = administradorRepository.findById(id).get();
        us.setRut(user.getRut());
        us.setNombre(user.getNombre());
        us.setEmail(user.getEmail());
        us.setPassword(user.getPassword());
        administradorRepository.save(us);
    }
}
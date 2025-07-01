package com.example.proyectoperfulandia.services;

import com.example.proyectoperfulandia.model.Administrador;
import com.example.proyectoperfulandia.model.Cliente;
import com.example.proyectoperfulandia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    // Obtener solo los usuarios con el rol CLIENTE
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    // Añadir un usuario CLIENTE
    public void addCliente(Cliente user) {
        clienteRepository.save(user);
    }

    // Obtener un usuario CLIENTE mediante ID
    public Optional<Cliente> getCliente(int id) {
        return clienteRepository.findById(id);
    }

    // Eliminar un usuario CLIENTE mediante ID
    public void removeCliente(int id) {
        clienteRepository.deleteById(id);
    }
    public boolean existsById(int id) {
        return clienteRepository.existsById(id);
    }

    // Actualizar un usuario CLIENTE mediante ID
    public void updateCliente(int id, Cliente user) {
        Cliente us = clienteRepository.findById(id).get();
        us.setRut(user.getRut());
        us.setNombre(user.getNombre());
        us.setEmail(user.getEmail());
        us.setPassword(user.getPassword());
        clienteRepository.save(us);
    }

}

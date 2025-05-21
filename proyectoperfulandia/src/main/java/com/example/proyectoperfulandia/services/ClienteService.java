package com.example.proyectoperfulandia.services;

import com.example.proyectoperfulandia.model.Cliente;
import com.example.proyectoperfulandia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public String getClientes(){
        String output = "";
        for (Cliente cliente : clienteRepository.findAll()) {
            output += "ID: " + cliente.getId() + "\n";
            output += "Nombre: " + cliente.getNombre() + "\n";
            output += "Rol: " + cliente.getRol() + "\n";
            output += "Correo: " + cliente.getEmail() + "\n";
            output += "Dirección: " + cliente.getDireccion() + "\n\n";
        }
        if (output.isEmpty()) {
            return "No se encuentran usuarios";
        } else {
            return output;
        }
    }

    public String addCliente(Cliente cliente){
        clienteRepository.save(cliente);
        return "Cliente agregado con éxito";
    }

    public String getClienteID(int id){
        String output = "";
        if (clienteRepository.existsById(id)) {
            Cliente cliente = clienteRepository.findById(id).get();
            output += "ID: " + cliente.getId() + "\n";
            output += "Nombre: " + cliente.getNombre() + "\n";
            output += "Rol: " + cliente.getRol() + "\n";
            output += "Correo: " + cliente.getEmail() + "\n";
            output += "Dirección: " + cliente.getDireccion() + "\n\n";
            return output;
        } else {
            return "No se encontraron clientes.";
        }
    }

    public String removeCliente(int id){
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return "Cliente eliminado correctamente.";
        } else {
            return "No se encontraron clientes.";
        }
    }

    public String updateCliente(int id, Cliente cliente){
        if (clienteRepository.existsById(id)) {
            Cliente clienteActual = clienteRepository.findById(id).get();
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setEmail(cliente.getEmail());
            clienteActual.setDireccion(cliente.getDireccion());
            clienteRepository.save(clienteActual);
            return "Cliente actualizado correctamente.";
        } else {
            return "No se encontraron clientes.";
        }
    }

}

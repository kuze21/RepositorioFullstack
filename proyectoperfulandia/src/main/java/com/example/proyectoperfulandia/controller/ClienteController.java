package com.example.proyectoperfulandia.controller;

import com.example.proyectoperfulandia.model.Cliente;
import com.example.proyectoperfulandia.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    // Obtener solo los usuarios con el rol CLIENTE
    @GetMapping
    public String getClientes(){
        return clienteService.getClientes();
    }

    // Añadir un usuario CLIENTE
    @PostMapping
    public String addCliente(@RequestBody Cliente cliente){
        return clienteService.addCliente(cliente);
    }

    // Obtener un usuario CLIENTE mediante ID
    @GetMapping("/{id}")
    public String getCliente(@PathVariable int id){
        return clienteService.getClienteID(id);
    }

    // Eliminar un usuario CLIENTE mediante ID
    @DeleteMapping("/{id}")
    public String removeCliente(@PathVariable int id){
        return clienteService.removeCliente(id);
    }

    // Actualizar un usuario CLIENTE mediante ID
    @PutMapping("/{id}")
    public String updateCliente(@PathVariable int id, @RequestBody Cliente cliente){
        return clienteService.updateCliente(id, cliente);
    }

}

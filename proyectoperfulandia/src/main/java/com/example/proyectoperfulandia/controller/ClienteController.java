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

    @GetMapping
    public String getClientes(){
        return clienteService.getClientes();
    }

    @PostMapping
    public String addCliente(@RequestBody Cliente cliente){
        return clienteService.addCliente(cliente);
    }

    @GetMapping("/{id}")
    public String getCliente(@PathVariable int id){
        return clienteService.getClienteID(id);
    }

    @DeleteMapping("/{id}")
    public String removeCliente(@PathVariable int id){
        return clienteService.removeCliente(id);
    }

    @PutMapping("/{id}")
    public String updateCliente(@PathVariable int id, @RequestBody Cliente cliente){
        return clienteService.updateCliente(id, cliente);
    }

}

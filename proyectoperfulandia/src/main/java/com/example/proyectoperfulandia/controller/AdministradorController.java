package com.example.proyectoperfulandia.controller;

import com.example.proyectoperfulandia.model.Administrador;
import com.example.proyectoperfulandia.services.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    AdministradorService adminService;

    // Obtener solo los usuarios con el rol ADMIN y GERENTE
    @GetMapping
    public String getAdmins(){
        return adminService.getAdmins();
    }

    // Añadir un usuario ADMIN
    @PostMapping
    public String addAdmin(@RequestBody Administrador admin){
        return adminService.addAdmin(admin);
    }

    // Obtener un usuario ADMIN mediante ID
    @GetMapping("/{id}")
    public String getAdmin(@PathVariable int id){
        return adminService.getAdminID(id);
    }

    // Eliminar un usuario ADMIN mediante ID
    @DeleteMapping("/{id}")
    public String removeAdmin(@PathVariable int id){
        return adminService.removeAdmin(id);
    }

    // Actualizar un usuario ADMIN mediante ID
    @PutMapping("/{id}")
    public String updateAdmin(@PathVariable int id, @RequestBody Administrador admin){
        return adminService.updateAdmin(id, admin);
    }

}

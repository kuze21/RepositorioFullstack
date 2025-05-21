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

    @GetMapping
    public String getAdmins(){
        return adminService.getAdmins();
    }

    @PostMapping
    public String addAdmin(@RequestBody Administrador admin){
        return adminService.addAdmin(admin);
    }

    @GetMapping("/{id}")
    public String getAdmin(@PathVariable int id){
        return adminService.getAdminID(id);
    }

    @DeleteMapping("/{id}")
    public String removeAdmin(@PathVariable int id){
        return adminService.removeAdmin(id);

    }

    @PutMapping("/{id}")
    public String updateAdmin(@PathVariable int id, @RequestBody Administrador admin){
        return adminService.updateAdmin(id, admin);
    }

}

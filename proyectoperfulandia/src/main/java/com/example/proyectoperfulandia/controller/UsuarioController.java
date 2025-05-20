package com.example.proyectoperfulandia.controller;

import com.example.proyectoperfulandia.model.Usuario;
import com.example.proyectoperfulandia.services.UsuarioService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public String getUsuarios(){
        return usuarioService.getUsuarios();
    }

    @PostMapping
    public String addUsuario(@RequestBody Usuario usuario){
        return usuarioService.addUsuario(usuario);
    }

    @GetMapping("/{id}")
    public String getUsuario(@PathVariable int id){
        return usuarioService.getUsuario(id);
    }

    @DeleteMapping("/{id}")
    public String removeUsuario(@PathVariable int id){
        return usuarioService.removeUsuario(id);
    }

    @PutMapping("/{id}")
    public String updateUsuario(@PathVariable int id, @RequestBody Usuario usuario){
        return usuarioService.updateUsuario(id, usuario);
    }

}

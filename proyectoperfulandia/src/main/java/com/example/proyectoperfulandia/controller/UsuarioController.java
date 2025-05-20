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

    @GetMapping("/{id}");

}

package com.example.proyectoperfulandia.controller;

import com.example.proyectoperfulandia.dto.LoginRequest;
import com.example.proyectoperfulandia.model.Usuario;
import com.example.proyectoperfulandia.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // Preguntar si se pueden usar DTOs para crear las funciones, o si simplemente deben ir dentro de los servicios
    @PostMapping("/login")
    public ResponseEntity<?> autenticarUsuario(@RequestBody LoginRequest login) {
        Usuario usuario = usuarioService.autenticarUsuario(login.getEmail(), login.getPassword());
        if (usuario != null) {
            return ResponseEntity.ok("Bienvenido, " + usuario.getNombre());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o clave incorrectos");
        }
    }
}

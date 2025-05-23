package com.example.proyectoperfulandia.controller;

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

    // Obtener todos los usuarios
    @GetMapping
    public String getUsuarios(){
        return usuarioService.getUsuarios();
    }

    // Añadir usuario sin restriccion de roles
    @PostMapping
    public String addUsuario(@RequestBody Usuario usuario){
        return usuarioService.addUsuario(usuario);
    }

    // Obtener un usuario mediante ID
    @GetMapping("/{id}")
    public String getUsuario(@PathVariable int id){
        return usuarioService.getUsuario(id);
    }

    // Eliminar un usuario mediante ID
    @DeleteMapping("/{id}")
    public String removeUsuario(@PathVariable int id){
        return usuarioService.removeUsuario(id);
    }

    // Actualizar un usuario mediante ID
    @PutMapping("/{id}")
    public String updateUsuario(@PathVariable int id, @RequestBody Usuario usuario){
        return usuarioService.updateUsuario(id, usuario);
    }

    // Autenticación de usuario (correo y contraseña)
    @PostMapping("/login")
    public ResponseEntity<?> autenticarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioAutenticado = usuarioService.autenticarUsuario(usuario.getEmail(), usuario.getPassword());
        if (usuarioAutenticado == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
        return ResponseEntity.ok("Bienvenido, " + usuarioAutenticado.getNombre());
    }

}

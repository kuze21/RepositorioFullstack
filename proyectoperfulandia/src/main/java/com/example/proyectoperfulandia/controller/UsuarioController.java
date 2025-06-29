package com.example.proyectoperfulandia.controller;


import com.example.proyectoperfulandia.model.Usuario;
import com.example.proyectoperfulandia.services.UsuarioService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuario", description = "API REST para administrar todos los usuarios del sistema")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    // Obtener todos los usuarios
    @GetMapping
    @Operation(summary = "Obtener listado de todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de usuarios generado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Listado no encontrado.")
    })
    public String getUsuarios(){
        return usuarioService.getUsuarios();
    }

    // Añadir usuario sin restriccion de roles
    @PostMapping
    @Operation(summary = "Agregar usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario agregado exitosamente.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud.")
    })
    public String addUsuario(@RequestBody Usuario usuario){
        return usuarioService.addUsuario(usuario);
    }

    // Obtener un usuario mediante ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario mediante ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado.")
    })
    @Parameter(description = "ID del usuario a obtener", required = true, example = "1")
    public String getUsuario(@PathVariable int id){
        return usuarioService.getUsuario(id);
    }

    // Eliminar un usuario mediante ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado.")
    })
    @Parameter(description = "ID del usuario a eliminar", required = true, example = "1")
    public String removeUsuario(@PathVariable int id){
        return usuarioService.removeUsuario(id);
    }

    // Actualizar un usuario mediante ID
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar datos de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud.")
    })
    @Parameter(description = "ID del usuario a actualizar", required = true, example = "1")
    public String updateUsuario(@PathVariable int id, @RequestBody Usuario usuario){
        return usuarioService.updateUsuario(id, usuario);
    }

    // Autenticación de usuario (correo y contraseña)
    @PostMapping("/login")
    @Operation(summary = "Autenticación de usuario", description = "Autenticación mediante correo y contraseña.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Acceso concedido.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud.")
    })
    public ResponseEntity<?> autenticarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioAutenticado = usuarioService.autenticarUsuario(usuario.getEmail(), usuario.getPassword());
        if (usuarioAutenticado == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas.");
        }
        return ResponseEntity.ok("Bienvenido, " + usuarioAutenticado.getNombre());
    }

}

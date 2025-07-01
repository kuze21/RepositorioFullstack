package com.example.proyectoperfulandia.controller;


import com.example.proyectoperfulandia.assembler.UsuarioModelAssembler;
import com.example.proyectoperfulandia.model.Usuario;
import com.example.proyectoperfulandia.services.UsuarioService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuario", description = "API REST para administrar todos los usuarios del sistema")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioModelAssembler assembler;

    // Obtener todos los usuarios
    @GetMapping
    @Operation(summary = "Obtener listado de todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de usuarios generado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Listado no encontrado.")
    })
    public ResponseEntity<CollectionModel<EntityModel<Usuario>>> getUsuarios() {
        List<Usuario> usuarios = usuarioService.getUsuarios();
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(assembler.toCollectionModel(usuarios), HttpStatus.OK);
    }


    // Añadir usuario sin restriccion de roles
    @PostMapping
    @Operation(summary = "Agregar usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario agregado exitosamente.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud.")
    })
    public ResponseEntity<EntityModel<Usuario>> addUsuario(
            @Parameter(description = "Datos del usuario a agregar", required = true)
            @RequestBody Usuario usuario) {
        try {
            usuarioService.addUsuario(usuario);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

    // Obtener un usuario mediante ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario mediante ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado.")
    })
    public ResponseEntity<EntityModel<Usuario>> getUsuario(
            @Parameter(description = "ID del usuario a obtener", required = true, example = "1")
            @PathVariable int id){
        return usuarioService.getUsuario(id)
                .map(usuario -> new ResponseEntity<>(assembler.toModel(usuario), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar un usuario mediante ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado.")
    })
    public ResponseEntity<?> removeUsuario(
            @Parameter(description = "ID del usuario a eliminar", required = true, example = "1")
            @PathVariable int id){
        if (!usuarioService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuarioService.removeUsuario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // Actualizar un usuario mediante ID
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar datos de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud."),
            @ApiResponse(responseCode = "400", description = "Datos ingresados de forma incorrecta."),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado.")
    })
    public ResponseEntity<EntityModel<Usuario>> updateUsuario(
            @Parameter(description = "ID del usuario a actualizar", required = true, example = "1")
            @PathVariable int id,
            @Parameter(description = "Datos del usuario a actualizar", required = true)
            @RequestBody Usuario usuario) {
        if (!usuarioService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuario.setId(id);
        usuarioService.updateUsuario(id, usuario);
        return new ResponseEntity<>(assembler.toModel(usuario), HttpStatus.OK);
    }

    // Autenticación de usuario (correo y contraseña)
    @PostMapping("/login")
    @Operation(summary = "Autenticación de usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acceso concedido.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud."),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas..")
    })
    public ResponseEntity<?> autenticarUsuario(
            @Parameter(description = "Ingrese su email y contraseña")
            @RequestBody Usuario usuario) {
        Usuario usuarioAutenticado = usuarioService.autenticarUsuario(usuario.getEmail(), usuario.getPassword());
        if (usuarioAutenticado == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas.");
        }
        return ResponseEntity.ok("Bienvenido, " + usuarioAutenticado.getNombre());
    }
}
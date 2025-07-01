package com.example.proyectoperfulandia.controller;

import com.example.proyectoperfulandia.assembler.AdministradorModelAssembler;
import com.example.proyectoperfulandia.model.Administrador;
import com.example.proyectoperfulandia.services.AdministradorService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administradores")
@Tag(name = "Administrador", description = "API REST para administrar los usuarios con el rol ADMIN y GERENTE")
public class AdministradorController {

    @Autowired
    AdministradorService adminService;

    @Autowired
    AdministradorModelAssembler assembler;

    // Obtener solo los usuarios con el rol ADMIN y GERENTE
    @GetMapping
    @Operation(summary = "Obtener listado de todos los administradores y gerentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de usuarios generado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Listado no encontrado.")
    })
    public ResponseEntity<CollectionModel<EntityModel<Administrador>>> getAdmins() {
        List<Administrador> admins = adminService.getAdmins();
        if (admins.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(assembler.toCollectionModel(admins), HttpStatus.OK);
    }

    // Añadir un usuario ADMIN
    @PostMapping
    @Operation(summary = "Agregar administrador o gerente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario agregado exitosamente.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Administrador.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud.")
    })
    public ResponseEntity<EntityModel<Administrador>> addAdmin(
            @Parameter(name = "Datos del usuario ADMIN a agregar", required = true)
            @RequestBody Administrador admin) {
        try {
            adminService.addAdmin(admin);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Obtener un usuario ADMIN mediante ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener administrador o gerente mediante ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado.")
    })
    public ResponseEntity<EntityModel<Administrador>> getAdmin(
            @Parameter(description = "ID del usuario ADMIN a obtener", required = true, example = "1")
            @PathVariable int id){
        return adminService.getAdmin(id)
                .map(admin -> new ResponseEntity<>(assembler.toModel(admin), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar un usuario ADMIN mediante ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar administrador o gerente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado.")
    })
    public ResponseEntity<?> removeAdmin(
            @Parameter(description = "ID del usuario ADMIN a eliminar", required = true, example = "1")
            @PathVariable int id){
        if (!adminService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        adminService.removeAdmin(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Actualizar un usuario ADMIN mediante ID
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar datos de un administrador o gerente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Administrador.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud.")
    })
    public ResponseEntity<EntityModel<Administrador>> updateAdmin(
            @Parameter(description = "ID del usuario ADMIN a actualizar", required = true, example = "1")
            @PathVariable int id,
            @Parameter(description = "Datos del usuario ADMIN a actualizar", required = true)
            @RequestBody Administrador admin) {
        if (!adminService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        admin.setId(id);
        adminService.updateAdmin(id, admin);
        return new ResponseEntity<>(assembler.toModel(admin), HttpStatus.OK);
    }

}

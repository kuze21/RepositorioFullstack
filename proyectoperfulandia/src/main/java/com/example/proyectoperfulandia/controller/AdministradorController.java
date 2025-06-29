package com.example.proyectoperfulandia.controller;

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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administradores")
@Tag(name = "Administrador", description = "API REST para administrar los usuarios con el rol ADMIN y GERENTE")
public class AdministradorController {

    @Autowired
    AdministradorService adminService;

    // Obtener solo los usuarios con el rol ADMIN y GERENTE
    @GetMapping
    @Operation(summary = "Obtener listado de todos los administradores y gerentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de usuarios generado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Listado no encontrado.")
    })
    public String getAdmins(){
        return adminService.getAdmins();
    }

    // Añadir un usuario ADMIN
    @PostMapping
    @Operation(summary = "Agregar administrador o gerente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario agregado exitosamente.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Administrador.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud.")
    })
    public String addAdmin(@RequestBody Administrador admin){
        return adminService.addAdmin(admin);
    }

    // Obtener un usuario ADMIN mediante ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener administrador o gerente mediante ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado.")
    })
    @Parameter(description = "ID del administrador o gerente a obtener", required = true, example = "1")
    public String getAdmin(@PathVariable int id){
        return adminService.getAdminID(id);
    }

    // Eliminar un usuario ADMIN mediante ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar administrador o gerente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado.")
    })
    @Parameter(description = "ID del administrador o gerente a eliminar", required = true, example = "1")
    public String removeAdmin(@PathVariable int id){
        return adminService.removeAdmin(id);
    }

    // Actualizar un usuario ADMIN mediante ID
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar datos de un administrador o gerente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Administrador.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud.")
    })
    @Parameter(description = "ID del administrador o gerente a actualizar", required = true, example = "1")
    public String updateAdmin(@PathVariable int id, @RequestBody Administrador admin){
        return adminService.updateAdmin(id, admin);
    }

}

package com.example.proyectoperfulandia.controller;

import com.example.proyectoperfulandia.model.Cliente;
import com.example.proyectoperfulandia.services.ClienteService;
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
@RequestMapping("/clientes")
@Tag(name = "Cliente", description = "API REST para administrar los usuarios con el rol CLIENTE")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    // Obtener solo los usuarios con el rol CLIENTE
    @GetMapping
    @Operation(summary = "Obtener listado de todos los clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de usuarios generado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Listado no encontrado.")
    })
    public String getClientes(){
        return clienteService.getClientes();
    }

    // Añadir un usuario CLIENTE
    @PostMapping
    @Operation(summary = "Agregar cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario agregado exitosamente.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud.")
    })
    public String addCliente(@RequestBody Cliente cliente){
        return clienteService.addCliente(cliente);
    }

    // Obtener un usuario CLIENTE mediante ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente mediante ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado.")
    })
    @Parameter(description = "ID del cliente a obtener", required = true, example = "1")
    public String getCliente(@PathVariable int id){
        return clienteService.getClienteID(id);
    }

    // Eliminar un usuario CLIENTE mediante ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado.")
    })
    @Parameter(description = "ID del cliente a eliminar", required = true, example = "1")
    public String removeCliente(@PathVariable int id){
        return clienteService.removeCliente(id);
    }

    // Actualizar un usuario CLIENTE mediante ID
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar datos de un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud.")
    })
    @Parameter(description = "ID del cliente a actualizar", required = true, example = "1")
    public String updateCliente(@PathVariable int id, @RequestBody Cliente cliente){
        return clienteService.updateCliente(id, cliente);
    }

}

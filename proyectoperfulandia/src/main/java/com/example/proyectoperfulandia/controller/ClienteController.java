package com.example.proyectoperfulandia.controller;

import com.example.proyectoperfulandia.assembler.ClienteModelAssembler;
import com.example.proyectoperfulandia.model.Administrador;
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
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Cliente", description = "API REST para administrar los usuarios con el rol CLIENTE")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ClienteModelAssembler assembler;

    // Obtener solo los usuarios con el rol CLIENTE
    @GetMapping
    @Operation(summary = "Obtener listado de todos los clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de usuarios generado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Listado no encontrado.")
    })
    public ResponseEntity<CollectionModel<EntityModel<Cliente>>> getClientes() {
        List<Cliente> clientes = clienteService.getClientes();
        if (clientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(assembler.toCollectionModel(clientes), HttpStatus.OK);
    }

    // Añadir un usuario CLIENTE
    @PostMapping
    @Operation(summary = "Agregar cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario agregado exitosamente.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud.")
    })
    public ResponseEntity<EntityModel<Cliente>> addCliente(
            @Parameter(description = "Datos del usuario CLIENTE a agregar", required = true)
            @RequestBody Cliente cliente) {
        try {
            clienteService.addCliente(cliente);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Obtener un usuario CLIENTE mediante ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente mediante ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado.")
    })
    public ResponseEntity<EntityModel<Cliente>> getCliente(
            @Parameter(description = "ID del usuario CLIENTE a obtener", required = true, example = "1")
            @PathVariable int id){
        return clienteService.getCliente(id)
                .map(cliente -> new ResponseEntity<>(assembler.toModel(cliente), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar un usuario CLIENTE mediante ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado.")
    })
    public ResponseEntity<?> removeCliente(
            @Parameter(description = "ID del usuario CLIENTE a eliminar", required = true, example = "1")
            @PathVariable int id){
        if (!clienteService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        clienteService.removeCliente(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Actualizar un usuario CLIENTE mediante ID
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar datos de un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud.")
    })
    public ResponseEntity<EntityModel<Cliente>> updateCliente(
            @Parameter(description = "ID del usuario ADMIN a actualizar", required = true, example = "1")
            @PathVariable int id,
            @Parameter(description = "Datos del usuario ADMIN a actualizar", required = true)
            @RequestBody Cliente cliente) {
        if (!clienteService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cliente.setId(id);
        clienteService.updateCliente(id, cliente);
        return new ResponseEntity<>(assembler.toModel(cliente), HttpStatus.OK);
    }

}

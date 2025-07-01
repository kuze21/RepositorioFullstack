package com.example.proyectoperfulandia.controller;

import com.example.proyectoperfulandia.assembler.EmpleadoModelAssembler;
import com.example.proyectoperfulandia.model.Empleado;
import com.example.proyectoperfulandia.services.EmpleadoService;
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
@RequestMapping("/empleados")
@Tag(name = "Empleado", description = "API REST para administrar los usuarios con el rol EMPLEADO")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    EmpleadoModelAssembler assembler;

    // Obtener solo los usuarios con el rol EMPLEADO
    @GetMapping
    @Operation(summary = "Obtener listado de todos los empleados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de usuarios generado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Listado no encontrado.")
    })
    public ResponseEntity<CollectionModel<EntityModel<Empleado>>> getEmpleados() {
        List<Empleado> empleado = empleadoService.getEmpleados();
        if (empleado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(assembler.toCollectionModel(empleado), HttpStatus.OK);
    }

    // Añadir un usuario EMPLEADO
    @PostMapping
    @Operation(summary = "Agregar empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario agregado exitosamente.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Empleado.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud.")
    })
    public ResponseEntity<EntityModel<Empleado>> addEmpleado(
            @Parameter(description = "Datos del usuario EMPLEADO a agregar", required = true)
            @RequestBody Empleado empleado) {
        try {
            empleadoService.addEmpleado(empleado);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Obtener un usuario EMPLEADO mediante ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener empleado mediante ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado.")
    })
    public ResponseEntity<EntityModel<Empleado>> getEmpleado(
            @Parameter(description = "ID del usuario EMPLEADO a obtener", required = true, example = "1")
            @PathVariable int id){
        return empleadoService.getEmpleado(id)
                .map(empleado -> new ResponseEntity<>(assembler.toModel(empleado), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar un usuario EMPLEADO mediante ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado.")
    })
    @Parameter(description = "ID del empleado a eliminar", required = true, example = "1")
    public ResponseEntity<?> removeEmpleado(
            @Parameter(description = "ID del usuario EMPLEADO a eliminar", required = true, example = "1")
            @PathVariable int id){
        if (!empleadoService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        empleadoService.removeEmpleado(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Actualizar un usuario EMPLEADO mediante ID
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar datos de un empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Empleado.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud.")
    })
    public ResponseEntity<EntityModel<Empleado>> updateEmpleado(
            @Parameter(description = "ID del usuario ADMIN a actualizar", required = true, example = "1")
            @PathVariable int id,
            @Parameter(description = "Datos del usuario ADMIN a actualizar", required = true)
            @RequestBody Empleado empleado) {
        if (!empleadoService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        empleado.setId(id);
        empleadoService.updateEmpleado(id, empleado);
        return new ResponseEntity<>(assembler.toModel(empleado), HttpStatus.OK);
    }

}

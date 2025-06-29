package com.example.proyectoperfulandia.controller;

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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleados")
@Tag(name = "Empleado", description = "API REST para administrar los usuarios con el rol EMPLEADO")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    // Obtener solo los usuarios con el rol EMPLEADO
    @GetMapping
    @Operation(summary = "Obtener listado de todos los empleados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de usuarios generado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Listado no encontrado.")
    })
    public String getEmpleados(){
        return empleadoService.getEmpleados();
    }

    // Añadir un usuario EMPLEADO
    @PostMapping
    @Operation(summary = "Agregar empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario agregado exitosamente.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Empleado.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud.")
    })
    public String addEmpleado(@RequestBody Empleado empleado){
        return empleadoService.addEmpleado(empleado);
    }

    // Obtener un usuario EMPLEADO mediante ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener empleado mediante ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado.")
    })
    @Parameter(description = "ID del empleado a obtener", required = true, example = "1")
    public String getEmpleado(@PathVariable int id){
        return empleadoService.getEmpleadoID(id);
    }

    // Eliminar un usuario EMPLEADO mediante ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado.")
    })
    @Parameter(description = "ID del empleado a eliminar", required = true, example = "1")
    public String removeEmpleado(@PathVariable int id){
        return empleadoService.removeEmpleado(id);
    }

    // Actualizar un usuario EMPLEADO mediante ID
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar datos de un empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Empleado.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud.")
    })
    @Parameter(description = "ID del empleado a actualizar", required = true, example = "1")
    public String updateEmpleado(@PathVariable int id, @RequestBody Empleado empleado){
        return empleadoService.updateEmpleado(id, empleado);
    }

}

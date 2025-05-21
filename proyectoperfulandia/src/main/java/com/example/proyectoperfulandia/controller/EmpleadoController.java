package com.example.proyectoperfulandia.controller;

import com.example.proyectoperfulandia.model.Cliente;
import com.example.proyectoperfulandia.model.Empleado;
import com.example.proyectoperfulandia.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    @GetMapping
    public String getEmpleados(){
        return empleadoService.getEmpleados();
    }

    @PostMapping
    public String addEmpleado(@RequestBody Empleado empleado){
        return empleadoService.addEmpleado(empleado);
    }

    @GetMapping("/{id}")
    public String getEmpleado(@PathVariable int id){
        return empleadoService.getEmpleadoID(id);
    }

    @DeleteMapping("/{id}")
    public String removeEmpleado(@PathVariable int id){
        return empleadoService.removeEmpleado(id);
    }

    @PutMapping("/{id}")
    public String updateEmpleado(@PathVariable int id, @RequestBody Empleado empleado){
        return empleadoService.updateEmpleado(id, empleado);
    }

}

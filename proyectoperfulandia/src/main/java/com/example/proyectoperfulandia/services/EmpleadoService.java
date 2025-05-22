package com.example.proyectoperfulandia.services;

import com.example.proyectoperfulandia.model.Empleado;
import com.example.proyectoperfulandia.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    // Obtener solo los usuarios con el rol EMPLEADO
    public String getEmpleados(){
        String output = "";
        for (Empleado empleado : empleadoRepository.findAll()) {
            output += "ID: " + empleado.getId() + "\n";
            output += "Nombre: " + empleado.getNombre() + "\n";
            output += "Rol: " + empleado.getRol() + "\n";
            output += "Correo: " + empleado.getEmail() + "\n";
            output += "ID Sucursal: " + empleado.getSucursal().getId() + "\n\n";
        }
        if (output.isEmpty()) {
            return "No se encuentran empleados";
        } else {
            return output;
        }
    }

    // Añadir un usuario EMPLEADO
    public String addEmpleado(Empleado empleado){
        empleadoRepository.save(empleado);
        return "Empleado agregado con éxito";
    }

    // Obtener un usuario EMPLEADO mediante ID
    public String getEmpleadoID(int id){
        String output = "";
        if (empleadoRepository.existsById(id)) {
            Empleado empleado = empleadoRepository.findById(id).get();
            output += "ID: " + empleado.getId() + "\n";
            output += "Nombre: " + empleado.getNombre() + "\n";
            output += "Rol: " + empleado.getRol() + "\n";
            output += "Correo: " + empleado.getEmail() + "\n";
            output += "ID Sucursal: " + empleado.getSucursal().getId() + "\n\n";
            return output;
        } else {
            return "No se encontraron empleados.";
        }
    }

    // Eliminar un usuario EMPLEADO mediante ID
    public String removeEmpleado(int id){
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
            return "Empelado eliminado correctamente.";
        } else {
            return "No se encontraron empleados.";
        }
    }

    // Actualizar un usuario EMPLEADO mediante ID
    public String updateEmpleado(int id, Empleado empleado){
        if (empleadoRepository.existsById(id)) {
            Empleado empleadoActual = empleadoRepository.findById(id).get();
            empleadoActual.setNombre(empleado.getNombre());
            empleadoActual.setEmail(empleado.getEmail());
            empleadoRepository.save(empleadoActual);
            return "Empleado actualizado correctamente.";
        } else {
            return "No se encontraron empleados.";
        }
    }

}

package com.example.proyectoperfulandia.services;

import com.example.proyectoperfulandia.model.Empleado;
import com.example.proyectoperfulandia.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    // Obtener solo los usuarios con el rol EMPLEADO
    public List<Empleado> getEmpleados() {
        return empleadoRepository.findAll();
    }

    // Añadir un usuario EMPLEADO
    public void addEmpleado(Empleado user) {
        empleadoRepository.save(user);
    }

    // Obtener un usuario EMPLEADO mediante ID
    public Optional<Empleado> getEmpleado(int id) {
        return empleadoRepository.findById(id);
    }

    // Eliminar un usuario EMPLEADO mediante ID
    public void removeEmpleado(int id) {
        empleadoRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return empleadoRepository.existsById(id);
    }

    // Actualizar un usuario EMPLEADO mediante ID
    public void updateEmpleado(int id, Empleado user) {
        Empleado us = empleadoRepository.findById(id).get();
        us.setRut(user.getRut());
        us.setNombre(user.getNombre());
        us.setEmail(user.getEmail());
        us.setPassword(user.getPassword());
        empleadoRepository.save(us);
    }
}
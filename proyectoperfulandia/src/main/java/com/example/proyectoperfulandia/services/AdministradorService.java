package com.example.proyectoperfulandia.services;

import com.example.proyectoperfulandia.model.Administrador;
import com.example.proyectoperfulandia.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {

    @Autowired
    AdministradorRepository administradorRepository;

    // Obtener solo los usuarios con el rol ADMIN y GERENTE
    public String getAdmins(){
        String output = "";
        for (Administrador admin : administradorRepository.findAll()) {
            output += "ID: " + admin.getId() + "\n";
            output += "Nombre: " + admin.getNombre() + "\n";
            output += "Rol: " + admin.getRol() + "\n";
            output += "Correo: " + admin.getEmail() + "\n\n";
        }
        if (output.isEmpty()) {
            return "No se encuentran administradores";
        } else {
            return output;
        }
    }

    // Añadir un usuario ADMIN
    public String addAdmin(Administrador admin){
        administradorRepository.save(admin);
        return "Administrador agregado con éxito";
    }

    // Obtener un usuario ADMIN mediante ID
    public String getAdminID(int id){
        String output = "";
        if (administradorRepository.existsById(id)) {
            Administrador admin = administradorRepository.findById(id).get();
            output += "ID: " + admin.getId() + "\n";
            output += "Nombre: " + admin.getNombre() + "\n";
            output += "Rol: " + admin.getRol() + "\n";
            output += "Correo: " + admin.getEmail() + "\n\n";
            return output;
        } else {
            return "No se encontraron administradores.";
        }
    }

    // Eliminar un usuario ADMIN mediante ID
    public String removeAdmin(int id){
        if (administradorRepository.existsById(id)) {
            administradorRepository.deleteById(id);
            return "Administrados eliminado correctamente.";
        } else {
            return "No se encontraron administradores.";
        }
    }

    // Actualizar un usuario ADMIN mediante ID
    public String updateAdmin(int id, Administrador admin){
        if (administradorRepository.existsById(id)) {
            Administrador adminActual = administradorRepository.findById(id).get();
            adminActual.setNombre(admin.getNombre());
            adminActual.setEmail(admin.getEmail());
            administradorRepository.save(adminActual);
            return "Cliente actualizado correctamente.";
        } else {
            return "No se encontraron clientes.";
        }
    }

}

package com.example.proyectoperfulandia.assembler;

import com.example.proyectoperfulandia.controller.AdministradorController;
import com.example.proyectoperfulandia.model.Administrador;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
// AdministradorModelAssembler.java
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AdministradorModelAssembler implements RepresentationModelAssembler<Administrador, EntityModel<Administrador>> {

    @Override
    public EntityModel<Administrador> toModel(Administrador user) {
        return EntityModel.of(user,
                // Enlace a sí mismo - permite obtener este usuario específico
                linkTo(methodOn(AdministradorController.class).getAdmin(user.getId())).withSelfRel(),
                // Enlace a la colección - permite navegar a todos los usuario
                linkTo(methodOn(AdministradorController.class).getAdmins()).withRel("administradores"),
                // Enlace para actualizar - permite modificar este usuario
                linkTo(methodOn(AdministradorController.class).updateAdmin(user.getId(), user)).withRel("PUT"),
                // Enlace para eliminar - permite borrar este usuario
                linkTo(methodOn(AdministradorController.class).removeAdmin(user.getId())).withRel("DELETE")
        );
    }
}
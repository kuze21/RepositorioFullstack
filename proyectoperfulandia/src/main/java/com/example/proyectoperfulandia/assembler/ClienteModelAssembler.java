package com.example.proyectoperfulandia.assembler;

import com.example.proyectoperfulandia.controller.ClienteController;
import com.example.proyectoperfulandia.model.Cliente;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
// ClienteModelAssembler.java
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {

    @Override
    public EntityModel<Cliente> toModel(Cliente user) {
        return EntityModel.of(user,
                // Enlace a sí mismo - permite obtener este usuario específico
                linkTo(methodOn(ClienteController.class).getCliente(user.getId())).withSelfRel(),
                // Enlace a la colección - permite navegar a todos los usuarios
                linkTo(methodOn(ClienteController.class).getClientes()).withRel("clientes"),
                // Enlace para actualizar - permite modificar este usuario
                linkTo(methodOn(ClienteController.class).updateCliente(user.getId(), user)).withRel("PUT"),
                // Enlace para eliminar - permite borrar este usuario
                linkTo(methodOn(ClienteController.class).removeCliente(user.getId())).withRel("DELETE")
        );
    }
}
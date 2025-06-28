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

    /*
    RepresentionalModel = Permite a la clase contener una lista de Links de acceso
    EntityModel = Es un contenedor generico que adjunta la entidad mas una serie de enlaces
    LinkTo = Es un metodo que nos permite construir los Links o URL's de acceso que posee nuestro controlador
    */
    @Override
    public EntityModel<Cliente> toModel(Cliente user) {
        return EntityModel.of(user,
                linkTo(methodOn(ClienteController.class).getCliente(user.getId())).withSelfRel(),
                linkTo(methodOn(ClienteController.class).getClientes()).withRel("clientes"),
                linkTo(methodOn(ClienteController.class).updateCliente(user.getId(), user)).withRel("PUT"),
                linkTo(methodOn(ClienteController.class).removeCliente(user.getId())).withRel("DELETE")
        );
    }
}
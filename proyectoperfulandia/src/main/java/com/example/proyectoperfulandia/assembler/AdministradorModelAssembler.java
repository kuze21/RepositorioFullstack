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

    /*
    RepresentionalModel = Permite a la clase contener una lista de Links de acceso
    EntityModel = Es un contenedor generico que adjunta la entidad mas una serie de enlaces
    LinkTo = Es un metodo que nos permite construir los Links o URL's de acceso que posee nuestro controlador
    */
    @Override
    public EntityModel<Administrador> toModel(Administrador user) {
        return EntityModel.of(user,
                linkTo(methodOn(AdministradorController.class).getAdmin(user.getId())).withSelfRel(),
                linkTo(methodOn(AdministradorController.class).getAdmins()).withRel("administradores"),
                linkTo(methodOn(AdministradorController.class).updateAdmin(user.getId(), user)).withRel("PUT"),
                linkTo(methodOn(AdministradorController.class).removeAdmin(user.getId())).withRel("DELETE")
        );
    }
}
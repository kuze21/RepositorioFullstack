package com.example.proyectoperfulandia.assembler;

import com.example.proyectoperfulandia.controller.UsuarioController;
import com.example.proyectoperfulandia.model.Usuario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
// UserModelAssembler.java
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    /*
    RepresentionalModel = Permite a la clase contener una lista de Links de acceso
    EntityModel = Es un contenedor generico que adjunta la entidad mas una serie de enlaces
    LinkTo = Es un metodo que nos permite construir los Links o URL's de acceso que posee nuestro controlador
    */
    @Override
    public EntityModel<Usuario> toModel(Usuario user) {
        return EntityModel.of(user,
                linkTo(methodOn(UsuarioController.class).getUsuario(user.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).getUsuarios()).withRel("users"),
                linkTo(methodOn(UsuarioController.class).updateUsuario(user.getId(), user)).withRel("PUT"),
                linkTo(methodOn(UsuarioController.class).removeUsuario(user.getId())).withRel("DELETE")
        );
    }
}
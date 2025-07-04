package com.example.proyectoperfulandia.assembler;

import com.example.proyectoperfulandia.controller.UsuarioController;
import com.example.proyectoperfulandia.model.Usuario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
// UsuarioModelAssembler.java
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    public EntityModel<Usuario> toModel(Usuario user) {
        return EntityModel.of(user,
                // Enlace a sí mismo - permite obtener este usuario específico
                linkTo(methodOn(UsuarioController.class).getUsuario(user.getId())).withSelfRel(),
                // Enlace a la colección - permite navegar a todos los usuario
                linkTo(methodOn(UsuarioController.class).getUsuarios()).withRel("usuarios"),
                // Enlace para actualizar - permite modificar este usuario
                linkTo(methodOn(UsuarioController.class).updateUsuario(user.getId(), user)).withRel("PUT"),
                // Enlace para eliminar - permite borrar este usuario
                linkTo(methodOn(UsuarioController.class).removeUsuario(user.getId())).withRel("DELETE")
        );
    }
}
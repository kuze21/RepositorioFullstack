package com.example.proyectoperfulandia.assembler;

import com.example.proyectoperfulandia.controller.EmpleadoController;
import com.example.proyectoperfulandia.model.Empleado;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
// EmpleadoModelAssembler.java
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EmpleadoModelAssembler implements RepresentationModelAssembler<Empleado, EntityModel<Empleado>> {

    /*
    RepresentionalModel = Permite a la clase contener una lista de Links de acceso
    EntityModel = Es un contenedor generico que adjunta la entidad mas una serie de enlaces
    LinkTo = Es un metodo que nos permite construir los Links o URL's de acceso que posee nuestro controlador
    */
    @Override
    public EntityModel<Empleado> toModel(Empleado user) {
        return EntityModel.of(user,
                linkTo(methodOn(EmpleadoController.class).getEmpleado(user.getId())).withSelfRel(),
                linkTo(methodOn(EmpleadoController.class).getEmpleados()).withRel("empleados"),
                linkTo(methodOn(EmpleadoController.class).updateEmpleado(user.getId(), user)).withRel("PUT"),
                linkTo(methodOn(EmpleadoController.class).removeEmpleado(user.getId())).withRel("DELETE")
        );
    }
}
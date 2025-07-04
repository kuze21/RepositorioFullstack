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

    @Override
    public EntityModel<Empleado> toModel(Empleado user) {
        return EntityModel.of(user,
                // Enlace a sí mismo - permite obtener este usuario específico
                linkTo(methodOn(EmpleadoController.class).getEmpleado(user.getId())).withSelfRel(),
                // Enlace a la colección - permite navegar a todos los usuario
                linkTo(methodOn(EmpleadoController.class).getEmpleados()).withRel("empleados"),
                // Enlace para actualizar - permite modificar este usuario
                linkTo(methodOn(EmpleadoController.class).updateEmpleado(user.getId(), user)).withRel("PUT"),
                // Enlace para eliminar - permite borrar este usuario
                linkTo(methodOn(EmpleadoController.class).removeEmpleado(user.getId())).withRel("DELETE")
        );
    }
}
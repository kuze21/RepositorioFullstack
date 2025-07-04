package com.example.proyectoperfulandia.assembler;

import com.example.proyectoperfulandia.controller.ProductoController;
import com.example.proyectoperfulandia.model.Producto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
// ProductoModelAssembler.java
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {

    @Override
    public EntityModel<Producto> toModel(Producto producto) {
        return EntityModel.of(producto,
                // Enlace a sí mismo - permite obtener este producto específico
                linkTo(methodOn(ProductoController.class).getProducto(producto.getId())).withSelfRel(),
                // Enlace a la colección - permite navegar a todos los productos
                linkTo(methodOn(ProductoController.class).getProductos()).withRel("productos"),
                // Enlace para actualizar - permite modificar este producto
                linkTo(methodOn(ProductoController.class).updateProducto(producto.getId(), producto)).withRel("PUT"),
                // Enlace para eliminar - permite borrar este producto
                linkTo(methodOn(ProductoController.class).removeProducto(producto.getId())).withRel("DELETE")
        );
    }
}
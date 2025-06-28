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

    /*
    RepresentionalModel = Permite a la clase contener una lista de Links de acceso
    EntityModel = Es un contenedor generico que adjunta la entidad mas una serie de enlaces
    LinkTo = Es un metodo que nos permite construir los Links o URL's de acceso que posee nuestro controlador
    */
    @Override
    public EntityModel<Producto> toModel(Producto producto) {
        return EntityModel.of(producto,
                linkTo(methodOn(ProductoController.class).getProductoById(producto.getId())).withSelfRel(),
                linkTo(methodOn(ProductoController.class).getProductos()).withRel("productos"),
                linkTo(methodOn(ProductoController.class).updateProductoById(producto.getId(), producto)).withRel("PUT"),
                linkTo(methodOn(ProductoController.class).deleteProductoById(producto.getId())).withRel("DELETE")
        );
    }
}
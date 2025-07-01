package com.example.proyectoperfulandia.controller;

import com.example.proyectoperfulandia.assembler.ProductoModelAssembler;
import com.example.proyectoperfulandia.model.Producto;
import com.example.proyectoperfulandia.services.ProductoService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@Tag(name = "Producto", description = "API REST para administrar los productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    ProductoModelAssembler assembler;

    // Obtener todos los productos
    @GetMapping
    @Operation(summary = "Obtener listado de todos los productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de usuarios generado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Listado no encontrado.")
    })
    public ResponseEntity<CollectionModel<EntityModel<Producto>>> getProductos() {
        List<Producto> productos = productoService.getProductos();
        if (productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(assembler.toCollectionModel(productos), HttpStatus.OK);
    }

    // Añadir producto
    @PostMapping
    @Operation(summary = "Agregar producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto agregado exitosamente.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud.")
    })
    public ResponseEntity<EntityModel<Producto>> addProducto(
            @Parameter(description = "Datos del producto a agregar", required = true)
            @RequestBody Producto producto) {
        try {
            productoService.addProducto(producto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Obtiene los productos y sus datos mediante ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto mediante ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado.")
    })
    public ResponseEntity<EntityModel<Producto>> getProducto(
            @Parameter(description = "ID del producto a obtener", required = true, example = "1")
            @PathVariable int id){
        return productoService.getProducto(id)
                .map(producto -> new ResponseEntity<>(assembler.toModel(producto), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Elimina los productos y sus datos mediante ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado.")
    })
    public ResponseEntity<?> removeProducto(
            @Parameter(description = "ID del producto a eliminar", required = true, example = "1")
            @PathVariable int id){
        if (!productoService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productoService.removeProducto(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Actualiza los productos y sus datos mediante ID
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar datos de un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud.")
    })
    public ResponseEntity<EntityModel<Producto>> updateProducto(
            @Parameter(description = "ID del producto a actualizar", required = true, example = "1")
            @PathVariable int id,
            @Parameter(description = "Datos del producto a actualizar", required = true)
            @RequestBody Producto producto) {
        if (!productoService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        producto.setId(id);
        productoService.updateProducto(id, producto);
        return new ResponseEntity<>(assembler.toModel(producto), HttpStatus.OK);
    }

}

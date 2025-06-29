package com.example.proyectoperfulandia.controller;

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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
@Tag(name = "Producto", description = "API REST para administrar los productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Obtener todos los productos
    @GetMapping
    @Operation(summary = "Obtener listado de todos los productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de usuarios generado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Listado no encontrado.")
    })
    public String getProductos() {
        return productoService.listarProducto();
    }

    // Añadir producto
    @PostMapping
    @Operation(summary = "Agregar producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto agregado exitosamente.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud.")
    })
    public String addProducto(@RequestBody Producto producto) {
        return productoService.agregarProducto(producto);
    }

    // Obtiene los productos y sus datos mediante ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto mediante ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado.")
    })
    @Parameter(description = "ID del Producto a obtener", required = true, example = "1")
    public String getProductoById(@PathVariable int id) {
        return productoService.obtenerProductoID(id);
    }

    // Elimina los productos y sus datos mediante ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado.")
    })
    @Parameter(description = "ID del producto a eliminar", required = true, example = "1")
    public String deleteProductoById(@PathVariable int id) {
        return productoService.eliminarProducto(id);
    }

    // Actualiza los productos y sus datos mediante ID
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar datos de un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente.", content = @Content(mediaType="application/json",
                    schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud.")
    })
    @Parameter(description = "ID del Producto a actualizar", required = true, example = "1")
    public String updateProductoById(@PathVariable int id, @RequestBody Producto producto) {
        return productoService.actualizarProducto(id, producto);
    }

}

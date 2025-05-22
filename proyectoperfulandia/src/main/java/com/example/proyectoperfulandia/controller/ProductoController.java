package com.example.proyectoperfulandia.controller;

import com.example.proyectoperfulandia.model.Producto;
import com.example.proyectoperfulandia.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Obtener todos los productos
    @GetMapping
    public String getProductos() {
        return productoService.listarProducto();
    }

    // Añadir producto
    @PostMapping
    public String addProducto(@RequestBody Producto producto) {
        return productoService.agregarProducto(producto);
    }

    // Obtiene los productos y sus datos mediante ID
    @GetMapping("/{id}")
    public String getProductoById(@PathVariable int id) {
        return productoService.obtenerProductoID(id);
    }

    // Elimina los productos y sus datos mediante ID
    @DeleteMapping("/{id}")
    public String deleteProductoById(@PathVariable int id) {
        return productoService.eliminarProducto(id);
    }

    // Actualiza los productos y sus datos mediante ID
    @PutMapping("/{id}")
    public String updateProductoById(@PathVariable int id, @RequestBody Producto producto) {
        return productoService.actualizarProducto(id, producto);
    }

}

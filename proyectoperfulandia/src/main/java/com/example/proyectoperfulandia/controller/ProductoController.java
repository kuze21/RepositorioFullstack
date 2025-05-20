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

    @GetMapping
    public String getProductos() {
        return productoService.listarProducto();
    }

    @PostMapping
    public String addProducto(@RequestBody Producto producto) {
        return productoService.agregarProducto(producto);
    }

    @GetMapping("/{id}")
    public String getProductoById(@PathVariable int id) {
        return productoService.obtenerProductoID(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProductoById(@PathVariable int id) {
        return productoService.eliminarProducto(id);
    }

    @PutMapping("/{id}")
    public String updateProductoById(@PathVariable int id, @RequestBody Producto producto) {
        return productoService.actualizarProducto(id, producto);
    }

}

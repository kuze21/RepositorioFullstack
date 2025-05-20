package com.example.proyectoperfulandia.services;

import com.example.proyectoperfulandia.model.Producto;
import com.example.proyectoperfulandia.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    public String agregarProducto(Producto producto) {
        productoRepository.save(producto);
        return "Producto agregado correctamente.";
    }

    public String listarProducto() {
        String output = "";
        for (Producto producto : productoRepository.findAll()) {
            output += "ID Producto: "+ producto.getId() + "\n";
            output += "Nombre: "+ producto.getNombre() + "\n";
            output += "Categoría: "+ producto.getCategoria() + "\n";
            output += "Precio: $"+ producto.getPrecio() + "\n";
            output += "Stock: "+ producto.getStock() + "\n";
        }
        if (output.isEmpty()){
            return "No se encontraron productos.";
        } else {
            return output;
        }
    }

    public String obtenerProductoID(int id) {
        String output = "";
        if (productoRepository.existsById(id)) {
            Producto producto = productoRepository.findById(id).get();
            output += "ID Producto: "+ producto.getId() + "\n";
            output += "Nombre: "+ producto.getNombre() + "\n";
            output += "Categoría: "+ producto.getCategoria() + "\n";
            output += "Precio: $"+ producto.getPrecio() + "\n";
            output += "Stock: "+ producto.getStock() + "\n";
            return output;
        } else {
            return "No se encontraron productos.";
        }
    }

    public String eliminarProducto(int id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return "Producto eliminado correctamente.";
        } else {
            return "No se encontraron productos.";
        }
    }

    public String actualizarProducto(int id, Producto producto) {
        if (productoRepository.existsById(id)) {
            Producto productoActual = productoRepository.findById(id).get();
            productoActual.setNombre(producto.getNombre());
            productoActual.setCategoria(producto.getCategoria());
            productoActual.setPrecio(producto.getPrecio());
            productoActual.setStock(producto.getStock());
            productoRepository.save(productoActual);
            return "Producto actualizado correctamente.";
        } else {
            return "No se encontraron productos.";
        }
    }
}

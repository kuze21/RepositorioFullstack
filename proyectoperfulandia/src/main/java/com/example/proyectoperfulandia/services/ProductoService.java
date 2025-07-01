package com.example.proyectoperfulandia.services;

import com.example.proyectoperfulandia.model.Producto;
import com.example.proyectoperfulandia.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    // Obtener todos los productos
    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    // Añadir un producto
    public void addProducto(Producto user) {
        productoRepository.save(user);
    }

    // Obtiene los productos y sus datos mediante ID
    public Optional<Producto> getProducto(int id) {
        return productoRepository.findById(id);
    }

    // Elimina los productos mediante ID
    public void removeProducto(int id) {
        productoRepository.deleteById(id);
    }
    public boolean existsById(int id) {
        return productoRepository.existsById(id);
    }

    // Actualiza los productos y sus datos mediante ID
    public void updateProducto(int id, Producto producto) {
        Producto us = productoRepository.findById(id).get();
        us.setNombre(producto.getNombre());
        us.setPrecio(producto.getPrecio());
        us.setStock(producto.getStock());
        us.setCategoria(producto.getCategoria());
        productoRepository.save(us);
    }
}

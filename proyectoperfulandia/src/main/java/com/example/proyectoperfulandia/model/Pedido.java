package com.example.proyectoperfulandia.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha;

    @Enumerated(EnumType.STRING)
    private EnumEstado estado;

    private double total;

    @ManyToOne(fetch = FetchType.LAZY) // Evita cargar cliente
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }) // PERSIST: para que cuando se guarde el pedido, también lo hagan sus productos. MERGE: Para actualizar las entidades relacionadas
    @JoinTable(
            name = "pedido_producto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> listaProductos = new ArrayList<>();

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Logistica logistica;

}

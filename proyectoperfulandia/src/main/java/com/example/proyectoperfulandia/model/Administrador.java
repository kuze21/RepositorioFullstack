package com.example.proyectoperfulandia.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
@Table(name = "administradores")
public class Administrador extends Usuario {
}

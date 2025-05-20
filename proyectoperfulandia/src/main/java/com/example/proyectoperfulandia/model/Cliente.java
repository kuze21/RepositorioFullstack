package com.example.proyectoperfulandia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass

@Entity
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Cliente extends Usuario {
}


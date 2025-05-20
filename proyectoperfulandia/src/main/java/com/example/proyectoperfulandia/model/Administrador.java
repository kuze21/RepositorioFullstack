package com.example.proyectoperfulandia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass

@Entity
public class Administrador extends Usuario {
}

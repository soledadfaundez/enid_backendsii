package com.prueba.mantenedor.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // Constructor vacío
@AllArgsConstructor // Constructor con todos los campos
@Entity
@Getter
@Setter
@Table(name = "`usuario`") // SFC: Hibernate reclama que no está definido el nombre de la entidad.
@EntityListeners(AuditingEntityListener.class) // ESFC: Habilitar auditoría automática
public class Usuario {

    // ESFC: Configurar la llave como un UUID
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    // ESFC: Propiedades
    // EFC: Pendiente, agregar los largos de los campos.
    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private boolean cuentaActiva;

    @Column(nullable = false)
    private LocalDateTime lastLogin;

    // ESFC: Campos de auditoría automáticos
    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedBy
    private String updatedBy;

    @LastModifiedDate
    private LocalDateTime updatedDate;

}

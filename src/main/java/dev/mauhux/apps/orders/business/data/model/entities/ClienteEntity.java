package dev.mauhux.apps.orders.business.data.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "CLIENTES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTES_SEQ")
    @SequenceGenerator(name = "CLIENTES_SEQ", sequenceName = "CLIENTES_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    private String nombres;

    private String apellidos;

    private String email;

    private String direccion;

    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;
}

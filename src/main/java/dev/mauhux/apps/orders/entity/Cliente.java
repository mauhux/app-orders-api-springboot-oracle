package dev.mauhux.apps.orders.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CLIENTES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTES_SEQ")
    @SequenceGenerator(name = "CLIENTES_SEQ", sequenceName = "CLIENTES_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOMBRES")
    private String nombres;

    @Column(name = "APELLIDOS")
    private String apellidos;
}

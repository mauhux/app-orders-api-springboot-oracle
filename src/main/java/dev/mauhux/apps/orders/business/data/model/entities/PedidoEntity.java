package dev.mauhux.apps.orders.business.data.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PEDIDOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PEDIDOS_SEQ")
    @SequenceGenerator(name = "PEDIDOS_SEQ", sequenceName = "PEDIDOS_SEQ", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID", nullable = false)
    private ClienteEntity cliente;

    private LocalDate fecha;

    private String estado;

    private BigDecimal total;

    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<PedidoDetalleEntity> detalles;
}

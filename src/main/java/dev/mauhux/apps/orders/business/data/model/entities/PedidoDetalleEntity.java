package dev.mauhux.apps.orders.business.data.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PEDIDO_DETALLES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoDetalleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PEDIDOS_SEQ")
    @SequenceGenerator(name = "PEDIDOS_SEQ", sequenceName = "PEDIDOS_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PEDIDO_ID", nullable = false)
    private PedidoEntity pedido;

    @ManyToOne
    @JoinColumn(name = "PRODUCTO_ID", nullable = false)
    private ProductoEntity producto;

    private BigDecimal cantidad;

    private BigDecimal subtotal;

    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;
}

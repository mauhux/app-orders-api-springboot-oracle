package dev.mauhux.apps.orders.business.data.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "V_REPORTE_PEDIDOS_CLIENTE")
@Data
@Immutable
public class PedidoClienteReportVo {

    @Id
    @Column(name = "ID_PEDIDO")
    private Integer idPedido;

    @Column(name = "ID_CLIENTE")
    private Integer idCliente;

    @Column(name = "NOMBRES")
    private String nombres;

    @Column(name = "APELLIDOS")
    private String apellidos;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "FECHA_PEDIDO")
    private LocalDate fechaPedido;

    @Column(name = "ESTADO_PEDIDO")
    private String estadoPedido;

    @Column(name = "ID_PRODUCTO")
    private Integer idProducto;

    @Column(name = "DESCRIPCION_PRODUCTO")
    private String descripcionProducto;

    @Column(name = "PRECIO_UNITARIO")
    private BigDecimal precioUnitario;

    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;

    @Column(name = "SUBTOTAL")
    private BigDecimal subtotal;

    @Column(name = "TOTAL_PEDIDO")
    private BigDecimal totalPedido;
}

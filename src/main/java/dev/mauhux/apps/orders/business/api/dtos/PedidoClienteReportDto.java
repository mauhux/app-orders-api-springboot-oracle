package dev.mauhux.apps.orders.business.api.dtos;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record PedidoClienteReportDto(
        Integer idPedido,
        Integer idCliente,
        String nombres,
        String apellidos,
        String email,
        LocalDate fechaPedido,
        String estadoPedido,
        Integer idProducto,
        String descripcionProducto,
        BigDecimal cantidad,
        BigDecimal subtotal,
        BigDecimal totalPedido
) {
}

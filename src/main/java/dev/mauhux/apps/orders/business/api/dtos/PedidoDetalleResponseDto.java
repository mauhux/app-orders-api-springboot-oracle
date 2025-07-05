package dev.mauhux.apps.orders.business.api.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public record PedidoDetalleResponseDto(
        Integer id,
        @JsonIgnore
        PedidoDetalleResponseDto pedido,
        ProductoResponseDto producto,
        BigDecimal cantidad,
        BigDecimal subtotal
) {
}

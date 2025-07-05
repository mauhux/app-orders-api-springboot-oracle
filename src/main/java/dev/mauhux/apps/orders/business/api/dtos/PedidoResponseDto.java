package dev.mauhux.apps.orders.business.api.dtos;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
public record PedidoResponseDto(
        Integer id,
        ClienteResponseDto cliente,
        LocalDate fecha,
        BigDecimal total,
        String estado,
        List<PedidoDetalleResponseDto> detalles
) {
}

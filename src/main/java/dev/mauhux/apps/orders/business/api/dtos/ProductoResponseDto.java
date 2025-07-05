package dev.mauhux.apps.orders.business.api.dtos;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record ProductoResponseDto(
        Integer id,
        String descripcion,
        BigDecimal precioUnitario,
        BigDecimal stock,
        String estado,
        LocalDateTime fechaCreacion
) {
}

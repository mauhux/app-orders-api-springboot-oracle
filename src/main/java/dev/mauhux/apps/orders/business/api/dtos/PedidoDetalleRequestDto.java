package dev.mauhux.apps.orders.business.api.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PedidoDetalleRequestDto(
        @NotNull(message = "El Producto es obligatorio")
        Integer productoId,
        @NotNull(message = "La cantidad es obligatoria")
        @DecimalMin(value = "0.01", message = "La cantidad tiene que ser mayor a cero")
        BigDecimal cantidad
) {
}

package dev.mauhux.apps.orders.business.api.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

@Builder
public record PedidoRequestDto(
        @NotNull(message = "El cliente es obligatorio")
        Integer clienteId,
        @Size(min = 1, message = "Debe haber al menos un detalle")
        @Valid
        List<PedidoDetalleRequestDto> detalles
) {
}

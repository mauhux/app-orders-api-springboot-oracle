package dev.mauhux.apps.orders.business.api.dtos;

import lombok.Builder;

@Builder
public record ClienteResponseDto(
        Integer id,
        String nombres,
        String apellidos
) {
}

package dev.mauhux.apps.orders.business.api.dtos;

import lombok.Builder;

@Builder
public record ClienteDto(

        Integer id,
        String nombres,
        String apellidos
) {
}

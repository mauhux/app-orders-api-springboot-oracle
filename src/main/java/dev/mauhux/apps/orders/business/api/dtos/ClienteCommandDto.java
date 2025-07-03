package dev.mauhux.apps.orders.business.api.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record ClienteCommandDto(

        @NotEmpty(message = "El campo nombres no puede estar vacío")
        String nombres,

        @NotEmpty(message = "El campo apellidos no puede estar vacío")
        String apellidos
) {
}

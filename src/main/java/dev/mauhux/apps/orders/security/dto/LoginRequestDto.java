package dev.mauhux.apps.orders.security.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record LoginRequestDto(
        @NotEmpty(message = "El userName es obligatorio")
        String userName,
        @NotEmpty(message = "El password es obligatorio")
        String password
) {
}

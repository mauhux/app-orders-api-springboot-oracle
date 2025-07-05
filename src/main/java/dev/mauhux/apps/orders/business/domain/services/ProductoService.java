package dev.mauhux.apps.orders.business.domain.services;

import dev.mauhux.apps.orders.business.api.dtos.ProductoResponseDto;

import java.util.List;

public interface ProductoService {

    List<ProductoResponseDto> getProductosFromProcedure();

    List<ProductoResponseDto> getProductosFromNativeQuery();
}

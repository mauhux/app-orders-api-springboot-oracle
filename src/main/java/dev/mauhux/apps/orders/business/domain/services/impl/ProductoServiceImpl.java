package dev.mauhux.apps.orders.business.domain.services.impl;

import dev.mauhux.apps.orders.business.api.dtos.ProductoResponseDto;
import dev.mauhux.apps.orders.business.data.repositories.ProductoRepository;
import dev.mauhux.apps.orders.business.domain.mappers.ProductoMapper;
import dev.mauhux.apps.orders.business.domain.services.ProductoService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Transactional
    @Override
    public List<ProductoResponseDto> getProductosFromProcedure() {
        return productoRepository
                .findAllProductosFromProcedure()
                .stream()
                .map(productoMapper::toDto)
                .toList();
    }

    @Override
    public List<ProductoResponseDto> getProductosFromNativeQuery() {
        return productoRepository
                .findAllProductosFromNativeQuery()
                .stream()
                .map(productoMapper::toDto)
                .toList();
    }
}

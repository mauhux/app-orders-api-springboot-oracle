package dev.mauhux.apps.orders.business.domain.mappers;

import dev.mauhux.apps.orders.business.api.dtos.ProductoResponseDto;
import dev.mauhux.apps.orders.business.data.model.entities.ProductoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoResponseDto toDto(ProductoEntity productoEntity);
}

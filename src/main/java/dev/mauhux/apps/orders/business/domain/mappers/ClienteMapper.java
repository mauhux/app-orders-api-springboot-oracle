package dev.mauhux.apps.orders.business.domain.mappers;

import dev.mauhux.apps.orders.business.api.dtos.ClienteRequestDto;
import dev.mauhux.apps.orders.business.api.dtos.ClienteResponseDto;
import dev.mauhux.apps.orders.business.data.model.entities.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(source = "nombres", target = "nombres")
    ClienteResponseDto toDto(ClienteEntity clienteEntity);

    @Mapping(target = "id", ignore = true)
    ClienteEntity toEntity(ClienteRequestDto clienteRequestDto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(ClienteRequestDto clienteRequestDto,
                             @MappingTarget ClienteEntity clienteEntity);

}

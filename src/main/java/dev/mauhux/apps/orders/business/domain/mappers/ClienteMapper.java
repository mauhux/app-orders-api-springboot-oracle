package dev.mauhux.apps.orders.business.domain.mappers;

import dev.mauhux.apps.orders.business.api.dtos.ClienteCommandDto;
import dev.mauhux.apps.orders.business.api.dtos.ClienteDto;
import dev.mauhux.apps.orders.business.data.model.entities.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(source = "nombres", target = "nombres")
    ClienteDto toDto(ClienteEntity clienteEntity);

    @Mapping(target = "id", ignore = true)
    ClienteEntity toEntity(ClienteCommandDto clienteCommandDto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(ClienteCommandDto clienteCommandDto,
                             @MappingTarget ClienteEntity clienteEntity);

}

package dev.mauhux.apps.orders.business.domain.mappers;

import dev.mauhux.apps.orders.business.api.dtos.PedidoClienteReportDto;
import dev.mauhux.apps.orders.business.api.dtos.PedidoDetalleResponseDto;
import dev.mauhux.apps.orders.business.api.dtos.PedidoResponseDto;
import dev.mauhux.apps.orders.business.data.model.entities.PedidoClienteReportVo;
import dev.mauhux.apps.orders.business.data.model.entities.PedidoDetalleEntity;
import dev.mauhux.apps.orders.business.data.model.entities.PedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    PedidoResponseDto toDto(PedidoEntity pedidoEntity);

    @Mapping(target = "pedido", ignore = true)
    PedidoDetalleResponseDto toDto(PedidoDetalleEntity pedidoDetalleEntity);

    PedidoClienteReportDto toReportDto(PedidoClienteReportVo pedidoClienteReportVo);
}

package dev.mauhux.apps.orders.business.domain.services;

import dev.mauhux.apps.orders.business.api.dtos.PedidoClienteReportDto;
import dev.mauhux.apps.orders.business.api.dtos.PedidoRequestDto;
import dev.mauhux.apps.orders.business.api.dtos.PedidoResponseDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PedidoService {

    List<PedidoResponseDto> findAllByDatesAndEstado(LocalDate desde, LocalDate hasta, String estado);

    Optional<PedidoResponseDto> findById(Integer id);

    PedidoResponseDto create(PedidoRequestDto pedidoRequestDto);

    Boolean cancelPedido(Integer id);

    Boolean confirmStockPedido(Integer id);

    Boolean confirmPayPedido(Integer id);

    Boolean preparingPedido(Integer id);

    Boolean readyToDelivery(Integer id);

    Boolean deliveredPedido(Integer id);

    List<PedidoClienteReportDto> getReporte();
}

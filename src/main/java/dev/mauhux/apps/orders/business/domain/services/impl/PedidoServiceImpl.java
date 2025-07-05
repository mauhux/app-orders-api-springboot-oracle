package dev.mauhux.apps.orders.business.domain.services.impl;

import dev.mauhux.apps.orders.business.api.dtos.PedidoClienteReportDto;
import dev.mauhux.apps.orders.business.api.dtos.PedidoRequestDto;
import dev.mauhux.apps.orders.business.api.dtos.PedidoResponseDto;
import dev.mauhux.apps.orders.business.api.exceptions.NotFoundException;
import dev.mauhux.apps.orders.business.commons.EstadoConstants;
import dev.mauhux.apps.orders.business.data.model.entities.PedidoDetalleEntity;
import dev.mauhux.apps.orders.business.data.model.entities.PedidoEntity;
import dev.mauhux.apps.orders.business.data.repositories.ClienteRepository;
import dev.mauhux.apps.orders.business.data.repositories.PedidoClienteReportRepository;
import dev.mauhux.apps.orders.business.data.repositories.PedidoRepository;
import dev.mauhux.apps.orders.business.data.repositories.ProductoRepository;
import dev.mauhux.apps.orders.business.domain.mappers.PedidoMapper;
import dev.mauhux.apps.orders.business.domain.services.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PedidoServiceImpl implements PedidoService {

    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    private final PedidoClienteReportRepository pedidoClienteReportRepository;
    private final PedidoMapper pedidoMapper;

    @Override
    public List<PedidoResponseDto> findAllByDatesAndEstado(LocalDate desde, LocalDate hasta, String estado) {
        return pedidoRepository
                .findByFechaBetweenAndEstado(desde, hasta, estado)
                .stream()
                .map(pedidoMapper::toDto)
                .toList();
    }

    @Override
    public Optional<PedidoResponseDto> findById(Integer id) {
        return Optional.ofNullable(
                pedidoRepository
                        .findById(id)
                        .map(pedidoMapper::toDto)
                        .orElseThrow(() -> new NotFoundException("Pedido no encontrado"))
        );
    }

    @Override
    public PedidoResponseDto create(PedidoRequestDto pedidoRequestDto) {
        var pedidoEntity = new PedidoEntity();

        var pedidoDetalleEntities = pedidoRequestDto
                .detalles()
                .stream()
                .map(detalleDto -> {
                    var producto = productoRepository
                            .findById(detalleDto.productoId())
                            .orElseThrow(() -> new NotFoundException("Producto no encontrado"));
                    var subTotal = detalleDto.cantidad().multiply(producto.getPrecioUnitario());

                    return PedidoDetalleEntity.builder()
                            .subtotal(subTotal)
                            .cantidad(detalleDto.cantidad())
                            .producto(producto)
                            .pedido(pedidoEntity)
                            .fechaCreacion(LocalDateTime.now())
                            .build();
                })
                .toList();

        var total = pedidoDetalleEntities
                .stream()
                .map(PedidoDetalleEntity::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var cliente = clienteRepository
                .findById(pedidoRequestDto.clienteId())
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

        pedidoEntity.setCliente(cliente);
        pedidoEntity.setTotal(total);
        pedidoEntity.setFecha(LocalDate.now());
        pedidoEntity.setFechaCreacion(LocalDateTime.now());
        pedidoEntity.setEstado(EstadoConstants.GENERADO);
        pedidoEntity.setDetalles(pedidoDetalleEntities);

        var pedidoSaved = pedidoRepository.save(pedidoEntity);
        return pedidoMapper.toDto(pedidoSaved);
    }

    @Override
    public Boolean cancelPedido(Integer id) {
        return pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setEstado(EstadoConstants.CANCELADO);
                    return pedidoRepository.save(pedido);
                })
                .isPresent();
    }

    @Override
    public Boolean confirmStockPedido(Integer id) {
        return pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setEstado(EstadoConstants.STOCK_CONFIRMADO);
                    return pedidoRepository.save(pedido);
                })
                .isPresent();
    }

    @Override
    public Boolean confirmPayPedido(Integer id) {
        return pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setEstado(EstadoConstants.PAGO_CONFIRMADO);
                    return pedidoRepository.save(pedido);
                })
                .isPresent();
    }

    @Override
    public Boolean preparingPedido(Integer id) {
        return pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setEstado(EstadoConstants.PREPARANDO);
                    return pedidoRepository.save(pedido);
                })
                .isPresent();
    }

    @Override
    public Boolean readyToDelivery(Integer id) {
        return pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setEstado(EstadoConstants.LISTO);
                    return pedidoRepository.save(pedido);
                })
                .isPresent();
    }

    @Override
    public Boolean deliveredPedido(Integer id) {
        return pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setEstado(EstadoConstants.ENTREGADO);
                    pedido.getDetalles().forEach(item -> {
                        var producto = item.getProducto();
                        producto.setStock(producto.getStock().subtract(item.getCantidad()));
                        productoRepository.save(producto);
                    });
                    return pedidoRepository.save(pedido);
                })
                .isPresent();
    }

    @Override
    public List<PedidoClienteReportDto> getReporte() {
        return pedidoClienteReportRepository
                .findAll()
                .stream()
                .map(pedidoMapper::toReportDto)
                .toList();
    }
}

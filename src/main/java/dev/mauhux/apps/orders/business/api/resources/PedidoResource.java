package dev.mauhux.apps.orders.business.api.resources;

import dev.mauhux.apps.orders.business.api.dtos.PedidoRequestDto;
import dev.mauhux.apps.orders.business.api.dtos.PedidoResponseDto;
import dev.mauhux.apps.orders.business.domain.services.PedidoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class PedidoResource {

    private final PedidoService pedidoService;

    @GetMapping("/from-range-and-estado")
    public ResponseEntity<?> findAllByDatesAndEstado(@RequestParam("desde") String desde,
                                                     @RequestParam("hasta") String hasta,
                                                     @RequestParam("estado") String estado) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            List<PedidoResponseDto> pedidos = pedidoService.findAllByDatesAndEstado(
                    LocalDate.parse(desde, formatter),
                    LocalDate.parse(hasta, formatter),
                    StringUtils.isBlank(estado) ? null : estado
            );
            if (pedidos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            log.error("Error al obtener los pedidos", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDto> findById(@PathVariable Integer id) {
        try {
            Optional<PedidoResponseDto> pedido = pedidoService.findById(id);
            if (pedido.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(pedido.get());
        } catch (Exception e) {
            log.error("Error al obtener el pedido", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody PedidoRequestDto pedidoCreateDTO) {
        try {
            PedidoResponseDto pedidoResponseDto = pedidoService.create(pedidoCreateDTO);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(pedidoResponseDto.id())
                    .toUri();

            return ResponseEntity.created(location).body(pedidoResponseDto);
        } catch (Exception e) {
            log.error("Error al crear pedido", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Boolean> cancel(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(pedidoService.cancelPedido(id));
        } catch (Exception e) {
            log.error("Error al cancelar pedido:", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}/confirm-stock")
    public ResponseEntity<Boolean> confirmStock(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(pedidoService.confirmStockPedido(id));
        } catch (Exception e) {
            log.error("Error al confirmar pedido:", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}/delivered")
    public ResponseEntity<Boolean> delivered(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(pedidoService.deliveredPedido(id));
        } catch (Exception e) {
            log.error("Error al entregar pedido:", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

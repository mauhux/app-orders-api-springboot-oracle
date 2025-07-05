package dev.mauhux.apps.orders.business.api.resources;

import dev.mauhux.apps.orders.business.api.dtos.ProductoResponseDto;
import dev.mauhux.apps.orders.business.domain.services.ProductoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoResource {

    private final ProductoService productoService;

    @GetMapping("/procedure")
    public ResponseEntity<List<ProductoResponseDto>> getClientesFromProcedure() {
        try {
            List<ProductoResponseDto> clientes = productoService.getProductosFromProcedure();
            if (clientes.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            log.error("Error getting productos from procedure.", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/native-query")
    public ResponseEntity<List<ProductoResponseDto>> getClientesFromNativeQuery() {
        try {
            List<ProductoResponseDto> clientes = productoService.getProductosFromNativeQuery();
            if (clientes.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            log.error("Error getting productos from native query.", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

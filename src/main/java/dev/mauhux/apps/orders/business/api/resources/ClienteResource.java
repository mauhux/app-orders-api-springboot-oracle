package dev.mauhux.apps.orders.business.api.resources;

import dev.mauhux.apps.orders.business.api.dtos.ClienteCommandDto;
import dev.mauhux.apps.orders.business.api.dtos.ClienteDto;
import dev.mauhux.apps.orders.business.domain.services.ClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteResource {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getClientes() {
        try {
            List<ClienteDto> clientes = clienteService.getClientes();
            if (clientes.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            log.error("Error getting clientes.", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable Integer id) {
        try {
            Optional<ClienteDto> cliente = clienteService.getClienteById(id);
            return cliente
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.noContent().build());
        } catch (Exception e) {
            log.error("Error getting cliente by id {}.", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<ClienteDto> create(@Valid @RequestBody ClienteCommandDto clienteCommandDto) {
        try {
            ClienteDto clienteDto = clienteService.createCliente(clienteCommandDto);
            URI location = URI.create(String.format("/clientes/%s", clienteDto.id()));
            return ResponseEntity
                    .created(location)
                    .body(clienteDto);
        } catch (Exception e) {
            log.error("Error creating cliente.", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> update(@Valid @RequestBody ClienteCommandDto clienteCommandDto,
                                             @PathVariable Integer id) {
        try {
            ClienteDto clienteDto = clienteService.updateCliente(clienteCommandDto, id);
            return ResponseEntity.ok(clienteDto);
        } catch (Exception e) {
            log.error("Error updating cliente.", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            clienteService.deleteCliente(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error deleting cliente.", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

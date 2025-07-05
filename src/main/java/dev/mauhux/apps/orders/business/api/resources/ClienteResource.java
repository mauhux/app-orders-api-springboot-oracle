package dev.mauhux.apps.orders.business.api.resources;

import dev.mauhux.apps.orders.business.api.dtos.ClienteRequestDto;
import dev.mauhux.apps.orders.business.api.dtos.ClienteResponseDto;
import dev.mauhux.apps.orders.business.domain.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
@Tag(name = "Clientes", description = "API REST para gestionar los clientes")
public class ClienteResource {

    private final ClienteService clienteService;

    @Operation(
            summary = "Obtener todos los clientes",
            description = "Permite consultar todos los clientes registrados",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista obtenida correctamente.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ClienteResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No existen clientes registrados."
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor."
                    )
            },
            operationId = "getClientes"
    )

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> getClientes() {
        try {
            List<ClienteResponseDto> clientes = clienteService.getClientes();
            if (clientes.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            log.error("Error getting clientes.", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(
            summary = "Obtener cliente por ID",
            description = "Fetches a customer by their unique identifier",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customer found successfully.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ClienteResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No customer found with the given ID."
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error."
                    )
            },
            operationId = "getClienteById"
    )

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> getClienteById(@PathVariable Integer id) {
        try {
            Optional<ClienteResponseDto> cliente = clienteService.getClienteById(id);
            return cliente
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.noContent().build());
        } catch (Exception e) {
            log.error("Error getting cliente by id {}.", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(
            summary = "Create a new customer",
            description = "Creates a new customer based on the provided customer data",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Customer created successfully.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ClienteResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input data."
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error."
                    )
            },
            operationId = "createCliente"
    )

    @PostMapping
    public ResponseEntity<ClienteResponseDto> create(@Valid @RequestBody ClienteRequestDto clienteRequestDto) {
        try {
            ClienteResponseDto clienteResponseDto = clienteService.createCliente(clienteRequestDto);
            URI location = URI.create(String.format("/clientes/%s", clienteResponseDto.id()));
            return ResponseEntity
                    .created(location)
                    .body(clienteResponseDto);
        } catch (Exception e) {
            log.error("Error creating cliente.", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(
            summary = "Update an existing customer",
            description = "Updates the details of an existing customer with the given ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customer updated successfully.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ClienteResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input data."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Customer not found with the given ID."
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error."
                    )
            },
            operationId = "updateCliente"
    )

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> update(@Valid @RequestBody ClienteRequestDto clienteRequestDto,
                                                     @PathVariable Integer id) {
        try {
            ClienteResponseDto clienteResponseDto = clienteService.updateCliente(clienteRequestDto, id);
            return ResponseEntity.ok(clienteResponseDto);
        } catch (Exception e) {
            log.error("Error updating cliente.", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(
            summary = "Delete a customer by ID",
            description = "Deletes the customer identified by the provided ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customer deleted successfully."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Customer not found with the given ID."
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error."
                    )
            },
            operationId = "deleteCliente"
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        try {
            clienteService.deleteCliente(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error deleting cliente.", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // http://localhost:8080/clientes/filter/paging?page=0&size=10&sortBy=nombres&sortDir=asc&nombres
    @GetMapping("/filter/paging")
    public ResponseEntity<?> getAllClientesByPage(@RequestParam("page") int page,
                                                  @RequestParam("size") int size,
                                                  @RequestParam(name = "sortBy", defaultValue = "nombres") String sortBy,
                                                  @RequestParam(name = "sortDir", defaultValue = "sortDir") String sortDir,
                                                  @RequestParam(name = "nombres") String nombres) {
        try {
            Page<ClienteResponseDto> clientes = clienteService.getClientesByPageAndNombres(nombres, page, size, sortBy, sortDir);
            if (clientes.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            log.error("Error get all clientes by page", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

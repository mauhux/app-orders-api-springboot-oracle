package dev.mauhux.apps.orders.business.domain.services;

import dev.mauhux.apps.orders.business.api.dtos.ClienteRequestDto;
import dev.mauhux.apps.orders.business.api.dtos.ClienteResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    List<ClienteResponseDto> getClientes();

    Optional<ClienteResponseDto> getClienteById(Integer id);

    ClienteResponseDto createCliente(ClienteRequestDto clienteRequestDto);

    ClienteResponseDto updateCliente(ClienteRequestDto clienteRequestDto, Integer id);

    void deleteCliente(Integer id);

    Page<ClienteResponseDto> getClientesByPageAndNombres(String nombres, int page, int size, String sortBy, String sortDir);

}

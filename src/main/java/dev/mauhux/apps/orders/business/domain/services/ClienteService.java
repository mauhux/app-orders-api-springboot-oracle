package dev.mauhux.apps.orders.business.domain.services;

import dev.mauhux.apps.orders.business.api.dtos.ClienteCommandDto;
import dev.mauhux.apps.orders.business.api.dtos.ClienteDto;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    List<ClienteDto> getClientes();

    Optional<ClienteDto> getClienteById(Integer id);

    ClienteDto createCliente(ClienteCommandDto clienteCommandDto);

    ClienteDto updateCliente(ClienteCommandDto clienteCommandDto, Integer id);

    void deleteCliente(Integer id);

}

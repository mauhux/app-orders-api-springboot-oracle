package dev.mauhux.apps.orders.business.domain.services.impl;

import dev.mauhux.apps.orders.business.api.dtos.ClienteCommandDto;
import dev.mauhux.apps.orders.business.api.dtos.ClienteDto;
import dev.mauhux.apps.orders.business.data.repositories.ClienteRepository;
import dev.mauhux.apps.orders.business.domain.mappers.ClienteMapper;
import dev.mauhux.apps.orders.business.domain.services.ClienteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public List<ClienteDto> getClientes() {
        return clienteRepository
                .findAll()
                .stream()
                .map(clienteMapper::toDto)
                .toList();
    }

    @Override
    public Optional<ClienteDto> getClienteById(Integer id) {
        return clienteRepository
                .findById(id)
                .map(clienteMapper::toDto);
    }

    @Override
    public ClienteDto createCliente(ClienteCommandDto clienteCommandDto) {
        return clienteMapper
                .toDto(
                        clienteRepository.save(
                                clienteMapper.toEntity(clienteCommandDto)
                        )
                );
    }

    @Override
    public ClienteDto updateCliente(ClienteCommandDto clienteCommandDto, Integer id) {
        return clienteRepository
                .findById(id)
                .map(clienteEntity -> {
                    clienteMapper.updateEntityFromDto(clienteCommandDto, clienteEntity);
                    return clienteMapper.toDto(clienteRepository.save(clienteEntity));
                })
                .orElseThrow(() -> {
                    log.error("Cliente with id {} not found.", id);
                    return new RuntimeException("Cliente with id " + id + " not found.");
                });
    }

    @Override
    public void deleteCliente(Integer id) {
        clienteRepository
                .findById(id)
                .ifPresent(clienteRepository::delete);
    }
}

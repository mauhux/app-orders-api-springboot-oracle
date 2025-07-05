package dev.mauhux.apps.orders.business.domain.services.impl;

import dev.mauhux.apps.orders.business.api.dtos.ClienteRequestDto;
import dev.mauhux.apps.orders.business.api.dtos.ClienteResponseDto;
import dev.mauhux.apps.orders.business.data.repositories.ClienteRepository;
import dev.mauhux.apps.orders.business.domain.mappers.ClienteMapper;
import dev.mauhux.apps.orders.business.domain.services.ClienteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<ClienteResponseDto> getClientes() {
        return clienteRepository
                .findAll()
                .stream()
                .map(clienteMapper::toDto)
                .toList();
    }

    @Override
    public Optional<ClienteResponseDto> getClienteById(Integer id) {
        return clienteRepository
                .findById(id)
                .map(clienteMapper::toDto);
    }

    @Override
    public ClienteResponseDto createCliente(ClienteRequestDto clienteRequestDto) {
        return clienteMapper
                .toDto(
                        clienteRepository.save(
                                clienteMapper.toEntity(clienteRequestDto)
                        )
                );
    }

    @Override
    public ClienteResponseDto updateCliente(ClienteRequestDto clienteRequestDto, Integer id) {
        return clienteRepository
                .findById(id)
                .map(clienteEntity -> {
                    clienteMapper.updateEntityFromDto(clienteRequestDto, clienteEntity);
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

    @Override
    public Page<ClienteResponseDto> getClientesByPageAndNombres(String nombres, int page, int size, String sortBy, String sortDir) {
        Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return clienteRepository
                .findByNombresContainingIgnoreCase(nombres, pageable)
                .map(clienteMapper::toDto);
    }
}

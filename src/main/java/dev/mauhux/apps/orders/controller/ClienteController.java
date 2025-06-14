package dev.mauhux.apps.orders.controller;

import dev.mauhux.apps.orders.entity.Cliente;
import dev.mauhux.apps.orders.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> getClientes(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Integer id){
        return clienteRepository
                .findById(id)
                .orElse(null);
    }

    @PostMapping
    public Cliente create(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente update(@RequestBody Cliente cliente,
                          @PathVariable Integer id){
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        clienteRepository.deleteById(id);
    }
}

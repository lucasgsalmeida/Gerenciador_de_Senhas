package me.lucas.gerenciadorDeSenhas.controller;

import me.lucas.gerenciadorDeSenhas.model.domain.cliente.ClienteRequestDTO;
import me.lucas.gerenciadorDeSenhas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/id/{id}")
    public ResponseEntity getClienteById(@PathVariable Long id) {
        return clienteService.getClienteById(id);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAllClientes() {
        return clienteService.getAllClientes();
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity saveCliente(@Validated @RequestBody ClienteRequestDTO data) {
        return clienteService.register(data);
    }
}

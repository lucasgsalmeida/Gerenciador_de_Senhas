package me.lucas.gerenciadorDeSenhas.model.repository;

import me.lucas.gerenciadorDeSenhas.model.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

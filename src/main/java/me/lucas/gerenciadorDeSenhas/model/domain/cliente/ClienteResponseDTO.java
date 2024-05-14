package me.lucas.gerenciadorDeSenhas.model.domain.cliente;

public record ClienteResponseDTO(Long id, String nome, String cnpj){

    public ClienteResponseDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getCnpj());
    }
}

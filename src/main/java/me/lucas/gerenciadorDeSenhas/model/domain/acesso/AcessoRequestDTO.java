package me.lucas.gerenciadorDeSenhas.model.domain.acesso;

public record AcessoRequestDTO(Long idCliente, Long idUsuario, String nome, String usuario, String senha) {
}

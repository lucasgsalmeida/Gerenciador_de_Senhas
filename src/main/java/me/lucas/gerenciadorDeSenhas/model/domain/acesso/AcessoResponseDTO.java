package me.lucas.gerenciadorDeSenhas.model.domain.acesso;

public record AcessoResponseDTO(Long id, Long idCliente, Long idUsuario, String nome, String usuario, String senha) {

    public AcessoResponseDTO(Acesso acesso) {
        this(acesso.getId(), acesso.getIdCliente(), acesso.getIdUsuario(), acesso.getNome(), acesso.getUsuario(), acesso.getSenha());
    }
}

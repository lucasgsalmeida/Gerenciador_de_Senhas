package me.lucas.gerenciadorDeSenhas.model.domain.user;

public record UsuarioResponseDTO(Long id, Long idCliente, Long idDepartamento, Long idCargo, String nome, String email, String senha, UserRole role) {

    public UsuarioResponseDTO(Usuario user) {
        this(user.getId(), user.getIdCliente(), user.getIdDepartamento(), user.getIdCargo(), user.getNome(), user.getEmail(), user.getSenha(), user.getRole());
    }
}

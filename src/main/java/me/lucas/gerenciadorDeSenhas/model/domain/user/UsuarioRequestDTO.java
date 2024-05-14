package me.lucas.gerenciadorDeSenhas.model.domain.user;

public record UsuarioRequestDTO(Long idCliente, Long idDepartamento, Long idCargo, String nome, String email, String senha, UserRole role){
}

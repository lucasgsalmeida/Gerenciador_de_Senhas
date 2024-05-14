package me.lucas.gerenciadorDeSenhas.model.repository;

import me.lucas.gerenciadorDeSenhas.model.domain.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);

}

package me.lucas.gerenciadorDeSenhas.model.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.lucas.gerenciadorDeSenhas.model.domain.departamento.Departamento;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(table = "cliente", name = "idCliente", referencedColumnName = "id")
    private Long idCliente;

    @JoinColumn(table = "departamento", name = "idDepartamento", referencedColumnName = "id")
    private Long idDepartamento;

    @JoinColumn(table = "cargo", name = "idCargo", referencedColumnName = "id")
    private Long idCargo;

    private String nome;

    private String email;

    private String senha;

    private UserRole role;

    public Usuario(Long idCliente, Long idDepartamento, Long idCargo, String nome, String email, String senha, UserRole role) {
        this.idCliente = idCliente;
        this.idDepartamento = idDepartamento;
        this.idCargo = idCargo;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else if (this.role == UserRole.MASTER) {
            return List.of(new SimpleGrantedAuthority("ROLE_MASTER"));
        }
        else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

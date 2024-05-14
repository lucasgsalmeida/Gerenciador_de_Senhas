package me.lucas.gerenciadorDeSenhas.model.domain.acesso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "acesso")
@Getter
@Setter
public class Acesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(table = "cliente", name = "idCliente", referencedColumnName = "id")
    private Long idCliente;

    @JoinColumn(table = "usuario", name = "idUsuario", referencedColumnName = "id")
    private Long idUsuario;

    private String nome;

    private String usuario;

    private String senha;

    public Acesso(AcessoRequestDTO dto) {
        this.idCliente = dto.idCliente();
        this.idUsuario = dto.idUsuario();
        this.nome = dto.nome();
        this.usuario = dto.usuario();
        this.senha = dto.senha();
    }
}

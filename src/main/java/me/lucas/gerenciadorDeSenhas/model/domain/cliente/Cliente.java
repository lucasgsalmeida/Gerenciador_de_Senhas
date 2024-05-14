package me.lucas.gerenciadorDeSenhas.model.domain.cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cnpj;

    public Cliente(ClienteRequestDTO dto) {
        this.nome = dto.nome();
        this.cnpj = dto.cnpj();
    }


}

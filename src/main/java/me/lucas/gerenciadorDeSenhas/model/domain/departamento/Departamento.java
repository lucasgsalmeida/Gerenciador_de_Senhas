package me.lucas.gerenciadorDeSenhas.model.domain.departamento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departamento")
@Getter
@Setter
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(table = "cliente", name = "idCliente", referencedColumnName = "id")
    private Long idCliente;

    private String nome;

    public Departamento(DepartamentoRequestDTO dto) {
        this.idCliente = dto.idCliente();
        this.nome = dto.nome();
    }

}

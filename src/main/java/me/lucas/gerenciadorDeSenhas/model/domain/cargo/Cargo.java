package me.lucas.gerenciadorDeSenhas.model.domain.cargo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cargo")
@Getter
@Setter
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(table = "departamento", name = "idDepartamento", referencedColumnName = "id")
    private Long idDepartamento;

    @JoinColumn(table = "cliente", name = "idCliente", referencedColumnName = "id")
    private Long idCliente;

    private String nome;

    public Cargo(CargoRequestDTO dto) {
        this.idDepartamento = dto.idDepartamento();
        this.idCliente = dto.idCliente();
        this.nome = dto.nome();
    }
}

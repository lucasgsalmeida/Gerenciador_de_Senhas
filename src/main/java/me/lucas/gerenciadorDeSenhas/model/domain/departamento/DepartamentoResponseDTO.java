package me.lucas.gerenciadorDeSenhas.model.domain.departamento;

public record DepartamentoResponseDTO(Long id, Long idCliente, String nome) {

    public DepartamentoResponseDTO(Departamento departamento) {
        this(departamento.getId(), departamento.getIdCliente(), departamento.getNome());
    }
}

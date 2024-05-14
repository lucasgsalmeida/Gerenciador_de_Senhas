package me.lucas.gerenciadorDeSenhas.model.domain.cargo;

public record CargoResponseDTO(Long id, Long idDepartamento, Long idCliente, String nome) {

    public CargoResponseDTO(Cargo cargo) {
        this(cargo.getId(), cargo.getIdDepartamento(), cargo.getIdCliente(), cargo.getNome());
    }

}

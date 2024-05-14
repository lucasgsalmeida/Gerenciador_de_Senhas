package me.lucas.gerenciadorDeSenhas.model.repository;

import me.lucas.gerenciadorDeSenhas.model.domain.departamento.Departamento;
import me.lucas.gerenciadorDeSenhas.model.domain.departamento.DepartamentoRequestDTO;
import me.lucas.gerenciadorDeSenhas.model.domain.departamento.DepartamentoResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    @Query("SELECT departamento FROM Departamento departamento WHERE departamento.idCliente = :idCliente")
    List<DepartamentoResponseDTO> findAllDepartamentoByIdCliente(@Param("idCliente") Long idCliente);

    @Query("SELECT departamento from Departamento departamento where departamento.id = :id and departamento.idCliente = :idCliente")
    DepartamentoResponseDTO findDepartamentoByIdAndIdCliente(@Param("id") Long id, @Param("idCliente") Long idCliente);

    @Query("SELECT departamento from Departamento departamento where departamento.id = :id and departamento.idCliente = :idCliente")
    Departamento findDepartamentoDelete(@Param("id") Long id, @Param("idCliente") Long idCliente);

    @Query("SELECT departamento from Departamento departamento where departamento.idCliente = :idCliente and departamento.nome = 'DEPARTAMENTO ADMINISTRADOR'")
    Departamento findDepartamentoAdministradorByIdCliente(@Param("idCliente") Long idCliente);
}

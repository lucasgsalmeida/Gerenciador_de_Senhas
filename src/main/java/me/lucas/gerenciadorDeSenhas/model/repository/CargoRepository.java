package me.lucas.gerenciadorDeSenhas.model.repository;

import me.lucas.gerenciadorDeSenhas.model.domain.cargo.Cargo;
import me.lucas.gerenciadorDeSenhas.model.domain.cargo.CargoResponseDTO;
import me.lucas.gerenciadorDeSenhas.model.domain.cargo.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

    @Query("SELECT cargo FROM Cargo cargo WHERE cargo.idCliente = :idCliente")
    List<CargoResponseDTO> findAllCargoByIdCliente(@Param("idCliente") Long idCliente);

    @Query("SELECT cargo from Cargo cargo where cargo.id = :id and cargo.idCliente = :idCliente")
    CargoResponseDTO findCargoByIdAndIdCliente(@Param("id") Long id, @Param("idCliente") Long idCliente);

    @Query("SELECT cargo from Cargo cargo where cargo.id = :id and cargo.idCliente = :idCliente")
    Cargo findCargoDelete(@Param("id") Long id, @Param("idCliente") Long idCliente);

    @Query("SELECT cargo from Cargo cargo where cargo.idCliente = :idCliente and cargo.nome = 'ADMINISTRADOR'")
    Cargo findCargoAdministradorByIdCliente(@Param("idCliente") Long idCliente);



}

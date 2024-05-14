package me.lucas.gerenciadorDeSenhas.model.repository;

import me.lucas.gerenciadorDeSenhas.model.domain.acesso.Acesso;
import me.lucas.gerenciadorDeSenhas.model.domain.acesso.AcessoResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AcessoRepository extends JpaRepository<Acesso, Long> {

    @Query("SELECT acesso FROM Acesso acesso WHERE acesso.idCliente = :idCliente")
    List<AcessoResponseDTO> findAllAcessoByIdCliente(@Param("idCliente") Long idCliente);

    @Query("SELECT acesso FROM Acesso acesso WHERE acesso.idCliente = :idCliente and acesso.idUsuario = :idUsuario")
    List<AcessoResponseDTO> findAllByUsuario(@Param("idCliente") Long idCliente, @Param("idUsuario") Long idUsuario);

    @Query("SELECT acesso from Acesso acesso where acesso.id = :id and acesso.idCliente = :idCliente")
    AcessoResponseDTO findAcessoByIdAndIdCliente(@Param("id") Long id, @Param("idCliente") Long idCliente);

    @Query("SELECT acesso from Acesso acesso where acesso.id = :id and acesso.idCliente = :idCliente")
    Acesso findAcessoDelete(@Param("id") Long id, @Param("idCliente") Long idCliente);

}

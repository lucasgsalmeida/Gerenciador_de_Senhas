package me.lucas.gerenciadorDeSenhas.service;

import me.lucas.gerenciadorDeSenhas.model.domain.acesso.Acesso;
import me.lucas.gerenciadorDeSenhas.model.domain.acesso.AcessoRequestDTO;
import me.lucas.gerenciadorDeSenhas.model.domain.acesso.AcessoResponseDTO;
import me.lucas.gerenciadorDeSenhas.model.domain.user.Usuario;
import me.lucas.gerenciadorDeSenhas.model.repository.AcessoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcessoService {

    @Autowired
    private AcessoRepository repository;

    @Autowired
    private VerifyUserService verifyUserService;

    public ResponseEntity getAll(UserDetails userDetails) {

        Usuario user = verifyUserService.getUser(userDetails);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<AcessoResponseDTO> listRetorno = repository.findAllAcessoByIdCliente(user.getIdCliente());
        return ResponseEntity.ok(listRetorno);

    }

    public ResponseEntity getAllByUsuario(Long idUsuario, UserDetails userDetails) {

        Usuario user = verifyUserService.getUser(userDetails);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<AcessoResponseDTO> response = repository.findAllByUsuario(user.getIdCliente(), idUsuario);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(response);

    }

    public ResponseEntity register(AcessoRequestDTO dto, UserDetails userDetails) {

        Usuario user = verifyUserService.getUser(userDetails);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!user.getIdCliente().equals(dto.idCliente())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Acesso acesso = new Acesso(dto);
        repository.save(acesso);
        return ResponseEntity.ok().build();

    }

    public ResponseEntity delete(Long id, UserDetails userDetails) {

        Usuario user = verifyUserService.getUser(userDetails);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Acesso acesso = repository.findAcessoDelete(id, user.getIdCliente());

        if (acesso == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        repository.delete(acesso);
        return ResponseEntity.ok().build();

    }

    public ResponseEntity update(Long id, AcessoRequestDTO dto, UserDetails userDetails) {

        Usuario user = verifyUserService.getUser(userDetails);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Acesso acesso = repository.findAcessoDelete(id, user.getIdCliente());

        if (acesso == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        BeanUtils.copyProperties(dto, acesso);
        repository.save(acesso);
        return ResponseEntity.ok().build();
    }



}

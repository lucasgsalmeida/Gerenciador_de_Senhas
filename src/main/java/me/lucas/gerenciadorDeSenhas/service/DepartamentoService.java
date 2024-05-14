package me.lucas.gerenciadorDeSenhas.service;

import me.lucas.gerenciadorDeSenhas.model.domain.departamento.Departamento;
import me.lucas.gerenciadorDeSenhas.model.domain.departamento.DepartamentoRequestDTO;
import me.lucas.gerenciadorDeSenhas.model.domain.departamento.DepartamentoResponseDTO;
import me.lucas.gerenciadorDeSenhas.model.domain.user.Usuario;
import me.lucas.gerenciadorDeSenhas.model.repository.DepartamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository repository;

    @Autowired
    private VerifyUserService verifyUserService;

    public ResponseEntity getAll(UserDetails userDetails) {

        Usuario user = verifyUserService.getUser(userDetails);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<DepartamentoResponseDTO> listRetorno = repository.findAllDepartamentoByIdCliente(user.getIdCliente());
        return ResponseEntity.ok(listRetorno);

    }

    public ResponseEntity getById(Long id, UserDetails userDetails) {

        Usuario user = verifyUserService.getUser(userDetails);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        DepartamentoResponseDTO response = repository.findDepartamentoByIdAndIdCliente(id, user.getIdCliente());

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(response);

    }

    public ResponseEntity register(DepartamentoRequestDTO dto, UserDetails userDetails) {

        Usuario user = verifyUserService.getUser(userDetails);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!user.getIdCliente().equals(dto.idCliente())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(dto.nome().equalsIgnoreCase("DEPARTAMENTO ADMINISTRADOR")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Departamento departamento = new Departamento(dto);
        repository.save(departamento);
        return ResponseEntity.ok().build();

    }

    public ResponseEntity delete(Long id, UserDetails userDetails) {

        Usuario user = verifyUserService.getUser(userDetails);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Departamento departamento = repository.findDepartamentoDelete(id, user.getIdCliente());

        if (departamento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        repository.delete(departamento);
        return ResponseEntity.ok().build();

    }

    public ResponseEntity update(Long id, DepartamentoRequestDTO dto, UserDetails userDetails) {

        Usuario user = verifyUserService.getUser(userDetails);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Departamento departamento = repository.findDepartamentoDelete(id, user.getIdCliente());

        if (departamento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        BeanUtils.copyProperties(dto, departamento);
        repository.save(departamento);
        return ResponseEntity.ok().build();
    }

}

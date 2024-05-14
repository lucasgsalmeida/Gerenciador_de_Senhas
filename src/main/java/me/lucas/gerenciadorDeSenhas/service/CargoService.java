package me.lucas.gerenciadorDeSenhas.service;

import me.lucas.gerenciadorDeSenhas.model.domain.cargo.Cargo;
import me.lucas.gerenciadorDeSenhas.model.domain.cargo.CargoRequestDTO;
import me.lucas.gerenciadorDeSenhas.model.domain.cargo.CargoResponseDTO;
import me.lucas.gerenciadorDeSenhas.model.domain.user.Usuario;
import me.lucas.gerenciadorDeSenhas.model.repository.CargoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {

    @Autowired
    private CargoRepository repository;

    @Autowired
    private VerifyUserService verifyUserService;

    public ResponseEntity getAll(UserDetails userDetails) {

        Usuario user = verifyUserService.getUser(userDetails);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<CargoResponseDTO> listRetorno = repository.findAllCargoByIdCliente(user.getIdCliente());
        return ResponseEntity.ok(listRetorno);

    }

    public ResponseEntity getById(Long id, UserDetails userDetails) {

        Usuario user = verifyUserService.getUser(userDetails);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CargoResponseDTO response = repository.findCargoByIdAndIdCliente(id, user.getIdCliente());

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(response);

    }

    public ResponseEntity register(CargoRequestDTO dto, UserDetails userDetails) {

        Usuario user = verifyUserService.getUser(userDetails);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!user.getIdCliente().equals(dto.idCliente())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(dto.nome().equalsIgnoreCase("ADMINISTRADOR")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Cargo cargo = new Cargo(dto);
        repository.save(cargo);
        return ResponseEntity.ok().build();

    }

    public ResponseEntity delete(Long id, UserDetails userDetails) {

        Usuario user = verifyUserService.getUser(userDetails);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Cargo cargo = repository.findCargoDelete(id, user.getIdCliente());

        if (cargo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        repository.delete(cargo);
        return ResponseEntity.ok().build();

    }

    public ResponseEntity update(Long id, CargoRequestDTO dto, UserDetails userDetails) {

        Usuario user = verifyUserService.getUser(userDetails);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Cargo cargo = repository.findCargoDelete(id, user.getIdCliente());

        if (cargo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        BeanUtils.copyProperties(dto, cargo);
        repository.save(cargo);
        return ResponseEntity.ok().build();
    }

}

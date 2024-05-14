package me.lucas.gerenciadorDeSenhas.service;

import me.lucas.gerenciadorDeSenhas.model.domain.cargo.Cargo;
import me.lucas.gerenciadorDeSenhas.model.domain.cargo.CargoRequestDTO;
import me.lucas.gerenciadorDeSenhas.model.domain.cliente.Cliente;
import me.lucas.gerenciadorDeSenhas.model.domain.cliente.ClienteRequestDTO;
import me.lucas.gerenciadorDeSenhas.model.domain.cliente.ClienteResponseDTO;
import me.lucas.gerenciadorDeSenhas.model.domain.departamento.Departamento;
import me.lucas.gerenciadorDeSenhas.model.domain.departamento.DepartamentoRequestDTO;
import me.lucas.gerenciadorDeSenhas.model.repository.CargoRepository;
import me.lucas.gerenciadorDeSenhas.model.repository.ClienteRepository;
import me.lucas.gerenciadorDeSenhas.model.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private CargoRepository cargoRepository;

    public ResponseEntity getClienteById(@PathVariable Long id) {
        Cliente clienteProcurado = repository.findById(id).orElseThrow();

        if (clienteProcurado != null) {
            ClienteResponseDTO responseDTO = new ClienteResponseDTO(clienteProcurado);
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity getAllClientes() {
        List<ClienteResponseDTO> lista = repository.findAll().stream().map(ClienteResponseDTO::new).toList();
        return ResponseEntity.ok(lista);
    }


    /*

        Ao registrar um novo Cliente, será necessário persistir um departamento master e um cargo master

     */

    public ResponseEntity register(@Validated ClienteRequestDTO data) {
        if (data == null) {
            return ResponseEntity.badRequest().build();
        }

        Cliente cs = new Cliente(data);

        repository.save(cs);

        DepartamentoRequestDTO masterDepartamento = new DepartamentoRequestDTO(cs.getId(), "DEPARTAMENTO ADMINISTRADOR");
        Departamento persistDepartamento = new Departamento(masterDepartamento);
        departamentoRepository.save(persistDepartamento);

        CargoRequestDTO masterCargo = new CargoRequestDTO(persistDepartamento.getId(), cs.getId(), "ADMINISTRADOR");
        Cargo persistCargo = new Cargo(masterCargo);
        cargoRepository.save(persistCargo);

        return ResponseEntity.ok().build();
    }
}

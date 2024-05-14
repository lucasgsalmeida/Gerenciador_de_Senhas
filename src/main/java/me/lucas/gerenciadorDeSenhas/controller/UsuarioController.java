package me.lucas.gerenciadorDeSenhas.controller;

import me.lucas.gerenciadorDeSenhas.model.domain.user.UsuarioRequestDTO;
import me.lucas.gerenciadorDeSenhas.model.domain.user.UsuarioResponseDTO;
import me.lucas.gerenciadorDeSenhas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/user")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioById(@RequestParam(name = "id") Long id) {
        return service.getUsuarioById(id);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated UsuarioResponseDTO data) {
        return service.login(data);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated UsuarioRequestDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return service.register(data, userDetails);
    }

    @PostMapping("/register/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity registerAdmin(@RequestBody @Validated UsuarioRequestDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return service.registerAdministrator(data, userDetails);
    }

    @PostMapping("/register/master")
    public ResponseEntity registerMaster(@RequestBody UsuarioRequestDTO data) {
        return service.registerMaster(data);
    }

    @GetMapping("/get/id")
    public ResponseEntity<?> getUsuarioAndCliente(@AuthenticationPrincipal UserDetails userDetails) {
        return service.getUsuarioAndCliente(userDetails);
    }

    @PostMapping("/verify-token")
    public ResponseEntity<String> verifyToken(@RequestHeader("Authorization") String authorizationHeader) {
        return service.verifyToken(authorizationHeader);
    }

}

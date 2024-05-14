package me.lucas.gerenciadorDeSenhas.controller;

import me.lucas.gerenciadorDeSenhas.model.domain.cargo.CargoRequestDTO;
import me.lucas.gerenciadorDeSenhas.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cargo")
public class CargoController {

    @Autowired
    private CargoService service;

    @GetMapping("/get/all")
    public ResponseEntity getAll(@AuthenticationPrincipal UserDetails userDetails) {
        return service.getAll(userDetails);
    }

    @GetMapping("/get")
    public ResponseEntity getById(@RequestBody Long id, @AuthenticationPrincipal UserDetails userDetails) {
        return service.getById(id, userDetails);
    }

    @PostMapping("/add")
    public ResponseEntity register(@RequestBody CargoRequestDTO dto, @AuthenticationPrincipal UserDetails userDetails) {
        return service.register(dto, userDetails);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Long id, @AuthenticationPrincipal UserDetails userDetails) {
        return service.delete(id, userDetails);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Long id, @RequestBody CargoRequestDTO dto, @AuthenticationPrincipal UserDetails userDetails) {
        return service.update(id, dto, userDetails);
    }

}
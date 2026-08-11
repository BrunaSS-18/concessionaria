package com.concessionaria.controller;

import com.concessionaria.dto.CarroDTO;
import com.concessionaria.dto.CarroResponseDTO;
import com.concessionaria.service.CarroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concessionaria/carro")
@RequiredArgsConstructor
public class CarroController {

    private final CarroService carroService;

    @PostMapping
    public ResponseEntity<CarroResponseDTO> cadastrar(@Valid @RequestBody CarroDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carroService.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<CarroResponseDTO>> listar() {

        return ResponseEntity.ok(carroService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(carroService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        carroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

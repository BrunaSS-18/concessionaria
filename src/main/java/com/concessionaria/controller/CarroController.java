package com.concessionaria.controller;

import com.concessionaria.dto.CarroDTO;
import com.concessionaria.dto.CarroResponseDTO;
import com.concessionaria.service.CarroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Veiculo", description = "Faz o cadastro, lista, busca por ID e deleta o veículo")
@RestController
@RequestMapping("/concessionaria/carro")
@RequiredArgsConstructor
public class CarroController {

    private final CarroService carroService;

    @PostMapping
    @Operation (summary = "Cadastra novos veículos")
    @ApiResponse(responseCode = "200", description = "Retorna o veículo salvo")
    @ApiResponse(responseCode = "409", description = "Id já cadastrado")
    public ResponseEntity<CarroResponseDTO> cadastrar(@Valid @RequestBody CarroDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carroService.cadastrar(dto));
    }

    @GetMapping
    @Operation (summary = "Lista os veículos")
    public ResponseEntity<List<CarroResponseDTO>> listar() {

        return ResponseEntity.ok(carroService.listar());
    }

    @GetMapping("/{id}")
    @Operation (summary = "Busca por ID")
    @ApiResponse(responseCode = "200", description = "Veículo encontrado")
    @ApiResponse(responseCode = "409", description = "Veículo não encontrado")
    public ResponseEntity<CarroResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(carroService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    @Operation (summary = "Deleta o veículo")
    @ApiResponse(responseCode = "200", description = "Veiculo deletado")
    @ApiResponse(responseCode = "409", description = "Veículo não encontrado")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        carroService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/listarComFiltro")
    public ResponseEntity<List<CarroResponseDTO>> listarComFiltro(
            @RequestParam(required = false) String cor,
            @RequestParam(required = false) Integer anoFabricacao) {
        return ResponseEntity.ok(carroService.buscarComFiltro(cor, anoFabricacao));
    }
}

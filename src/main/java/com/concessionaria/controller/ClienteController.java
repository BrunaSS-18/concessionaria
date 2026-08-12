package com.concessionaria.controller;

import com.concessionaria.dto.ClienteDTO;
import com.concessionaria.dto.ClienteResponseDTO;
import com.concessionaria.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cliente", description = "Faz o cadastro, lista, busca por ID e deleta o cliente")
@RestController
@RequestMapping("/concessionaria/api/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    @Operation (summary = "Cadastra o cliente")
    @ApiResponse(responseCode = "200", description = "Retorna o cliente salvo")
    @ApiResponse(responseCode = "409", description = "Id já cadastrado")
    public ResponseEntity<ClienteResponseDTO> cadastrar(@Valid @RequestBody ClienteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.cadastrar(dto));
    }

    @GetMapping
    @Operation (summary = "Lista os clientes")
    public ResponseEntity<List<ClienteResponseDTO>> listar() {
        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/{id}")
    @Operation (summary = "Busca o cliente por ID")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "409", description = "Usuário não encontrado")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    @Operation (summary = "Deleta o Cliente")
    @ApiResponse(responseCode = "200", description = "Usuário deletado")
    @ApiResponse(responseCode = "409", description = "Usuário não encontrado")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
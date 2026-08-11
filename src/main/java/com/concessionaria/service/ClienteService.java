package com.concessionaria.service;

import com.concessionaria.dto.ClienteDTO;
import com.concessionaria.dto.ClienteResponseDTO;
import com.concessionaria.exception.RecursoNaoEncontradoException;
import com.concessionaria.exception.RegistroDuplicadoException;
import com.concessionaria.model.Cliente;
import com.concessionaria.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteResponseDTO cadastrar(ClienteDTO dto) {
        if (clienteRepository.existsByCpf(dto.cpf())) {
            throw new RegistroDuplicadoException("cpf", "CPF já cadastrado");
        }

        Cliente cliente = Cliente.builder()
                .nome(dto.nome())
                .cpf(dto.cpf())
                .telefone(dto.telefone())
                .email(dto.email())
                .build();

        cliente = clienteRepository.save(cliente);
        return toResponseDTO(cliente);
    }

    public List<ClienteResponseDTO> listar() {
        return clienteRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ClienteResponseDTO buscarPorId(Long id) {

        return toResponseDTO(buscarEntidadePorId(id));
    }

    public void deletar(Long id) {
        Cliente cliente = buscarEntidadePorId(id);
        clienteRepository.delete(cliente);
    }

    private Cliente buscarEntidadePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado."));
    }

    private ClienteResponseDTO toResponseDTO(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                mascararCpf(cliente.getCpf()),
                cliente.getTelefone(),
                cliente.getEmail()
        );
    }

    private String mascararCpf(String cpf) {
        if (cpf == null || cpf.length() < 4) {
            return "****";
        }
        String finalCpf = cpf.substring(cpf.length() - 4);
        return "*".repeat(cpf.length() - 4) + finalCpf;
    }
}

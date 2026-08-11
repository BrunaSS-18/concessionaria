package com.concessionaria.service;

import com.concessionaria.dto.CarroDTO;
import com.concessionaria.dto.CarroResponseDTO;
import com.concessionaria.exception.RecursoNaoEncontradoException;
import com.concessionaria.exception.RegistroDuplicadoException;
import com.concessionaria.model.Carro;
import com.concessionaria.repository.CarroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarroService {

    private final CarroRepository carroRepository;

    public CarroResponseDTO cadastrar(CarroDTO dto) {
        if (carroRepository.existsByChassi(dto.chassi())) {
            throw new RegistroDuplicadoException("chassi", "chassi já cadastrado");
        }
        if (dto.placa() != null && carroRepository.existsByPlaca(dto.placa())) {
            throw new RegistroDuplicadoException("placa", "placa já cadastrada");
        }

        Carro carro = Carro.builder()
                .marca(dto.marca())
                .modelo(dto.modelo())
                .anoFabricacao(dto.anoFabricacao())
                .anoModelo(dto.anoModelo())
                .cor(dto.cor())
                .chassi(dto.chassi())
                .placa(dto.placa())
                .quilometragem(dto.quilometragem())
                .preco(dto.preco())
                .tipoEstado(dto.tipoEstado())
                .statusVenda(dto.statusVenda())
                .build();

        carro = carroRepository.save(carro);
        return toResponseDTO(carro);
    }

    public List<CarroResponseDTO> listar() {
        return carroRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public CarroResponseDTO buscarPorId(Long id) {
        return toResponseDTO(buscarEntidadePorId(id));
    }

    public void deletar(Long id) {
        Carro carro = buscarEntidadePorId(id);
        carroRepository.delete(carro);
    }

    private Carro buscarEntidadePorId(Long id) {
        return carroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Carro não encontrado."));
    }

    private CarroResponseDTO toResponseDTO(Carro carro) {
        return new CarroResponseDTO(
                carro.getId(),
                carro.getMarca(),
                carro.getModelo(),
                carro.getAnoFabricacao(),
                carro.getAnoModelo(),
                carro.getCor(),
                carro.getChassi(),
                carro.getPlaca(),
                carro.getQuilometragem(),
                carro.getPreco(),
                carro.getTipoEstado(),
                carro.getStatusVenda()
        );
    }
}
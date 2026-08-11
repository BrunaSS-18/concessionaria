package com.concessionaria.repository;

import com.concessionaria.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    Optional<Carro> findByChassi(String chassi);

    Optional<Carro> findByPlaca(String placa);

    List<Carro> findByCliente_Id(Long clienteId);
}

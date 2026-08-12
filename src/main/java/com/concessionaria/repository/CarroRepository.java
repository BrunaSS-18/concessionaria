package com.concessionaria.repository;

import com.concessionaria.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

    boolean existsByChassi(String chassi);
    boolean existsByPlaca(String placa);

    List<Carro> findByCorIgnoreCase(String cor);
    List<Carro> findByAnoFabricacao(Integer anoFabricacao);
    List<Carro> findByCorIgnoreCaseAndAnoFabricacao(String cor, Integer anoFabricacao);
}

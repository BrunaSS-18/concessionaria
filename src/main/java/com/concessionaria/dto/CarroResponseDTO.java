package com.concessionaria.dto;

import com.concessionaria.enums.StatusVenda;
import com.concessionaria.enums.TipoEstado;

import java.math.BigDecimal;

public record CarroResponseDTO(
        Long id,
        String marca,
        String modelo,
        Integer anoFabricacao,
        Integer anoModelo,
        String cor,
        String chassi,
        String placa,
        Integer quilometragem,
        BigDecimal preco,
        TipoEstado tipoEstado,
        StatusVenda statusVenda
) {
}
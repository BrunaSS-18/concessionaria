package com.concessionaria.dto;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String cpfMascarado,
        String telefone,
        String email
) {
}

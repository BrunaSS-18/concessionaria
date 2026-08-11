package com.concessionaria.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteDTO(
        @NotBlank(message = "nome é obrigatório")
        @Size(min = 3, max = 150, message = "nome deve ter entre 3 e 150 caracteres")
        String nome,

        @NotBlank(message = "CPF é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 números, sem letras, pontos ou traços")
        String cpf,

        @NotBlank(message = "telefone é obrigatório")
        @Pattern(regexp = "\\d{10,11}", message = "telefone deve ter DDD + número, apenas dígitos (10 ou 11 números)")
        String telefone,

        @NotBlank(message = "e-mail é obrigatório")
        @Email(message = "e-mail inválido")
        String email
) {
}

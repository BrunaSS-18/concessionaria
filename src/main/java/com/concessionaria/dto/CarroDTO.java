package com.concessionaria.dto;

import com.concessionaria.enums.StatusVenda;
import com.concessionaria.enums.TipoEstado;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CarroDTO(
        @NotBlank(message = "marca é obrigatória")
        String marca,

        @NotBlank(message = "modelo é obrigatório")
        String modelo,

        @NotNull(message = "ano de fabricação é obrigatório")
        @Min(value = 1950, message = "ano de fabricação inválido")
        @Max(value = 2026, message = "deve ser menor ou igual a 2026")
        Integer anoFabricacao,

        @NotNull(message = "ano do modelo é obrigatório")
        @Min(value = 1950, message = "ano do modelo inválido")
        @Max(value = 2027, message = "ano do modelo não pode passar de 2027")
        Integer anoModelo,

        @NotBlank(message = "cor é obrigatória")
        String cor,

        @NotBlank(message = "chassi é obrigatório")
        @Size(min = 17, max = 17, message = "chassi deve ter exatamente 17 caracteres")
        String chassi,

        @Pattern(
                regexp = "^([A-Z]{3}[0-9]{4}|[A-Z]{3}[0-9][A-Z][0-9]{2})$",
                message = "placa fora do padrão (ex: ABC1234 ou ABC1D23)"
        )
        String placa,

        @NotNull(message = "quilometragem é obrigatória")
        @PositiveOrZero(message = "quilometragem não pode ser negativa")
        Integer quilometragem,

        @NotNull(message = "preço é obrigatório")
        @DecimalMin(value = "0.01", message = "deve ser maior que 0")
        BigDecimal preco,

        @NotNull(message = "Disponibilidade é obrigatória")
        StatusVenda statusVenda,

        @NotNull(message = "tipo do carro (novo/seminovo) é obrigatório")
        TipoEstado tipoEstado

){}

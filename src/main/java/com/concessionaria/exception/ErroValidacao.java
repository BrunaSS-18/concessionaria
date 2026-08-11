package com.concessionaria.exception;

import java.util.List;

public record ErroValidacao(int status, List<ErroCampoDTO> erros) {
}

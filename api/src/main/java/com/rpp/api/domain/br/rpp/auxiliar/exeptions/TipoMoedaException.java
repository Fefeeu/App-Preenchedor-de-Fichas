package com.rpp.api.domain.br.rpp.auxiliar.exeptions;

public class TipoMoedaException extends RuntimeException {
    public TipoMoedaException() {
        super("Tipo de moeda invalida");
    }
}

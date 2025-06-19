package br.rpp.auxiliar.exeptions;

public class TipoItemException extends RuntimeException {
    public TipoItemException() {
        super("Tipo de Item Invalido");
    }
}

package br.rpp.auxiliar.exeptions;

public class ValorNegativoException extends RuntimeException {
    public ValorNegativoException() {
        super("zero ou valor negativo inserido");
    }
}

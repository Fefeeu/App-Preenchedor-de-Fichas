package br.rpp.auxiliar.exeptions;

public class ValorMenorQueUmException extends RuntimeException {
    public ValorMenorQueUmException() {
        super("Valor Menor que 1");
    }
}

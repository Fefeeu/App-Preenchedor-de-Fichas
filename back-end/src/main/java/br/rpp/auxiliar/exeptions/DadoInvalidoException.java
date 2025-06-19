package br.rpp.auxiliar.exeptions;

public class DadoInvalidoException extends RuntimeException {
    public DadoInvalidoException() {
        super("Dado com quantidade de lados ou quantidade de dados invalido");
    }
}

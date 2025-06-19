package br.rpp.auxiliar.exeptions;

public class NivelException extends RuntimeException {
    public NivelException() {
      super("Valor do nível invalido, somente valores entre 1 e 20");
    }
}

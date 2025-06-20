package br.rpp.auxiliar.exeptions;

public class NivelMagiaException extends RuntimeException {
  public NivelMagiaException() {
    super("Nível de magia invalido");
  }
}

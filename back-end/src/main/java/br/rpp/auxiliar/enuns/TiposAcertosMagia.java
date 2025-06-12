package br.rpp.auxiliar.enuns;

public enum TiposAcertosMagia {
    RESISTENCIA("resistencia"),
    TESTE("teste"),
    NENHUM("nenhum");

    private final String nomeTipo;

    private TiposAcertosMagia(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }

    @Override
    public String toString() {
        return nomeTipo;
    }
}

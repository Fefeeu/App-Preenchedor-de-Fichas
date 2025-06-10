package br.rpp.sql;

public enum Tabelas {
    USER("user"),
    FICHA("ficha"),
    MAGIA("magia"),
    ITEM("item"),
    CLASSE("classe"),
    RACA("raca");

    private final String nomeTabela;

    private Tabelas(String nomeTabela) {
        this.nomeTabela = nomeTabela;
    }

    @Override
    public String toString() {
        return nomeTabela;
    }
}

package br.rpp.sql;

// garantir que não haja erro no nome das tabelas
public enum Tabelas {
    USER("user"),
    FICHA("ficha"),
    MAGIA("magia"),
    INVENTARIO("inventario"),
    ITEM("item"),
    CLASSE("classe"),
    RACA("raca"),
    MAGIAUSER("magiaUser");

    private final String nomeTabela;

    private Tabelas(String nomeTabela) {
        this.nomeTabela = nomeTabela;
    }

    @Override
    public String toString() {
        return nomeTabela;
    }
}

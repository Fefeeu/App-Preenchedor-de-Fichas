package com.rpp.api.domain.br.rpp.auxiliar.enuns;

public enum TiposMagia {
    EFEITO("efeito"),
    DANO("dano"),
    CURA("cura");

    private final String nomeTipo;

    private TiposMagia(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }

    @Override
    public String toString() {
        return nomeTipo;
    }
}

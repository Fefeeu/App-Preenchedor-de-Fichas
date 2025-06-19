package com.rpp.api.domain.br.rpp.magias;

import com.rpp.api.domain.br.rpp.auxiliar.enuns.TiposMagia;
import com.rpp.api.domain.br.rpp.dado.Dado;

public class MagiaCura extends Magia {
    private int dadoCura;
    private int quantidadeDado;

    public MagiaCura(int id, String nome, String descricao, int nivel, String tempoConjuracao,
                     String duracao, String alcance, String area, String escola, String tipoAcerto,
                     int dadoCura, int quantidadeDado) {
        super(id, nome, descricao, nivel, tempoConjuracao, duracao, alcance, area, escola, tipoAcerto);
        this.setTipo(TiposMagia.CURA.toString());
        this.dadoCura = dadoCura;
        this.quantidadeDado = quantidadeDado;
    }

    @Override
    public int usarMagia() {
        return Dado.rollD(this.dadoCura, this.quantidadeDado);
    }

    public int getDadoCura() {
        return dadoCura;
    }

    public void setDadoCura(int dadoCura) {
        this.dadoCura = dadoCura;
    }

    public int getQuantidadeDado() {
        return quantidadeDado;
    }

    public void setQuantidadeDado(int quantidadeDado) {
        this.quantidadeDado = quantidadeDado;
    }
}

package br.rpp.magias;

import br.rpp.auxiliar.enuns.TiposMagia;
import br.rpp.dado.Dado;

public class MagiaDano extends Magia {
    private int dadoDano;
    private int quantidadeDado;

    public MagiaDano(int id, String nome, String descricao, int nivel, String tempoConjuracao,
                     String duracao, String alcance, String area, String escola, String tipoAcerto,
                     int dadoDano, int quantidadeDado) {
        super(id, nome, descricao, nivel, tempoConjuracao, duracao, alcance, area, escola, tipoAcerto);
        this.setTipo(TiposMagia.DANO.toString());
        this.dadoDano = dadoDano;
        this.quantidadeDado = quantidadeDado;
    }

    @Override
    public int usarMagia() {
        Dado.rollD(this.dadoDano, this.quantidadeDado);
        return 0;
    }

    public int getDadoDano() {
        return dadoDano;
    }

    public void setDadoDano(int dadoDano) {
        this.dadoDano = dadoDano;
    }

    public int getQuantidadeDado() {
        return quantidadeDado;
    }

    public void setQuantidadeDado(int quantidadeDado) {
        this.quantidadeDado = quantidadeDado;
    }
}

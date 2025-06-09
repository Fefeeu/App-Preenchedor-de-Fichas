package br.rpp.magias;

import br.rpp.dado.Dado;

public class MagiaDano extends Magia {
    public int dadoDano;
    public int quantidadeDado;

    public MagiaDano(String tipo, String nome, String descricao, int nivel, String tempoConjuracao,
                     String duracao, String alcance, String area, String escola, String tipoAcerto,
                     int dadoDano, int quantidadeDado) {
        super(tipo, nome, descricao, nivel, tempoConjuracao, duracao, alcance, area, escola, tipoAcerto);
        this.dadoDano = dadoDano;
        this.quantidadeDado = quantidadeDado;
    }

    @Override
    public int usarMagia() {
        int dano = Dado.rollD(this.dadoDano, this.quantidadeDado);
        return dano;
    }
}

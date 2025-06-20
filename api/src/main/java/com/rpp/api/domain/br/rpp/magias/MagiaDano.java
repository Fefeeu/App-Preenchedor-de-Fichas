package com.rpp.api.domain.br.rpp.magias;

import com.rpp.api.domain.br.rpp.auxiliar.enuns.TiposMagia;
import com.rpp.api.domain.br.rpp.auxiliar.exeptions.DadoInvalidoException;
import com.rpp.api.domain.br.rpp.auxiliar.exeptions.ValorMenorQueUmException;
import com.rpp.api.domain.br.rpp.dado.Dado;

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
        if (!Dado.verificaLados(dadoDano)) {
            throw new DadoInvalidoException();
        }

        try{
            this.dadoDano = dadoDano;
        } catch(NullPointerException e){
            System.out.println("valor do dadoDano não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public int getQuantidadeDado() {
        return quantidadeDado;
    }

    public void setQuantidadeDado(int quantidadeDado) {
        if (quantidadeDado < 1) {
            throw new ValorMenorQueUmException();
        }

        try{
            this.quantidadeDado = quantidadeDado;
        } catch(NullPointerException e){
            System.out.println("valor do quantidadeDado não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }
}

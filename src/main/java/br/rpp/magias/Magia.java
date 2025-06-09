package br.rpp.magias;

import br.rpp.sql.SQLMagia;

import java.sql.SQLException;

public class Magia {
    private int idMagia;
    public String tipo;
    public String nome;
    public String descricao;
    public int nivel;
    public String tempoConjuracao;
    public String duracao;
    public String alcance;
    public String area;
    public String escola;
    public String tipoAcerto;

    public Magia(String tipo, String nome, String descricao, int nivel, String tempoConjuracao,
                 String duracao, String alcance, String area, String escola, String tipoAcerto) {
        this.tipo = tipo;
        this.nome = nome;
        this.descricao = descricao;
        this.nivel = nivel;
        this.tempoConjuracao = tempoConjuracao;
        this.duracao = duracao;
        this.alcance = alcance;
        this.area = area;
        this.escola = escola;
        this.tipoAcerto = tipoAcerto;

        idMagia = SQLMagia.gerarIdMagia();
    }


    public int usarMagia(){
        System.out.println("usando magia");
        return 0;
    }

    public int getIdMagia() {
        return idMagia;
    }
}

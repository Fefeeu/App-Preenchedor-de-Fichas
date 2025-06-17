package br.rpp.magias;

import br.rpp.auxiliar.enuns.TiposMagia;

public class Magia {
    private final int idMagia;
    private String tipo;
    private String nome;
    private String descricao;
    private int nivel;
    private String tempoConjuracao;
    private String duracao;
    private String alcance;
    private String area;
    private String escola;
    private String tipoAcerto;

    public Magia(int id, String nome, String descricao, int nivel, String tempoConjuracao,
                 String duracao, String alcance, String area, String escola, String tipoAcerto) {
        this.idMagia = id;
        this.tipo = TiposMagia.EFEITO.toString();
        this.nome = nome;
        this.descricao = descricao;
        this.nivel = nivel;
        this.tempoConjuracao = tempoConjuracao;
        this.duracao = duracao;
        this.alcance = alcance;
        this.area = area;
        this.escola = escola;
        this.tipoAcerto = tipoAcerto;


    }


    public int usarMagia(){
        System.out.println("usando magia");
        return -1;
    }

    public int getIdMagia() {
        return idMagia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getTempoConjuracao() {
        return tempoConjuracao;
    }

    public void setTempoConjuracao(String tempoConjuracao) {
        this.tempoConjuracao = tempoConjuracao;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public String getTipoAcerto() {
        return tipoAcerto;
    }

    public void setTipoAcerto(String tipoAcerto) {
        this.tipoAcerto = tipoAcerto;
    }
}
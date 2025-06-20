package br.rpp.magias;

import br.rpp.auxiliar.enuns.TiposMagia;
import br.rpp.auxiliar.exeptions.NivelMagiaException;

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
        try {
            this.tipo = tipo;
        } catch (NullPointerException e) {
            System.out.println("valor do tipo não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        try {
            this.nome = nome;
        } catch (NullPointerException e) {
            System.out.println("valor do nome não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        try {
            this.descricao = descricao;
        } catch (NullPointerException e) {
            System.out.println("valor do descricao não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        if(nivel < 0 || nivel > 9) {
            throw new NivelMagiaException();
        }

        try {
            this.nivel = nivel;
        } catch (NullPointerException e) {
            System.out.println("valor do nivel não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getTempoConjuracao() {
        return tempoConjuracao;
    }

    public void setTempoConjuracao(String tempoConjuracao) {
        try {
            this.tempoConjuracao = tempoConjuracao;
        } catch (NullPointerException e) {
            System.out.println("valor do tempoConjuracao não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        try {
            this.duracao = duracao;
        } catch (NullPointerException e) {
            System.out.println("valor do duracao não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        try {
            this.alcance = alcance;
        } catch (NullPointerException e) {
            System.out.println("valor do alcance não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        try {
            this.area = area;
        } catch (NullPointerException e) {
            System.out.println("valor do area não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        try {
            this.escola = escola;
        } catch (NullPointerException e) {
            System.out.println("valor do escola não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getTipoAcerto() {
        return tipoAcerto;
    }

    public void setTipoAcerto(String tipoAcerto) {
        try {
            this.tipoAcerto = tipoAcerto;
        } catch (NullPointerException e) {
            System.out.println("valor do tipoAcerto não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }
}
package com.rpp.api.domain.br.rpp.ficha;

public class Descricao {

    private String historia;
    private String aparencia;
    private String personalidade;
    private String ideal;
    private String ligacao;
    private String defeitos;

    public Descricao(String historia, String aparencia, String personalidade, String ideal, String ligacao, String defeitos) {
        this.historia = historia;
        this.aparencia = aparencia;
        this.personalidade = personalidade;
        this.ideal = ideal;
        this.ligacao = ligacao;
        this.defeitos = defeitos;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        try {
            this.historia = historia;
        } catch (NullPointerException e) {
            System.out.println("valor do historia não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getAparencia() {
        return aparencia;
    }

    public void setAparencia(String aparencia) {
        try {
            this.aparencia = aparencia;
        } catch (NullPointerException e) {
            System.out.println("valor do aparencia não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getPersonalidade() {
        return personalidade;
    }

    public void setPersonalidade(String personalidade) {
        try {
            this.personalidade = personalidade;
        } catch (NullPointerException e) {
            System.out.println("valor do personalidade não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getIdeal() {
        return ideal;
    }

    public void setIdeal(String ideal) {
        try {
            this.ideal = ideal;
        } catch (NullPointerException e) {
            System.out.println("valor do ideal não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getLigacao() {
        return ligacao;
    }

    public void setLigacao(String ligacao) {
        try {
            this.ligacao = ligacao;
        } catch (NullPointerException e) {
            System.out.println("valor do ligacao não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getDefeitos() {
        return defeitos;
    }

    public void setDefeitos(String defeitos) {
        try {
            this.defeitos = defeitos;
        } catch (NullPointerException e) {
            System.out.println("valor do defeitos não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }
}
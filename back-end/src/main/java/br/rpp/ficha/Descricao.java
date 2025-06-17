package br.rpp.ficha;

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
        this.historia = historia;
    }

    public String getAparencia() {
        return aparencia;
    }

    public void setAparencia(String aparencia) {
        this.aparencia = aparencia;
    }

    public String getPersonalidade() {
        return personalidade;
    }

    public void setPersonalidade(String personalidade) {
        this.personalidade = personalidade;
    }

    public String getIdeal() {
        return ideal;
    }

    public void setIdeal(String ideal) {
        this.ideal = ideal;
    }

    public String getLigacao() {
        return ligacao;
    }

    public void setLigacao(String ligacao) {
        this.ligacao = ligacao;
    }

    public String getDefeitos() {
        return defeitos;
    }

    public void setDefeitos(String defeitos) {
        this.defeitos = defeitos;
    }
}

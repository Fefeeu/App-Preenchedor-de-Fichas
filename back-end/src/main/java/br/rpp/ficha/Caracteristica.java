package br.rpp.ficha;

import br.rpp.sql.SQLClasse;
import br.rpp.sql.SQLRaca;

public class Caracteristica {
    private String nomePersonagem;
    private Classe classe;
    private String antecedente;
    private String userName;
    private Raca raca;
    private String tendencia;
    private int xp; //apenas valores positivos
    private int idade; //apenas valores positivos
    private float altura;
    private float peso;
    private String olho;
    private String pele;
    private String cabelo;
    private String idiomas;
    private String proficiencias;

    public Caracteristica(String nomePersonagem, String idClasse, String antecedente,
                          String userName, String idRaca, String tendencia,
                          int xp, int idade, float altura, float peso,
                          String olho, String pele, String cabelo,
                          String idiomas, String proficiencias)
    {
        this.nomePersonagem = nomePersonagem;
        this.classe = SQLClasse.readClasse(idClasse);
        this.antecedente = antecedente;
        this.userName = userName;
        this.raca = SQLRaca.readRaca(idRaca);
        this.tendencia = tendencia;
        this.xp = Math.max(0,xp);
        this.idade = Math.max(0,idade);
        this.altura = altura;
        this.peso = peso;
        this.olho = olho;
        this.pele = pele;
        this.cabelo = cabelo;
        this.idiomas = idiomas;
        this.proficiencias = proficiencias;
    }

    public String getUserName() {
        return userName;
    }

    public String getNomePersonagem() {
        return nomePersonagem;
    }

    public void setNomePersonagem(String nomePersonagem) {
        this.nomePersonagem = nomePersonagem;
    }

    public String getIdClasse() {
        return this.classe.getId();
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public String getAntecedente() {
        return antecedente;
    }

    public void setAntecedente(String antecedente) {
        this.antecedente = antecedente;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdRaca() {
        return this.raca.getId();
    }

    public void setRaca(Raca raca){
        this.raca = raca;
    }

    public String getTendencia() {
        return tendencia;
    }

    public void setTendencia(String tendencia) {
        this.tendencia = tendencia;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getOlho() {
        return olho;
    }

    public void setOlho(String olho) {
        this.olho = olho;
    }

    public String getPele() {
        return pele;
    }

    public void setPele(String pele) {
        this.pele = pele;
    }

    public String getCabelo() {
        return cabelo;
    }

    public void setCabelo(String cabelo) {
        this.cabelo = cabelo;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public String getProficiencias() {
        return proficiencias;
    }

    public void setProficiencias(String proficiencias) {
        this.proficiencias = proficiencias;
    }
}

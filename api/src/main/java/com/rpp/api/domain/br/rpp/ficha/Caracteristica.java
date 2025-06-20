package com.rpp.api.domain.br.rpp.ficha;

import com.rpp.api.domain.br.rpp.auxiliar.exeptions.ValorNegativoException;
import com.rpp.api.domain.br.rpp.sql.SQLClasse;
import com.rpp.api.domain.br.rpp.sql.SQLRaca;

import java.util.stream.Stream;

public class Caracteristica {
    private String nomePersonagem;
    private Classe classe;
    private String antecedente;
    private String userName;
    private Raca raca;
    private String tendencia;
    private int xp; //apenas valores positivos
    private int idade; //apenas valores positivos
    private float altura; //apenas valores positivos
    private float peso; //apenas valores positivos
    private String olho;
    private String pele;
    private String cabelo;
    private String idiomas;
    private String proficiencias;

    public Caracteristica(String nomePersonagem, String idClasse, String antecedente,
                          String userName, String idRaca, String tendencia,
                          int xp, int idade, float altura, float peso,
                          String olho, String pele, String cabelo,
                          String idiomas, String proficiencias) {
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
        try {
            this.nomePersonagem = nomePersonagem;
        } catch (NullPointerException e) {
            System.out.println("valor do nomePersonagem não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getIdClasse() {
        return this.classe.getId();
    }

    public void setClasse(Classe classe) {
        try {
            this.classe = classe;
        } catch (NullPointerException e) {
            System.out.println("valor do classe não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getAntecedente() {
        return antecedente;
    }

    public void setAntecedente(String antecedente) {
        try {
            this.antecedente = antecedente;
        } catch (NullPointerException e) {
            System.out.println("valor do antecedente não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public void setUserName(String userName) {
        try {
            this.userName = userName;
        } catch (NullPointerException e) {
            System.out.println("valor do userName não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getIdRaca() {
        return this.raca.getId();
    }

    public void setRaca(Raca raca) {
        try {
            this.raca = raca;
        } catch (NullPointerException e) {
            System.out.println("valor do raca não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getTendencia() {
        return tendencia;
    }

    public void setTendencia(String tendencia) {
        try {
            this.tendencia = tendencia;
        } catch (NullPointerException e) {
            System.out.println("valor do tendencia não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        if (xp < 0) {
            throw new ValorNegativoException();
        }

        try {
            this.xp = xp;
        } catch (NullPointerException e) {
            System.out.println("valor do xp não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade < 0) {
            throw new ValorNegativoException();
        }

        try {
            this.idade = idade;
        } catch (NullPointerException e) {
            System.out.println("valor do idade não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        if (altura <= 0) {
            throw new ValorNegativoException();
        }

        try {
            this.altura = altura;
        } catch (NullPointerException e) {
            System.out.println("valor do altura não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        if (peso <= 0) {
            throw new ValorNegativoException();
        }

        try {
            this.peso = peso;
        } catch (NullPointerException e) {
            System.out.println("valor do peso não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getOlho() {
        return olho;
    }

    public void setOlho(String olho) {
        try {
            this.olho = olho;
        } catch (NullPointerException e) {
            System.out.println("valor do olho não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getPele() {
        return pele;
    }

    public void setPele(String pele) {
        try {
            this.pele = pele;
        } catch (NullPointerException e) {
            System.out.println("valor do pele não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getCabelo() {
        return cabelo;
    }

    public void setCabelo(String cabelo) {
        try {
            this.cabelo = cabelo;
        } catch (NullPointerException e) {
            System.out.println("valor do cabelo não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        try {
            this.idiomas = idiomas;
        } catch (NullPointerException e) {
            System.out.println("valor do idiomas não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public String getProficiencias() {
        return proficiencias;
    }

    public void setProficiencias(String proficiencias) {
        try {
            this.proficiencias = proficiencias;
        } catch (NullPointerException e) {
            System.out.println("valor do proficiencias não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }
}
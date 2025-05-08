package br.inatel.cdg.usuario;

import br.inatel.cdg.ficha.Ficha;

import java.util.ArrayList;

public class Usuario {
    private String email;
    private String userName;
    private String senha;
    private ArrayList<Ficha> fichas = new ArrayList<Ficha>();

    public void setEmail(String email) {
        this.email = email;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void criarNovaFicha(){
        Ficha novaFicha = new Ficha();
        fichas.add(novaFicha);
    }

}

package br.rpp.usuario;

import br.rpp.ficha.Ficha;

import java.util.ArrayList;

public class User {

    private String userName;
    private String email;
    private String senha;
    private ArrayList<Ficha> fichas;

    public User(String userName, String email, String senha) {
        this.userName = userName;
        this.email = email;
        this.senha = senha;
        ArrayList<Ficha> fichas = new ArrayList<>();
    }

    public void criaFicha() {

    }

    public void removerFicha() {}

    public void logout(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

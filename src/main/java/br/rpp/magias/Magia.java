package br.rpp.magias;

public class Magia {
    private String nome;
    String descricao;
    int nivel;
    String tempoConjuracao;
    String duracao;
    float alcance;
    String area;
    String escola;
    char tipo;

    public Magia(String nome, String descricao, int nivel, String tempoConjuracao,
                 String duracao, float alcance, String area, String escola, char tipo) {
        this.nome = nome;
        this.descricao = descricao;
        this.nivel = nivel;
        this.tempoConjuracao = tempoConjuracao;
        this.duracao = duracao;
        this.alcance = alcance;
        this.area = area;
        this.escola = escola;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public int getNivel() {
        return nivel;
    }



    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da magia não pode ser vazio!");
        }
        this.nome = nome;
    }


    public void usarMagia(){
        System.out.println(nome + " " + descricao + " " + nivel + " " + tempoConjuracao ); //analisar melhor o que será mostrado
    }
}

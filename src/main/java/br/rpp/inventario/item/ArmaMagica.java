package br.rpp.inventario.item;

import br.rpp.auxiliar.Dado;
import br.rpp.ficha.Ficha;
import br.rpp.interfaces.Usavel;

public class ArmaMagica extends Arma implements Usavel {
    public String efeito;
    private int cargas;
    private int cargasMaxima;
    public int bonus;
    private Ficha ficha;

    public ArmaMagica(Ficha ficha, String nome, String descricao, float peso, char moeda, int preco, int dado, int quantidade, String atributo, boolean proficiencia, String efeito, int cargas, int bonus) {
        super(ficha, nome, descricao, peso, moeda, preco, dado, quantidade, atributo, proficiencia);
        this.efeito = efeito;
        this.cargas = cargas;
        this.bonus = bonus;

        this.ficha = ficha;
    }


    @Override
    public void usar(){
        if (this.cargas > 0){
            this.cargas--;
        } else {
            System.out.println("Cargas Insuficiente");
        }
    }

    @Override
    public void recuperarUsos(){
        this.cargas = this.cargasMaxima;
    }

    @Override
    public void recuperarQuantidadeDeUsos(int usos){
        usos = Math.max(0, usos); // garante que não seja negativo
        if(usos > cargasMaxima - cargas){
            this.cargas = this.cargasMaxima;
        } else {
            this.cargas+= usos;
        }
    }

    @Override
    public int rodarDadoTeste(String tipo) {
        if (proficiencia) {
            return Dado.rollD20(tipo) + ficha.atributos.get(tipo) + this.bonus;
        }
        return Dado.rollD20(tipo) + this.bonus;
    }
}

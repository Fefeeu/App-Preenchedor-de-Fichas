package br.rpp.inventario.item;

import br.rpp.dado.Dado;
import br.rpp.ficha.Ficha;
import br.rpp.interfaces.Roll20;
import br.rpp.interfaces.RollADice;
import br.rpp.interfaces.Usavel;

public class ArmaMagica extends Arma implements Usavel, Roll20, RollADice {
    public String efeito;
    private int cargas;
    private int cargasMaxima;
    public int bonus;

    public ArmaMagica(int id, Ficha ficha, String nome, String descricao, float peso, char moeda, int preco, int dado, int quantidade, String atributo, boolean proficiencia, String efeito, int cargas, int bonus) {
        super(id, ficha, nome, descricao, peso, moeda, preco, dado, quantidade, atributo, proficiencia);
        this.efeito = efeito;
        this.cargas = cargas;
        this.bonus = bonus;

        this.setTipo("armaMagica");
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
        int valor = Dado.rollD20(tipo) + this.ficha.atributos.get(atributo) + this.bonus;
        if (proficiencia) {
            return valor + Ficha.getProficiencia(this.ficha.nivel);
        }
        return valor;
    }

    @Override
    public int rodarDado() {
        return Dado.rollD(this.dadoDeDano, this.quantidadeDeDados) + this.ficha.atributos.get(atributo) + this.bonus;
    }

    public int getCargasMaxima() {
        return cargasMaxima;
    }

    public int getCargas(){
        return cargas;
    }
}

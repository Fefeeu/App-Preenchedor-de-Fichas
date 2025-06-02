package br.rpp.inventario.item;

import br.rpp.ficha.Ficha;
import br.rpp.interfaces.Usavel;

import java.util.Scanner;

public class ItemConsumivel extends Item implements Usavel {
    private int usos = 0;
    private int usosMaximo = 0;
    Ficha ficha;

    public ItemConsumivel(Ficha ficha, String nome, String descricao, float peso, char moeda, int preco, int usos) {
        super(nome, descricao, peso, moeda, preco);
        this.usos = usos;
        usosMaximo = usos;

        this.ficha = ficha;
    }

    @Override
    public void usar(){
        if (this.usos > 0){
            this.usos--;
        } else {
            System.out.println("Cargas Insuficiente");
        }

        if (this.usos == 0){
            Scanner sc = new Scanner(System.in);
            boolean descartar = sc.nextBoolean(); // TODO(front): entrada de dados
            if(descartar){
                ficha.inventario.descartarItem(this);
            }
        }
    }

    @Override
    public void recuperarUsos(){
        this.usos = this.usosMaximo;
    }

    @Override
    public void recuperarQuantidadeDeUsos(int usos){
        usos = Math.max(0, usos); // garante que não seja negativo
        if(usos > usosMaximo - usos){
            this.usos = this.usosMaximo;
        } else {
            this.usos+= usos;
        }
    }

}

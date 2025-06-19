package com.rpp.api.domain.br.rpp.inventario.item;

import com.rpp.api.domain.br.rpp.auxiliar.exeptions.ValorNegativoException;
import com.rpp.api.domain.br.rpp.interfaces.ItemUsavel;

public class ItemConsumivel extends Item implements ItemUsavel {
    private int usos = 0;
    private int usosMaximo = 0;

    public ItemConsumivel(int id, String nome, String descricao, float peso, char moeda, int preco, int usos) {
        super(id, nome, descricao, peso, moeda, preco);
        this.usos = usos;
        usosMaximo = usos;

        this.setTipo("consumivel");
    }

    @Override
    public void usar() {
        if (this.usos > 0) {
            this.usos--;
        } else {
            System.out.println("Cargas Insuficiente");
        }
    }

    @Override
    public void recuperarUsos() {
        this.usos = this.usosMaximo;
    }

    @Override
    public void recuperarQuantidadeDeUsos(int usos) {
        usos = Math.max(0, usos); // garante que não seja negativo
        if(usos > usosMaximo - usos) {
            this.usos = this.usosMaximo;
        } else {
            this.usos += usos;
        }
    }

    public int getUsos() {
        return usos;
    }

    public void setUsos(int usos) {
        if (usos < 0){
            throw new ValorNegativoException();
        }

        try {
            this.usos = usos;
        } catch (NullPointerException e) {
            System.out.println("valor do usos não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public int getUsosMaximo() {
        return usosMaximo;
    }

    public void setUsosMaximo(int usosMaximo) {
        try {
            this.usosMaximo = usosMaximo;
        } catch (NullPointerException e) {
            System.out.println("valor do usosMaximo não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }
}
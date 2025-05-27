package br.rpp.inventario.item;

import br.rpp.interfaces.Consumivel;

public class EquipavelMagico implements Consumivel {
    public String efeito;
    private int cargas;
    private int cargasMaxima;
    public int bonus;

    public EquipavelMagico(String efeito, int cargas, int bonus){
        this.efeito = efeito;
        this.cargas = cargas;
        this.cargasMaxima = cargas;
        this.bonus = bonus;
    }

    @Override
    public void usar(){
        // --cargas
        // exeption, se nao tiver carga
    }

    @Override
    public void recuperarUsos(){
        // cargas ++
        // exeption, se nao tiver carga
    }

    @Override
    public void recuperarQuantidadeDeUsos(int usos){
        // cargas += usos
    }
}

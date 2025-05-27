package br.rpp.inventario;

import br.rpp.inventario.item.Item;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Item> itens;
    public int pc = 0; // Peça de Cobre
    public int pp = 0; // Peça de Prata
    public int pe = 0; // Peça de Electro
    public int po = 0; // Peça de Ouro
    public int pl = 0; // Peça de pLatina

    public Inventario() {
        itens = new ArrayList<Item>();
    }

    public void guardarItem(){
        // criar item e adicionar
        // perguntar o tipo de item antes de criar
    }

    public void descartarItem(Item item){
        // remove o item
    }

    public void venderItem(Item item){
        // remove o item e adiciona moeda ao inventario
    }
}

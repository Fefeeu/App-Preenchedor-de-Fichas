package br.rpp.inventario;

import br.rpp.inventario.item.*;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Item> itens;
    public int pc = 0; // Peça de Cobre
    public int pp = 0; // Peça de Prata
    public int pe = 0; // Peça de Electro
    public int po = 0; // Peça de Ouro
    public int pl = 0; // Peça de pLatina

    public Inventario() {
        itens = new ArrayList<>();
    }

    private Item criarItem(int tipo){ // TODO(front): entrada de dados
        String nome = "";
        String descricao = "";
        float peso = 0.01f;
        char moeda = 'o';
        int preco = 0;

        switch(tipo){
            case 0:{    // Item
                return new Item(nome, descricao, peso, moeda, preco);
            }
            case 1:{    // Consumivel
                int usos = 0;
                return new ItemConsumivel(nome, descricao, peso, moeda, preco, usos);
            }
            case 2:{    // Magico
                String efeito = "";
                return new ItemMagico(nome, descricao, peso, moeda, preco, efeito);
            }
            case 3:{    // Arma
                int dado = 1;
                int quantidade = 1;
                String atributo = "";
                boolean proficiencia = false;
                return new Arma(nome, descricao, peso, moeda, preco, dado, quantidade, atributo, proficiencia);
            }
            case 4:{    // Arma Magica
                int dado = 1;
                int quantidade = 1;
                String atributo = "";
                boolean proficiencia = false;
                int bonus = 0;
                String efeito = "";
                int usos = 0;

                return new ArmaMagica(nome, descricao, peso, moeda, preco, dado, quantidade,
                                    atributo, proficiencia, efeito, usos, bonus);
            }
            case 5:{    // Equipavel
                int bonusCA = 0;
                boolean proficiencia = false;
                return new Equipavel(nome, descricao, peso, moeda, preco, bonusCA, proficiencia);
            }
            case 6:{    // Equipavel Magico
                int bonusCA = 0;
                boolean proficiencia = false;
                String efeito = "";
                int usos = 0;
                int bonus = 0;
                return new EquipavelMagico(nome, descricao, peso, moeda, preco, bonusCA,
                                        proficiencia, efeito, usos, bonus);
            }
            default: return new Item("genérico", "genérico", 0.01f, 'o', 0);
        }
    }

    public void guardarItem(){

        int tipoDoItem = 0; // TODO(front): entrada de dados com seleção de opções
        itens.add(criarItem(tipoDoItem));
    }

    public void descartarItem(Item item){   // TODO(front): seleciona o item e a opcao de descartar
        itens.remove(item);
    }

    public void venderItem(Item item){  // TODO(front): seleciona o item e a opcao de vender
        venderItemPersonalizado(item, item.moeda, item.preco);
    }

    public void venderItemPersonalizado(Item item, char moeda, int valor){
        boolean valido = true;
        switch (moeda){
            case 'c': this.pc += valor;
            case 'p': this.pp += valor;
            case 'e': this.pe += valor;
            case 'o': this.po += valor;
            case 'l': this.pl += valor;
            default: valido = false;
        }
        if(valido){
            itens.remove(item);
        } else {
            System.out.println("compra negada, moeda invalida");
        }
    }
}
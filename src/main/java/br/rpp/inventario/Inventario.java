package br.rpp.inventario;

import br.rpp.ficha.Ficha;
import br.rpp.inventario.item.*;
import br.rpp.sql.SQLItem;

import java.sql.*;
import java.util.ArrayList;

public class Inventario {
    private ArrayList<Item> itens;
    public int pc = 0; // Peça de Cobre
    public int pp = 0; // Peça de Prata
    public int pe = 0; // Peça de Electro
    public int po = 0; // Peça de Ouro
    public int pl = 0; // Peça de pLatina
    private Ficha ficha;

    public Inventario(Ficha ficha) {
        itens = new ArrayList<>();
        this.ficha = ficha;
    }

    private Item criarItem(String tipo) throws SQLException { // TODO(front): entrada de dados
        int id = SQLItem.gerarIdItem();
        String nome = "";       // TODO(front): entrada de dados
        String descricao = "";  // TODO(front): entrada de dados
        float peso = 0.01f;     // TODO(front): entrada de dados
        char moeda = 'o';       // TODO(front): entrada de dados
        int preco = 0;          // TODO(front): entrada de dados

        switch(tipo){
            case "comum":{    // Item
                return new Item(id, nome, descricao, peso, moeda, preco);
            }
            case "consumivel":{    // Consumivel
                int usos = 0;   // TODO(front): entrada de dados
                return new ItemConsumivel(id, nome, descricao, peso, moeda, preco, usos);
            }
            case "magico":{    // Magico
                String efeito = ""; // TODO(front): entrada de
                int cargas = 0;
                return new ItemMagico(id, nome, descricao, peso, moeda, preco, efeito, cargas);
            }
            case "arma":{    // Arma
                int dado = 1;                   // TODO(front): entrada de dados
                int quantidade = 1;             // TODO(front): entrada de dados
                String atributo = "";           // TODO(front): entrada de dados
                boolean proficiencia = false;   // TODO(front): entrada de dados
                return new Arma(id, this.ficha, nome, descricao, peso, moeda, preco, dado, quantidade, atributo, proficiencia);
            }
            case "armaMagica":{    // Arma Magica
                int dado = 1;                   // TODO(front): entrada de dados
                int quantidade = 1;             // TODO(front): entrada de dados
                String atributo = "";           // TODO(front): entrada de dados
                boolean proficiencia = false;   // TODO(front): entrada de dados
                int bonus = 0;                  // TODO(front): entrada de dados
                String efeito = "";             // TODO(front): entrada de dados
                int usos = 0;                   // TODO(front): entrada de dados

                return new ArmaMagica(id, this.ficha, nome, descricao, peso, moeda, preco, dado, quantidade,
                        atributo, proficiencia, efeito, usos, bonus);
            }
            case "equipavel":{    // Equipavel
                int bonusCA = 0;                // TODO(front): entrada de dados
                boolean proficiencia = false;   // TODO(front): entrada de dados
                return new Equipavel(id, nome, descricao, peso, moeda, preco, bonusCA, proficiencia);
            }
            case "equipavelMagico":{    // Equipavel Magico
                int bonusCA = 0;                // TODO(front): entrada de dados
                boolean proficiencia = false;   // TODO(front): entrada de dados
                String efeito = "";             // TODO(front): entrada de dados
                int usos = 0;                   // TODO(front): entrada de dados
                int bonus = 0;                  // TODO(front): entrada de dados
                return new EquipavelMagico(id, nome, descricao, peso, moeda, preco, bonusCA,
                        proficiencia, efeito, usos, bonus);
            }
            default: return new Item(id, "genérico", "genérico", 0.01f, 'o', 0);
        }
    }

    public void guardarItem() throws SQLException {

        String tipoDoItem = "comum"; // TODO(front): entrada de dados com seleção de opções
        itens.add(criarItem(tipoDoItem));
    }

    public void descartarItem(Item item){   // TODO(front): seleciona o item e a opcao de descartar
        itens.remove(item);
    }

    public void venderItem(Item item){  // TODO(front): seleciona o item e a opcao de vender
        venderItemPersonalizado(item, item.moeda, item.preco);
    }

    public void venderItemPersonalizado(Item item, char moeda, int valor){  // TODO(front): entrada de dados
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
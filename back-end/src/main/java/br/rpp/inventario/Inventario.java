package br.rpp.inventario;

import br.rpp.auxiliar.enuns.Tabelas;
import br.rpp.auxiliar.exeptions.TipoItemException;
import br.rpp.ficha.Ficha;
import br.rpp.inventario.item.*;
import br.rpp.sql.BD;
import br.rpp.sql.SQLFicha;
import br.rpp.sql.SQLItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Inventario {
    private final int id;
    private HashMap<Integer,Item> itens;
    private int pc = 0; // Peça de Cobre
    private int pp = 0; // Peça de Prata
    private int pe = 0; // Peça de Electro
    private int po = 0; // Peça de Ouro
    private int pl = 0; // Peça de pLatina

    public Inventario(int id) {
        this.itens = new HashMap<>();
        this.id = id;
    }

    public Item criarItem(String tipo, Ficha ficha) { // TODO(front): entrada de dados
        Item novoItem;
        int id = BD.gerarId(Tabelas.ITEM.toString());
        String nome = "";       // TODO(front): entrada de dados
        String descricao = "";  // TODO(front): entrada de dados
        float peso = 0.01f;     // TODO(front): entrada de dados
        char moeda = 'o';       // TODO(front): entrada de dados
        int preco = 0;          // TODO(front): entrada de dados

        switch(tipo){
            case "comum":{    // Item
                novoItem =  new Item(id, nome, descricao, peso, moeda, preco);
                break;
            }
            case "consumivel":{    // Consumivel
                int usos = 0;   // TODO(front): entrada de dados
                novoItem =  new ItemConsumivel(id, nome, descricao, peso, moeda, preco, usos);
                break;
            }
            case "magico":{    // Magico
                String efeito = ""; // TODO(front): entrada de
                int cargas = 0;
                novoItem =  new ItemMagico(id, nome, descricao, peso, moeda, preco, efeito, cargas);
                break;
            }
            case "arma":{    // Arma
                int dado = 1;                   // TODO(front): entrada de dados
                int quantidade = 1;             // TODO(front): entrada de dados
                String atributo = "";           // TODO(front): entrada de dados
                boolean proficiencia = false;   // TODO(front): entrada de dados
                novoItem =  new Arma(id, ficha, nome, descricao, peso, moeda, preco, dado, quantidade, atributo, proficiencia);
                break;
            }
            case "armaMagica":{    // Arma Magica
                int dado = 1;                   // TODO(front): entrada de dados
                int quantidade = 1;             // TODO(front): entrada de dados
                String atributo = "";           // TODO(front): entrada de dados
                boolean proficiencia = false;   // TODO(front): entrada de dados
                int bonus = 0;                  // TODO(front): entrada de dados
                String efeito = "";             // TODO(front): entrada de dados
                int usos = 0;                   // TODO(front): entrada de dados

                novoItem =  new ArmaMagica(id, ficha, nome, descricao, peso, moeda, preco, dado, quantidade,
                        atributo, proficiencia, efeito, usos, bonus);
                break;
            }
            case "equipavel":{    // Equipavel
                int bonusCA = 0;                // TODO(front): entrada de dados
                boolean proficiencia = false;   // TODO(front): entrada de dados
                novoItem =  new Equipavel(id, nome, descricao, peso, moeda, preco, bonusCA, proficiencia);
                break;
            }
            case "equipavelMagico":{    // Equipavel Magico
                int bonusCA = 0;                // TODO(front): entrada de dados
                boolean proficiencia = false;   // TODO(front): entrada de dados
                String efeito = "";             // TODO(front): entrada de dados
                int usos = 0;                   // TODO(front): entrada de dados
                int bonus = 0;                  // TODO(front): entrada de dados
                novoItem =  new EquipavelMagico(id, nome, descricao, peso, moeda, preco, bonusCA,
                        proficiencia, efeito, usos, bonus);
                break;
            }
            default: throw new TipoItemException();
        }
        guardarItem(novoItem, false);
        return novoItem;
    }

    public void guardarItem(Item item, boolean read) {
        try {
            this.itens.put(item.getId(), item);
            if (!read){
                SQLItem.createItem(this, item);
            }
        } catch (NullPointerException e) {
            System.out.println("item nao encontrado");
            System.out.println(e.getMessage());
        }

    }

    public void descartarItem(Item item){   // TODO(front): seleciona o item e a opcao de descartar
        try {
            itens.remove(item);
            SQLItem.deleteItem(item.getId());
        } catch (NullPointerException e) {
            System.out.println("item nao encontrado");
            System.out.println(e.getMessage());
        }
    }

    public void venderItem(Item item){  // TODO(front): seleciona o item e a opcao de vender
        venderItemPersonalizado(item, item.getMoeda(), item.getPreco());
    }

    public void venderItemPersonalizado(Item item, char moeda, int valor){  // TODO(front): entrada de dados
        try {
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
                SQLItem.deleteItem(item.getId());
            } else {
                System.out.println("compra negada, moeda invalida");
            }
        } catch (NullPointerException e) {
            System.out.println("item nao encontrado");
            System.out.println(e.getMessage());
        }
    }

    public int getId() {
        return id;
    }

    public void setMoedas(char moeda, int valor) {
        switch (moeda){
            case 'c': this.pc = valor;
            case 'p': this.pp = valor;
            case 'e': this.pe = valor;
            case 'o': this.po = valor;
            case 'l': this.pl = valor;
            default: System.out.println("moeda invalida");
        }
    }

    public int getMoedas(char moeda) {
        return switch (moeda) {
            case 'c' -> this.pc;
            case 'p' -> this.pp;
            case 'e' -> this.pe;
            case 'o' -> this.po;
            case 'l' -> this.pl;
            default -> 0;
        };
    }

    public Item getItem(int id) {
        return itens.get(id);
    }

}
package com.rpp.api.domain.br.rpp.inventario;

import com.rpp.api.domain.br.rpp.auxiliar.enuns.Tabelas;
import com.rpp.api.domain.br.rpp.auxiliar.exeptions.TipoItemException;
import com.rpp.api.domain.br.rpp.ficha.Ficha;
import com.rpp.api.domain.br.rpp.inventario.item.*;
import com.rpp.api.domain.br.rpp.sql.BD;
import com.rpp.api.domain.br.rpp.sql.SQLFicha;
import com.rpp.api.domain.br.rpp.sql.SQLItem;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Inventario {
    private final int id;
    private HashMap<Integer,Item> itens;
    @Getter
    private int pc = 0; // Peça de Cobre
    @Getter
    private int pp = 0; // Peça de Prata
    @Getter
    private int pe = 0; // Peça de Electro
    @Getter
    private int po = 0; // Peça de Ouro
    @Getter
    private int pl = 0; // Peça de pLatina

    public Inventario(int id) {
        this.itens = new HashMap<>();
        this.id = id;
    }

    public Item criarItem(String tipo, Ficha ficha) {
        Item novoItem;
        int id = BD.gerarId(Tabelas.ITEM.toString());
        String nome = "";
        String descricao = "";
        float peso = 0.01f;
        char moeda = 'o';
        int preco = 0;

        switch(tipo){
            case "comum":{    // Item
                novoItem =  new Item(id, nome, descricao, peso, moeda, preco);
                break;
            }
            case "consumivel":{    // Consumivel
                int usos = 0;
                novoItem =  new ItemConsumivel(id, nome, descricao, peso, moeda, preco, usos);
                break;
            }
            case "magico":{    // Magico
                String efeito = "";
                int cargas = 0;
                novoItem =  new ItemMagico(id, nome, descricao, peso, moeda, preco, efeito, cargas);
                break;
            }
            case "arma":{    // Arma
                int dado = 1;
                int quantidade = 1;
                String atributo = "";
                boolean proficiencia = false;
                novoItem =  new Arma(id, ficha, nome, descricao, peso, moeda, preco, dado, quantidade, atributo, proficiencia);
                break;
            }
            case "armaMagica":{    // Arma Magica
                int dado = 1;
                int quantidade = 1;
                String atributo = "";
                boolean proficiencia = false;
                int bonus = 0;
                String efeito = "";
                int usos = 0;

                novoItem =  new ArmaMagica(id, ficha, nome, descricao, peso, moeda, preco, dado, quantidade,
                        atributo, proficiencia, efeito, usos, bonus);
                break;
            }
            case "equipavel":{    // Equipavel
                int bonusCA = 0;
                boolean proficiencia = false;
                novoItem =  new Equipavel(id, nome, descricao, peso, moeda, preco, bonusCA, proficiencia);
                break;
            }
            case "equipavelMagico":{    // Equipavel Magico
                int bonusCA = 0;
                boolean proficiencia = false;
                String efeito = "";
                int usos = 0;
                int bonus = 0;
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

    public void descartarItem(Item item){
        try {
            itens.remove(item);
            SQLItem.deleteItem(item.getId());
        } catch (NullPointerException e) {
            System.out.println("item nao encontrado");
            System.out.println(e.getMessage());
        }
    }

    public void venderItem(Item item){
        venderItemPersonalizado(item, item.getMoeda(), item.getPreco());
    }

    public void venderItemPersonalizado(Item item, char moeda, int valor){
        try {
            boolean valido = true;
            switch (moeda){
                case 'c': this.pc += valor;
                break;
                case 'p': this.pp += valor;
                break;
                case 'e': this.pe += valor;
                break;
                case 'o': this.po += valor;
                break;
                case 'l': this.pl += valor;
                break;
                default: valido = false;
                break;
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
            case 'c': {
                this.pc = valor;
                break;
            }
            case 'p': {
                this.pp = valor;
                break;
            }
            case 'e': {
                this.pe = valor;
                break;
            }
            case 'o': {
                this.po = valor;
                break;
            }
            case 'l': {
                this.pl = valor;
                break;
            }
            default: {
                System.out.println("moeda invalida");
                break;
            }
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
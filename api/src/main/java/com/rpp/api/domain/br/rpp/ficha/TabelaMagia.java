package com.rpp.api.domain.br.rpp.ficha;

import com.rpp.api.domain.br.rpp.auxiliar.enuns.TiposAcertosMagia;
import com.rpp.api.domain.br.rpp.auxiliar.exeptions.NivelMagiaException;
import com.rpp.api.domain.br.rpp.auxiliar.exeptions.TipoMagiaException;
import com.rpp.api.domain.br.rpp.auxiliar.exeptions.ValorNegativoException;
import com.rpp.api.domain.br.rpp.magias.Magia;
import com.rpp.api.domain.br.rpp.magias.MagiaCura;
import com.rpp.api.domain.br.rpp.magias.MagiaDano;
import com.rpp.api.domain.br.rpp.sql.BD;
import com.rpp.api.domain.br.rpp.sql.SQLMagia;
import com.rpp.api.domain.br.rpp.sql.SQLMagiaUser;
import com.rpp.api.domain.br.rpp.auxiliar.enuns.Tabelas;

import java.util.HashMap;

public class TabelaMagia {
    private static final int[][] espacosMagiaNivel = {
    //Nv 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Níveis de magia)
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // Nível 0 (inexistente, apenas para alinhar índices)
        {0, 2, 0, 0, 0, 0, 0, 0, 0, 0}, // Nível 1
        {0, 3, 0, 0, 0, 0, 0, 0, 0, 0}, // Nível 2
        {0, 4, 2, 0, 0, 0, 0, 0, 0, 0}, // Nível 3
        {0, 4, 3, 0, 0, 0, 0, 0, 0, 0}, // Nível 4
        {0, 4, 3, 2, 0, 0, 0, 0, 0, 0}, // Nível 5
        {0, 4, 3, 3, 0, 0, 0, 0, 0, 0}, // Nível 6
        {0, 4, 3, 3, 1, 0, 0, 0, 0, 0}, // Nível 7
        {0, 4, 3, 3, 2, 0, 0, 0, 0, 0}, // Nível 8
        {0, 4, 3, 3, 3, 1, 0, 0, 0, 0}, // Nível 9
        {0, 4, 3, 3, 3, 2, 0, 0, 0, 0}, // Nível 10
        {0, 4, 3, 3, 3, 2, 1, 0, 0, 0}, // Nível 11
        {0, 4, 3, 3, 3, 2, 1, 0, 0, 0}, // Nível 12
        {0, 4, 3, 3, 3, 2, 1, 1, 0, 0}, // Nível 13
        {0, 4, 3, 3, 3, 2, 1, 1, 0, 0}, // Nível 14
        {0, 4, 3, 3, 3, 2, 1, 1, 1, 0}, // Nível 15
        {0, 4, 3, 3, 3, 2, 1, 1, 1, 0}, // Nível 16
        {0, 4, 3, 3, 3, 2, 1, 1, 1, 1}, // Nível 17
        {0, 4, 3, 3, 3, 3, 1, 1, 1, 1}, // Nível 18
        {0, 4, 3, 3, 3, 3, 2, 1, 1, 1}, // Nível 19
        {0, 4, 3, 3, 3, 3, 2, 2, 1, 1}  // Nível 20
    };

    private int id;
    private HashMap<Integer, Magia> magias = new HashMap<>();
                        //    Nv 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Níveis de magia)
    private int[] espacoDeMagia = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private Ficha ficha;

    public TabelaMagia(int id, Ficha ficha) {
        this.id = id;
        this.ficha = ficha;
    }

    public void criarMagia(String tipo){
        int idMagia = BD.gerarId(Tabelas.MAGIA.toString());
        String nome = "nome";
        String descricao = "descricao";
        int nivel = 0;
        String tempoConjuracao = "tempoConjuracao";
        String duracao = "duracao";
        String alcance = "alcance";
        String area = "area";
        String escola = "escola";
        String tipoAcerto = TiposAcertosMagia.RESISTENCIA.toString();

        Magia novaMagia = null;

        switch (tipo) {
            case "efeito":{
                novaMagia = new Magia(idMagia, nome, descricao, nivel, tempoConjuracao, duracao, alcance, area, escola, tipoAcerto);
                break;
            }
            case "dano":{
                int dadoDano = 6;
                int quantidadeDado = 1;

                novaMagia = new MagiaDano(idMagia, nome, descricao, nivel, tempoConjuracao, duracao, alcance, area, escola, tipoAcerto, dadoDano, quantidadeDado);
                break;

            }
            case "cura":{
                int dadoCura = 6;
                int quantidadeDado = 1;
                novaMagia = new MagiaCura(idMagia, nome, descricao, nivel, tempoConjuracao, duracao, alcance, area, escola, tipoAcerto, dadoCura, quantidadeDado);
                break;
            }
            default: throw new TipoMagiaException();
        }

        this.magias.put(novaMagia.getIdMagia(), novaMagia);
        SQLMagia.createMagia(novaMagia, this.ficha);
    }

    public void registrarMagia(Magia magia) {
        try {
            int id = magia.getIdMagia();
            this.magias.put(id, magia);
        } catch (NullPointerException e) {
            System.out.println("Nao foi possivel registrar uma magia");
            System.out.println(e.getMessage());
        }
    }

    public void usarMagia (Magia magia){
        try {
            if (espacoDeMagia[magia.getNivel()]<=0) {
                System.out.println("Espaços de magia esgotados para nivel " + magia.getNivel());
                return;
            }

            if (magia instanceof MagiaCura magiaCura){
                magiaCura.usarMagia();
            } else if (magia instanceof MagiaDano magiaDano){
                magiaDano.usarMagia();
            } else {
                magia.usarMagia();
            }
            espacoDeMagia[magia.getNivel()]--;
            //TODO: mod no banco
        } catch (NullPointerException e) {
            System.out.println("Nao foi possivel registrar uma magia");
            System.out.println(e.getMessage());
        }
    }

    public void recuperarSlots() {
        System.out.println("Recuperando slots de magia");
        this.espacoDeMagia = espacosMagiaNivel[this.ficha.getNivel()];
        SQLMagiaUser.updateEspacoMagia(this);
    }

    public void recuperarSlotUnico(int nivel) {
        if(nivel < 0 || nivel > 9) {
            throw new NivelMagiaException();
        }

        System.out.println("Recuperando slots de magia");
        if (this.espacoDeMagia[nivel] < espacoDeMagia[this.ficha.getNivel()]) {
            this.espacoDeMagia[nivel]++;
        } else {
            System.out.println("Falha ao recuperar slots de magia");
        }
        SQLMagiaUser.updateEspacoMagia(this);
    }

    public void removerMagia(Magia magia) {
        try {
            int id = magia.getIdMagia();
            magias.remove(id);
            SQLMagia.deleteMagia(id);
        } catch (NullPointerException e) {
            System.out.println("Nao foi possivel remover magia");
            System.out.println(e.getMessage());
        }
    }

    public int getId() {
        return id;
    }

    public int getEspacoDeMagia(int nivel) {
        return this.espacoDeMagia[nivel];
    }

    public void setEspacoDeMagia(int nivel, int quantidade) {
        try {
            if(quantidade >= 0 && quantidade <= TabelaMagia.espacosMagiaNivel[this.ficha.getNivel()][nivel]){
                this.espacoDeMagia[nivel] = quantidade;
            } else {
                throw new NivelMagiaException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("nível ou quantidade inválidos para espacoDeMagia");
            System.out.println(e.getMessage());
        }
    }

    public Magia getMagia(int idMagia) {
        return this.magias.get(idMagia);
    }
}
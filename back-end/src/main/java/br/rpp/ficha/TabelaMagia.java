package br.rpp.ficha;

import br.rpp.auxiliar.enuns.TiposAcertosMagia;
import br.rpp.magias.Magia;
import br.rpp.magias.MagiaCura;
import br.rpp.magias.MagiaDano;
import br.rpp.sql.BD;
import br.rpp.sql.SQLMagia;
import br.rpp.sql.SQLMagiaUser;
import br.rpp.auxiliar.enuns.Tabelas;

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
    public Ficha ficha;

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
            default: System.out.println("Erro ao criar magia");
        }

        if(novaMagia != null){
            this.magias.put(novaMagia.getIdMagia(), novaMagia);
            SQLMagia.createMagia(novaMagia, this.ficha);
        }
    }

    public void registrarMagia(Magia magia) {
        int id = magia.getIdMagia();
        this.magias.put(id, magia);
    }

    public void usarMagia (int idMagia){
        Magia magia = magias.get(idMagia);

        if (magia == null){
            System.out.println("Magia não conhecida");
            return;
        }
        if (espacoDeMagia[magia.nivel]<=0) {
            System.out.println("Espaços de magia esgotados para nivel " + magia.nivel);
            return;
        }

        if (magia instanceof MagiaCura magiaCura){
            magiaCura.usarMagia();
        } else if (magia instanceof MagiaDano magiaDano){
            magiaDano.usarMagia();
        } else {
            magia.usarMagia();
        }
        espacoDeMagia[magia.nivel]--;
        //TODO: mod no banco
    }

    public void recuperarSlots() {
        System.out.println("Recuperando slots de magia");
        this.espacoDeMagia = espacosMagiaNivel[this.ficha.nivel];
        SQLMagiaUser.updateEspacoMagia(this);
    }

    public void recuperarSlotUnico(int nivel) {
        System.out.println("Recuperando slots de magia");
        if (this.espacoDeMagia[nivel] < espacoDeMagia[this.ficha.nivel]) {
            this.espacoDeMagia[nivel]++;
        } else {
            System.out.println("Falha ao recuperar slots de magia");
        }
        SQLMagiaUser.updateEspacoMagia(this);
    }

    public void deletarMagia(Magia magia) {
        int id = magia.getIdMagia();
        magias.remove(id);
        SQLMagia.deleteMagia(id);
    }

    public int getId() {
        return id;
    }

    public int getEspacoDeMagia(int nivel) {
        return this.espacoDeMagia[nivel];
    }

    public void setEspacoDeMagia(int nivel, int quantidade) {
        if(quantidade >= 0 && quantidade <= TabelaMagia.espacosMagiaNivel[this.ficha.nivel][nivel]){
            this.espacoDeMagia[nivel] = quantidade;
        } else {
            System.out.println("erro ao setar magia");
        }
    }

    public Magia getMagia(int idMagia) {
        return this.magias.get(idMagia);
    }
}
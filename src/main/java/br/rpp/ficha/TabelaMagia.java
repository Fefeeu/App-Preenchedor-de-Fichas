package br.rpp.ficha;

import br.rpp.magias.Magia;
import br.rpp.magias.MagiaCura;
import br.rpp.magias.MagiaDano;
import br.rpp.sql.SQLMagia;

import java.sql.SQLException;
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


    private final HashMap<Integer, Magia> magias = new HashMap<>();
                                //    Nv 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Níveis de magia)
    private int[] espacoDeMagia = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public Ficha ficha;

    public TabelaMagia(Ficha ficha) {
        this.ficha = ficha;
    }

    public void criarMagia(String tipo, int idFicha, int idUser){
        String tipoEfeito = "";
        String nome = "";
        String descricao = "";
        int nivel = 0;
        String tempoConjuracao = "";
        String duracao = "";
        String alcance = "";
        String area = "";
        String escola = "";
        String tipoAcerto = "";

        Magia novaMagia = null;

        switch (tipo) {
            case "efeito":{
                novaMagia = new Magia(tipoEfeito, nome, descricao, nivel, tempoConjuracao, duracao, alcance, area, escola, tipoAcerto);
            }
            case "dano":{
                int dadoDano = 6;
                int quantidadeDado = 1;

                novaMagia = new MagiaDano(tipoEfeito, nome, descricao, nivel, tempoConjuracao, duracao, alcance, area, escola, tipoAcerto, dadoDano, quantidadeDado);
            }
            case "cura":{
                int dadoCura = 6;
                int quantidadeDado = 1;
                novaMagia = new MagiaCura(tipoEfeito, nome, descricao, nivel, tempoConjuracao, duracao, alcance, area, escola, tipoAcerto, dadoCura, quantidadeDado);
            }
            default: System.out.println("Erro ao criar magia");
        }

        if(novaMagia != null){
            this.magias.put(novaMagia.getIdMagia(), novaMagia);
            SQLMagia.createMagia(novaMagia, idFicha, idUser);
        }
    }

    public void registrarMagia(Magia magia) {
        int id = magia.getIdMagia();
        magias.put(id, magia);
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
    }

    public void recuperarSlots() {
        System.out.println("Recuperando slots de magia");
        this.espacoDeMagia = espacosMagiaNivel[this.ficha.nivel];
    }

    public void recuperarSlotUnico(int nivel) {
        System.out.println("Recuperando slots de magia");
        if (this.espacoDeMagia[nivel] < espacoDeMagia[this.ficha.nivel]) {
            this.espacoDeMagia[nivel]++;
        } else {
            System.out.println("Falha ao recuperar slots de magia");
        }
    }
}

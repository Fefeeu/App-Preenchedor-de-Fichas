package br.rpp.ficha;

import br.rpp.magias.Magia;
import java.util.HashMap;

public class TabelaMagia {
    private HashMap<Integer, Magia> magias = new HashMap<>();
    private int [] espacoDeMagia = new int[9];

    public int registrarMagia(Magia magia) {
        int id = magias.size() + 1;
        magias.put(id, magia);
        return id;
    }

    public void usarMagia (int idMagia, int nivel){
        Magia magia = magias.get(idMagia);
        if (magia == null){
            System.out.println("Magia não conhecida"); //da para fazer por tratamento de erro
            return;
        }
        if (espacoDeMagia[nivel-1]<=0) {
            System.out.println("Espaços de magia esgotados para nivel " + nivel);
            return;
        }
        magia.usarMagia();
        espacoDeMagia[nivel-1]--;
    }

    public void recuperarSlots() { //averiguar slots por nivel
        System.out.println("Recuperando slots de magia");
        for (int i = 0; i < espacoDeMagia.length; i++) {
            espacoDeMagia[i] = i+1;
        }
    }

}

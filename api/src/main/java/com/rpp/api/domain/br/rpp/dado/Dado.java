package com.rpp.api.domain.br.rpp.dado;

import java.util.Random;

public abstract class Dado {

    public static int rollD20(String tipo){
        Random dice = new Random();
        switch(tipo){
            case "vantagem":{
                int valor1 = dice.nextInt(20) + 1;
                int valor2 = dice.nextInt(20) + 1;
                return Math.max(valor1, valor2);
            }

            case "normal":{
                return dice.nextInt(20) + 1;
            }

            case "desvantagem":{
                int valor1 = dice.nextInt(20) + 1;
                int valor2 = dice.nextInt(20) + 1;
                return Math.min(valor1, valor2);
            }

            default: {
                return 0;
            }
        }
    }

    public static int rollD(int lados){
        lados = Math.max(1, lados);

        Random dice = new Random();

        return dice.nextInt(lados) + 1;
    }

    public static int rollD(int lados, int quantidade){
        lados = Math.max(1, lados);
        quantidade = Math.max(1, quantidade);

        Random dice = new Random();

        int total = 0;
        for (int i = 0; i < quantidade; i++){
            total += dice.nextInt(lados) + 1;
        }

        return total;
    }
}

package br.rpp.ficha;

import br.rpp.inventario.Inventario;
import br.rpp.inventario.item.Equipavel;
import br.rpp.inventario.item.EquipavelMagico;
import br.rpp.sql.SQLFicha;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Ficha {
    private static final int[] proeficiencia = {0, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6};

    private final int idFicha;
    private final int idUser;
    private boolean estado;
    public Caracteristica caracteristicas;
    public HashMap<String, Integer> atributos;
    public HashMap<String, Boolean> pericias;
    public int nivel;
    private int iniciativa;
    private Equipavel vestido;
    private int classeArmadura;
    public float deslocamento;
    public int pontosVidaBase;
    private int pontosVidaTotal;
    public int vidaTemporaria;
    public int dadoDeVida;
    public Inventario inventario;
    public Descricao descricao;
    public TabelaMagia magias;
    public boolean inspiracao;


    public Ficha(int idFicha, int idUser, boolean estado, int nivel, String nomePersonagem, String idClasse, String antecedente, String userName,
                 String idRaca, String tendencia, int xp, int idade, float altura, float peso, String olho,
                 String pele, String cabelo, String idiomas, String proeficiencia,
                 Integer forca, Integer destreza, Integer constituicao, Integer inteligencia, Integer sabedoria, Integer carisma,
                 float deslocamento, int pontosVidaBase, int vidaTemporaria, int dadoDeVida,
                 String historia, String aparencia, String personalidade, String ideal, String ligacao, String defeitos){
        this.estado = estado;
        this.nivel = nivel;
        this.caracteristicas = new Caracteristica(nomePersonagem, idClasse, antecedente, userName, idRaca, tendencia, xp, idade, altura, peso, olho, pele, cabelo, idiomas, proeficiencia);
        this.atributos = new HashMap<>();
        this.atributos.put("forca", forca);
        this.atributos.put("destreza", destreza);
        this.atributos.put("constituicao", constituicao);
        this.atributos.put("inteligencia", inteligencia);
        this.atributos.put("sabedoria", sabedoria);
        this.atributos.put("carisma", carisma);
        this.deslocamento = deslocamento;
        this.pontosVidaBase = pontosVidaBase;
        this.vidaTemporaria = vidaTemporaria;
        this.dadoDeVida = dadoDeVida;
        this.descricao = new Descricao(historia, aparencia, personalidade, ideal, ligacao, defeitos);
        this.pericias = new HashMap<>();
        this.setPericias();

        this.iniciativa = this.converteAtributo("destreza");
        this.classeArmadura = 10 + this.converteAtributo("destreza");

        this.idUser = idUser;
        this.idFicha = idFicha;

        this.magias = new TabelaMagia(this);
        this.inventario = new Inventario(this);

    }

    public int converteAtributo(String atributo){
        return this.atributos.get(atributo)/2 - 5;
    }

    public void vestirItem(Equipavel vestimenta){
        if (vestimenta instanceof EquipavelMagico vestimentaMagica){
            this.classeArmadura = 10 + this.converteAtributo("classeArmadura") + vestimentaMagica.bonusCA + vestimentaMagica.bonus;
        } else {
            this.classeArmadura = 10 + this.converteAtributo("classeArmadura") + vestimenta.bonusCA;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Informações básicas
        sb.append("=== Ficha do Personagem ===\n");
        sb.append("Nome: ").append(caracteristicas.nomePersonagem).append("\n");
        sb.append("Classe: ").append(caracteristicas.idClasse).append(" (Nível ").append(nivel).append(")\n");
        sb.append("Raça: ").append(caracteristicas.idClasse).append("\n");
        sb.append("Antecedente: ").append(caracteristicas.antecedente).append("\n");
        sb.append("Tendência: ").append(caracteristicas.tendencia).append("\n");
        sb.append("XP: ").append(caracteristicas.xp).append("\n");
        sb.append("Inspiração: ").append(inspiracao ? "Sim" : "Não").append("\n\n");

        // Atributos
        sb.append("=== Atributos ===\n");
        sb.append(String.format("Força: %2d (%s)\n", atributos.get("forca"), pericias.get("forca") ? "Prof" : ""));
        sb.append(String.format("Destreza: %2d (%s)\n", atributos.get("destreza"), pericias.get("destreza") ? "Prof" : ""));
        sb.append(String.format("Constituição: %2d (%s)\n", atributos.get("constituicao"), pericias.get("constituicao") ? "Prof" : ""));
        sb.append(String.format("Inteligência: %2d (%s)\n", atributos.get("inteligencia"), pericias.get("inteligencia") ? "Prof" : ""));
        sb.append(String.format("Sabedoria: %2d (%s)\n", atributos.get("sabedoria"), pericias.get("sabedoria") ? "Prof" : ""));
        sb.append(String.format("Carisma: %2d (%s)\n", atributos.get("carisma"), pericias.get("carisma") ? "Prof" : ""));
        sb.append("\n");

        // Perícias proficientes
        sb.append("=== Perícias Proficientes ===\n");
        pericias.entrySet().stream()
                .filter(entry -> entry.getValue() && !entry.getKey().matches("forca|destreza|constituicao|inteligencia|sabedoria|carisma"))
                .forEach(entry -> sb.append("- ").append(entry.getKey()).append("\n"));
        sb.append("\n");

        // Características físicas
        sb.append("=== Características Físicas ===\n");
        sb.append("Idade: ").append(caracteristicas.idade).append(" anos\n");
        sb.append("Altura: ").append(caracteristicas.altura).append("m\n");
        sb.append("Peso: ").append(caracteristicas.peso).append("kg\n");
        sb.append("Olhos: ").append(caracteristicas.olho).append("\n");
        sb.append("Pele: ").append(caracteristicas.pele).append("\n");
        sb.append("Cabelo: ").append(caracteristicas.cabelo).append("\n\n");

        // Combate
        sb.append("=== Combate ===\n");
        sb.append("Pontos de Vida: ").append(pontosVidaBase).append("/").append(pontosVidaTotal).append("\n");
        sb.append("Vida Temporária: ").append(vidaTemporaria).append("\n");
        sb.append("Classe de Armadura: ").append(classeArmadura).append("\n");
        sb.append("Iniciativa: ").append(iniciativa).append("\n");
        sb.append("Deslocamento: ").append(deslocamento).append("m\n\n");

        // Descrição
        sb.append("=== História ===\n").append(descricao.historia).append("\n\n");
        sb.append("=== Aparência ===\n").append(descricao.aparencia).append("\n\n");
        sb.append("=== Personalidade ===\n").append(descricao.personalidade).append("\n\n");
        sb.append("=== Ideal ===\n").append(descricao.ideal).append("\n\n");
        sb.append("=== Ligações ===\n").append(descricao.ligacao).append("\n\n");
        sb.append("=== Defeitos ===\n").append(descricao.defeitos).append("\n");

        return sb.toString();
    }

    private void setPericias() {
        // Atributos básicos
        this.pericias.put("forca", false);
        this.pericias.put("destreza", false);
        this.pericias.put("constituicao", false);
        this.pericias.put("inteligencia", false);
        this.pericias.put("sabedoria", false);
        this.pericias.put("carisma", false);

        // Perícias
        this.pericias.put("acrobacia", false);
        this.pericias.put("arcanismo", false);
        this.pericias.put("atletismo", false);
        this.pericias.put("atuacao", false);
        this.pericias.put("blefar", false);
        this.pericias.put("furtividade", false);
        this.pericias.put("historia", false);
        this.pericias.put("intimidacao", false);
        this.pericias.put("intuicao", false);
        this.pericias.put("investigacao", false);
        this.pericias.put("lidar_com_animais", false);
        this.pericias.put("medicina", false);
        this.pericias.put("natureza", false);
        this.pericias.put("percepcao", false);
        this.pericias.put("persuasao", false);
        this.pericias.put("prestigitacao", false);
        this.pericias.put("religiao", false);
        this.pericias.put("sobrevivencia", false);
    }

    public void proficienciaPericia(String pericia){
        if (this.pericias.get(pericia)){
            this.pericias.put(pericia, false);
        } else {
            this.pericias.put(pericia, true);
        }
    }

    public int getIdUser(){
        return idUser;
    }

    public int getIdFicha(){
        return idFicha;
    }

    public boolean getEstado(){
        return estado;
    }

    public static int getProficiencia(int nivel){
        if (nivel >= 1 && nivel <= 20){
            return proeficiencia[nivel];
        }
        return 2;
    }
}
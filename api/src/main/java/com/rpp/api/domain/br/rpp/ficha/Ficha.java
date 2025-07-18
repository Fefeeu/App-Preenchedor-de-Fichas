package com.rpp.api.domain.br.rpp.ficha;

import com.rpp.api.domain.br.rpp.auxiliar.exeptions.DadoInvalidoException;
import com.rpp.api.domain.br.rpp.auxiliar.exeptions.NivelException;
import com.rpp.api.domain.br.rpp.auxiliar.exeptions.ValorMenorQueUmException;
import com.rpp.api.domain.br.rpp.auxiliar.exeptions.ValorNegativoException;
import com.rpp.api.domain.br.rpp.dado.Dado;
import com.rpp.api.domain.br.rpp.inventario.Inventario;
import com.rpp.api.domain.br.rpp.inventario.item.Equipavel;
import com.rpp.api.domain.br.rpp.inventario.item.EquipavelMagico;
import com.rpp.api.domain.br.rpp.inventario.item.Item;
import com.rpp.api.domain.br.rpp.sql.BD;
import com.rpp.api.domain.br.rpp.sql.SQLInventario;
import com.rpp.api.domain.br.rpp.sql.SQLItem;
import com.rpp.api.domain.br.rpp.auxiliar.enuns.Tabelas;
import com.rpp.api.domain.br.rpp.sql.SQLMagiaUser;

import java.util.HashMap;

public class Ficha {
    private static final int[] proficiencia = {0, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6};

    private final int idFicha;
    private final int idUser;
    private boolean estado;
    private Caracteristica caracteristicas;
    private HashMap<String, Integer> atributos;
    private HashMap<String, Boolean> pericias;
    private int nivel;
    private int iniciativa;
    private Equipavel vestido = null;
    private int classeArmadura;
    private float deslocamento;
    private int pontosVidaBase;
    private int pontosVidaTotal;
    private int vidaTemporaria;
    private int dadoDeVida;
    private Inventario inventario;
    private Descricao descricao;
    private TabelaMagia magias;
    private boolean inspiracao;


    public Ficha(int idFicha, int idUser, boolean estado, int nivel, String nomePersonagem, String idClasse, String antecedente, String userName,
                 String idRaca, String tendencia, int xp, int idade, float altura, float peso, String olho,
                 String pele, String cabelo, String idiomas, String proficiencia,
                 Integer forca, Integer destreza, Integer constituicao, Integer inteligencia, Integer sabedoria, Integer carisma,
                 float deslocamento, int pontosVidaBase, int vidaTemporaria, int dadoDeVida,
                 String historia, String aparencia, String personalidade, String ideal, String ligacao, String defeitos, boolean read){
        this.estado = estado;
        this.nivel = nivel;
        this.caracteristicas = new Caracteristica(nomePersonagem, idClasse, antecedente, userName, idRaca, tendencia, xp, idade, altura, peso, olho, pele, cabelo, idiomas, proficiencia);
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

        this.iniciativa = this.converteAtributo("destreza");
        this.classeArmadura = 10 + this.converteAtributo("destreza");
        this.pontosVidaTotal = this.pontosVidaBase + this.converteAtributo("constituicao")*this.nivel;

        this.idUser = idUser;
        this.idFicha = idFicha;

        this.pericias = new HashMap<>();
        this.setPericias();
        if(!read){
            this.magias = new TabelaMagia(BD.gerarId(Tabelas.MAGIAUSER.toString()), this);
            this.inventario = new Inventario(BD.gerarId(Tabelas.INVENTARIO.toString()));
        }
    }


    public int converteAtributo(String atributo){
        return this.atributos.get(atributo)/2 - 5;
    }

    public void vestirItem(int id){
        Item item = SQLItem.readItem(id);
        Equipavel vestimenta;
        if(item instanceof Equipavel){
            vestimenta = (Equipavel)item;
            if (vestimenta instanceof EquipavelMagico vestimentaMagica){
                this.classeArmadura = 10 + this.converteAtributo("classeArmadura") + vestimentaMagica.getBonusCA() + vestimentaMagica.getBonus();
            } else{
                this.classeArmadura = 10 + this.converteAtributo("classeArmadura") + vestimenta.getBonusCA();
            }
        }
    }

    public void criarInventario(int id){
        this.inventario = SQLInventario.readInventario(id);
    }

    public void criarMagias(int id){
        this.magias = SQLMagiaUser.readMagiaUser(id, this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Informações básicas
        sb.append("=== Ficha do Personagem ===\n");
        sb.append("Nome: ").append(caracteristicas.getNomePersonagem()).append("\n");
        sb.append("Classe: ").append(caracteristicas.getIdClasse()).append(" (Nível ").append(nivel).append(")\n");
        sb.append("Raça: ").append(caracteristicas.getIdRaca()).append("\n");
        sb.append("Antecedente: ").append(caracteristicas.getAntecedente()).append("\n");
        sb.append("Tendência: ").append(caracteristicas.getTendencia()).append("\n");
        sb.append("XP: ").append(caracteristicas.getXp()).append("\n");
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
        sb.append("Idade: ").append(caracteristicas.getIdade()).append(" anos\n");
        sb.append("Altura: ").append(caracteristicas.getAltura()).append("m\n");
        sb.append("Peso: ").append(caracteristicas.getPeso()).append("kg\n");
        sb.append("Olhos: ").append(caracteristicas.getOlho()).append("\n");
        sb.append("Pele: ").append(caracteristicas.getPele()).append("\n");
        sb.append("Cabelo: ").append(caracteristicas.getCabelo()).append("\n\n");

        // Combate
        sb.append("=== Combate ===\n");
        sb.append("Pontos de Vida: ").append(pontosVidaBase).append("/").append(pontosVidaTotal).append("\n");
        sb.append("Vida Temporária: ").append(vidaTemporaria).append("\n");
        sb.append("Classe de Armadura: ").append(classeArmadura).append("\n");
        sb.append("Iniciativa: ").append(iniciativa).append("\n");
        sb.append("Deslocamento: ").append(deslocamento).append("m\n\n");

        // Descrição
        sb.append("=== História ===\n").append(descricao.getHistoria()).append("\n\n");
        sb.append("=== Aparência ===\n").append(descricao.getAparencia()).append("\n\n");
        sb.append("=== Personalidade ===\n").append(descricao.getPersonalidade()).append("\n\n");
        sb.append("=== Ideal ===\n").append(descricao.getIdeal()).append("\n\n");
        sb.append("=== Ligações ===\n").append(descricao.getLigacao()).append("\n\n");
        sb.append("=== Defeitos ===\n").append(descricao.getDefeitos()).append("\n");

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

    public void setEstado(boolean estado) {
        try {
            this.estado = estado;
        } catch (NullPointerException e) {
            System.out.println("Valor do estado não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public void setCaracteristicas(Caracteristica caracteristicas) {
        try {
            this.caracteristicas = caracteristicas;
        } catch (NullPointerException e) {
            System.out.println("Valor das características não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public void setAtributos(HashMap<String, Integer> atributos) {
        try {
            for(Integer atributo : atributos.values()) {
                if (atributo < 1) {
                    throw new ValorMenorQueUmException();
                }
            }

            this.atributos = atributos;
        } catch (NullPointerException e) {
            System.out.println("Valor dos atributos não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public void setPericias(HashMap<String, Boolean> pericias) {
        try {
            this.pericias = pericias;
        } catch (NullPointerException e) {
            System.out.println("Valor das perícias não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public void setNivel(int nivel) {
        if (nivel < 1 || nivel > 20) {
            throw new NivelException();
        }

        try {
            this.nivel = nivel;
        } catch (NullPointerException e) {
            System.out.println("Valor do nível não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public void setIniciativa(int iniciativa) {
        try {
            this.iniciativa = iniciativa;
        } catch (NullPointerException e) {
            System.out.println("Valor da iniciativa não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public void setVestido(Equipavel vestido) {
        try {
            this.vestido = vestido;
        } catch (NullPointerException e) {
            System.out.println("Valor do vestido não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public void setClasseArmadura(int classeArmadura) {
        try {
            this.classeArmadura = classeArmadura;
        } catch (NullPointerException e) {
            System.out.println("Valor da classe de armadura não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public void setDeslocamento(float deslocamento) {
        if(deslocamento < 0){
            throw new ValorNegativoException();
        }

        try {
            this.deslocamento = deslocamento;
        } catch (NullPointerException e) {
            System.out.println("Valor do deslocamento não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public void setPontosVidaBase(int pontosVidaBase) {
        if(pontosVidaBase < 0){
            throw new ValorNegativoException();
        }

        try {
            this.pontosVidaBase = pontosVidaBase;
        } catch (NullPointerException e) {
            System.out.println("Valor dos pontos de vida base não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public void setPontosVidaTotal(int pontosVidaTotal) {
        if(pontosVidaTotal < 0){
            throw new ValorNegativoException();
        }

        try {
            this.pontosVidaTotal = pontosVidaTotal;
        } catch (NullPointerException e) {
            System.out.println("Valor dos pontos de vida total não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public void setVidaTemporaria(int vidaTemporaria) {
        if(vidaTemporaria < 0){
            throw new ValorNegativoException();
        }

        try {
            this.vidaTemporaria = vidaTemporaria;
        } catch (NullPointerException e) {
            System.out.println("Valor da vida temporária não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public void setDadoDeVida(int dadoDeVida) {
        if(!Dado.verificaLados(dadoDeVida)){
            throw new DadoInvalidoException();
        }

        try {
            this.dadoDeVida = dadoDeVida;
        } catch (NullPointerException e) {
            System.out.println("Valor do dado de vida não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public void setInventario(Inventario inventario) {
        try {
            this.inventario = inventario;
        } catch (NullPointerException e) {
            System.out.println("Valor do inventário não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public void setDescricao(Descricao descricao) {
        try {
            this.descricao = descricao;
        } catch (NullPointerException e) {
            System.out.println("Valor da descrição não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public void setMagias(TabelaMagia magias) {
        try {
            this.magias = magias;
        } catch (NullPointerException e) {
            System.out.println("Valor das magias não pode ser nulo");
            System.out.println(e.getMessage());
        }
    }

    public void setInspiracao(boolean inspiracao) {
        try {
            this.inspiracao = inspiracao;
        } catch (NullPointerException e) {
            System.out.println("Valor da inspiração não pode ser nulo");
            System.out.println(e.getMessage());
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
            return proficiencia[nivel];
        }
        return 2;
    }

    public Caracteristica getCaracteristicas() {
        return caracteristicas;
    }


    public HashMap<String, Integer> getAtributos() {
        return atributos;
    }


    public HashMap<String, Boolean> getPericias() {
        return pericias;
    }

    public void setPericia(String pericia, boolean proficiencia){
        this.pericias.replace(pericia, proficiencia);
    }

    public int getNivel() {
        return nivel;
    }

    public int getIniciativa() {
        return iniciativa;
    }

    public Equipavel getVestido() {
        return vestido;
    }

    public int getClasseArmadura() {
        return classeArmadura;
    }

    public float getDeslocamento() {
        return deslocamento;
    }

    public int getPontosVidaBase() {
        return pontosVidaBase;
    }

    public int getPontosVidaTotal() {
        return pontosVidaTotal;
    }

    public int getVidaTemporaria() {
        return vidaTemporaria;
    }

    public int getDadoDeVida() {
        return dadoDeVida;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public Descricao getDescricao() {
        return descricao;
    }

    public TabelaMagia getMagias() {
        return magias;
    }

    public boolean isInspiracao() {
        return inspiracao;
    }

}
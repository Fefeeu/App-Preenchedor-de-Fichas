package br.rpp.ficha;

import br.rpp.auxiliar.Dado;
import br.rpp.inventario.Inventario;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Ficha {
    static int[] proeficiencia = {0, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6};

    private boolean estado;
    public Caracteristica caracteristicas;
    public HashMap<String, Integer> atributos;
    public HashMap<String, Boolean> pericias;
    public int nivel;
    private int iniciativa;
    private int classeArmadura;
    public float deslocamento;
    public int pontosVidaBase;
    public int pontosVidaTotal;
    public int vidaTemporaria;
    public int dadoDeVida;
    public Inventario inventario;
    public Descricao descricao;
    public TabelaMagia magias;
    public boolean inspiracao;


    public Ficha(boolean estado, int nivel, String nomePersonagem, String idClasse, String antecedente, String userName,
                 String idRaca, String tendencia, int xp, int idade, float altura, float peso, String olho,
                 String pele, String cabelo, ArrayList<String> idiomas, ArrayList<String> proeficiencia,
                 Integer forca, Integer destreza, Integer constituicao, Integer inteligencia, Integer sabedoria, Integer carisma,
                 float deslocamento, int pontosVidaBase, int vidaTemporaria, int dadoDeVida,
                 String historia, String aparencia, String personalidade, String ideal, String ligacao, String defeitos) {
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
        this.setPericias(this);

    }

    public static int create(Connection connection, Ficha ficha) throws SQLException {
        String sql = "INSERT INTO ficha (" +
                "  idFicha, user_idUser, vivo, nivel, deslocamento, dadoDeVida, pontosDeVidaBase, pontosDeVidaTemporario, inspiracao," +
                "  nomePersonagem, classe_idClasse, Raca_idRaca, antecedente, tendencia, xp, idade, altura, peso," +
                "  olho, pele, cabelo," +
                "  forca, destreza, constituicao, inteligencia, sabedoria, carisma," +
                "  p_forca, p_destreza, p_constituicao, p_inteligencia, p_sabedoria, p_carisma," +
                "  p_acrobacia, p_arcanismo, p_atletismo, p_atuacao, p_blefar, p_furtividade," +
                "  p_historia, p_intimidacao, p_intuicao, p_investigacao, p_lidarComAnimais, p_medicina," +
                "  p_natureza, p_percepcao, p_persuasao, p_prestigitacao, p_religiao, p_sobrevivencia," +
                "  historia, aparencia, personalidade, ideal, ligacao, defeitos" +
                ") VALUES (" +
                "  ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                "  ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                "  ?, ?, ?," +
                "  ?, ?, ?, ?, ?, ?," +
                "  ?, ?, ?, ?, ?, ?," +
                "  ?, ?, ?, ?, ?, ?," +
                "  ?, ?, ?, ?, ?, ?," +
                "  ?, ?, ?, ?, ?, ?," +
                "  ?, ?, ?, ?, ?, ?" +
                ")";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;

            // Dados básicos (8 parâmetros)
            stmt.setInt(index++, 0); // idFicha (auto-increment)
            stmt.setInt(index++, 0); // user_idUser (deve ser fornecido)
            stmt.setBoolean(index++, ficha.estado);
            stmt.setInt(index++, ficha.nivel);
            stmt.setFloat(index++, ficha.deslocamento);
            stmt.setInt(index++, ficha.dadoDeVida);
            stmt.setInt(index++, ficha.pontosVidaBase);
            stmt.setInt(index++, ficha.vidaTemporaria);
            stmt.setBoolean(index++, ficha.inspiracao);

            // Info personagem (9 parâmetros)
            stmt.setString(index++, ficha.caracteristicas.nomePersonagem);
            stmt.setString(index++, ficha.caracteristicas.idClasse);
            stmt.setString(index++, ficha.caracteristicas.idRaca.toString());
            stmt.setString(index++, ficha.caracteristicas.antecedente);
            stmt.setString(index++, ficha.caracteristicas.tendencia);
            stmt.setInt(index++, ficha.caracteristicas.xp);
            stmt.setInt(index++, ficha.caracteristicas.idade);
            stmt.setFloat(index++, ficha.caracteristicas.altura);
            stmt.setFloat(index++, ficha.caracteristicas.peso);

            // Aparência (3 parâmetros)
            stmt.setString(index++, ficha.caracteristicas.olho);
            stmt.setString(index++, ficha.caracteristicas.pele);
            stmt.setString(index++, ficha.caracteristicas.cabelo);

            // Atributos (6 parâmetros)
            stmt.setInt(index++, ficha.atributos.get("forca"));
            stmt.setInt(index++, ficha.atributos.get("destreza"));
            stmt.setInt(index++, ficha.atributos.get("constituicao"));
            stmt.setInt(index++, ficha.atributos.get("inteligencia"));
            stmt.setInt(index++, ficha.atributos.get("sabedoria"));
            stmt.setInt(index++, ficha.atributos.get("carisma"));

            // Proficiências em atributos (6 parâmetros)
            stmt.setBoolean(index++, ficha.pericias.get("forca"));
            stmt.setBoolean(index++, ficha.pericias.get("destreza"));
            stmt.setBoolean(index++, ficha.pericias.get("constituicao"));
            stmt.setBoolean(index++, ficha.pericias.get("inteligencia"));
            stmt.setBoolean(index++, ficha.pericias.get("sabedoria"));
            stmt.setBoolean(index++, ficha.pericias.get("carisma"));

            // Perícias (18 parâmetros)
            stmt.setBoolean(index++, ficha.pericias.get("acrobacia"));
            stmt.setBoolean(index++, ficha.pericias.get("arcanismo"));
            stmt.setBoolean(index++, ficha.pericias.get("atletismo"));
            stmt.setBoolean(index++, ficha.pericias.get("atuacao"));
            stmt.setBoolean(index++, ficha.pericias.get("blefar"));
            stmt.setBoolean(index++, ficha.pericias.get("furtividade"));
            stmt.setBoolean(index++, ficha.pericias.get("historia"));
            stmt.setBoolean(index++, ficha.pericias.get("intimidacao"));
            stmt.setBoolean(index++, ficha.pericias.get("intuicao"));
            stmt.setBoolean(index++, ficha.pericias.get("investigacao"));
            stmt.setBoolean(index++, ficha.pericias.get("lidar_com_animais"));
            stmt.setBoolean(index++, ficha.pericias.get("medicina"));
            stmt.setBoolean(index++, ficha.pericias.get("natureza"));
            stmt.setBoolean(index++, ficha.pericias.get("percepcao"));
            stmt.setBoolean(index++, ficha.pericias.get("persuasao"));
            stmt.setBoolean(index++, ficha.pericias.get("prestigitacao"));
            stmt.setBoolean(index++, ficha.pericias.get("religiao"));
            stmt.setBoolean(index++, ficha.pericias.get("sobrevivencia"));

            // Descrições (6 parâmetros - agora incluindo personalidade)
            stmt.setString(index++, ficha.descricao.historia);
            stmt.setString(index++, ficha.descricao.aparencia);
            stmt.setString(index++, ficha.descricao.personalidade);
            stmt.setString(index++, ficha.descricao.ideal);
            stmt.setString(index++, ficha.descricao.ligacao);
            stmt.setString(index++, ficha.descricao.defeitos);

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }
        return -1;
    }

    public static Ficha read(Connection connection, int idFicha) throws SQLException {
        String sql = "SELECT * FROM ficha WHERE idFicha = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idFicha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Criar a ficha com todos os parâmetros do construtor
                    Ficha ficha = new Ficha(
                        rs.getBoolean("vivo"),
                        rs.getInt("nivel"),
                        rs.getString("nomePersonagem"),
                        rs.getString("classe_idClasse"),  // metodo busca classe
                        rs.getString("antecedente"),
                        "", // userName - ajustar conforme necessário
                        rs.getString("Raca_idRaca"),    // metodo busca raca
                        rs.getString("tendencia"),
                        rs.getInt("xp"),
                        rs.getInt("idade"),
                        rs.getFloat("altura"),
                        rs.getFloat("peso"),
                        rs.getString("olho"),
                        rs.getString("pele"),
                        rs.getString("cabelo"),
                        new ArrayList<>(), // idiomas
                        new ArrayList<>(), // proeficiencia
                        rs.getInt("forca"),
                        rs.getInt("destreza"),
                        rs.getInt("constituicao"),
                        rs.getInt("inteligencia"),
                        rs.getInt("sabedoria"),
                        rs.getInt("carisma"),
                        rs.getFloat("deslocamento"),
                        rs.getInt("pontosDeVidaBase"),
                        rs.getInt("pontosDeVidaTemporario"),
                        rs.getInt("dadoDeVida"),
                        rs.getString("historia"),
                        rs.getString("aparencia"),
                        rs.getString("personalidade"),
                        rs.getString("ideal"),
                        rs.getString("ligacao"),
                        rs.getString("defeitos")
                    );

                    // Configurar todas as perícias

                    ficha.pericias.put("forca", rs.getBoolean("p_forca"));
                    ficha.pericias.put("destreza", rs.getBoolean("p_destreza"));
                    ficha.pericias.put("constituicao", rs.getBoolean("p_constituicao"));
                    ficha.pericias.put("inteligencia", rs.getBoolean("p_inteligencia"));
                    ficha.pericias.put("sabedoria", rs.getBoolean("p_sabedoria"));
                    ficha.pericias.put("carisma", rs.getBoolean("p_carisma"));
                    ficha.pericias.put("acrobacia", rs.getBoolean("p_acrobacia"));
                    ficha.pericias.put("arcanismo", rs.getBoolean("p_arcanismo"));
                    ficha.pericias.put("atletismo", rs.getBoolean("p_atletismo"));
                    ficha.pericias.put("atuacao", rs.getBoolean("p_atuacao"));
                    ficha.pericias.put("blefar", rs.getBoolean("p_blefar"));
                    ficha.pericias.put("furtividade", rs.getBoolean("p_furtividade"));
                    ficha.pericias.put("historia", rs.getBoolean("p_historia"));
                    ficha.pericias.put("intimidacao", rs.getBoolean("p_intimidacao"));
                    ficha.pericias.put("intuicao", rs.getBoolean("p_intuicao"));
                    ficha.pericias.put("investigacao", rs.getBoolean("p_investigacao"));
                    ficha.pericias.put("lidar_com_animais", rs.getBoolean("p_lidarComAnimais"));
                    ficha.pericias.put("medicina", rs.getBoolean("p_medicina"));
                    ficha.pericias.put("natureza", rs.getBoolean("p_natureza"));
                    ficha.pericias.put("percepcao", rs.getBoolean("p_percepcao"));
                    ficha.pericias.put("persuasao", rs.getBoolean("p_persuasao"));
                    ficha.pericias.put("prestigitacao", rs.getBoolean("p_prestigitacao"));
                    ficha.pericias.put("religiao", rs.getBoolean("p_religiao"));
                    ficha.pericias.put("sobrevivencia", rs.getBoolean("p_sobrevivencia"));

                    return ficha;
                }
            }
        }
        return null; // Retorna null se a ficha não for encontrada
    }

    public static void delete(Connection connection, int id) throws SQLException {
        String sql = "DELETE FROM ficha WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id); // Define o parâmetro ID na query
            stmt.executeUpdate(); // Executa a exclusão
        }
        // O PreparedStatement é automaticamente fechado ao sair do bloco try
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

    private void setPericias(Ficha ficha) {
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

}

package br.rpp.sql;

import br.rpp.auxiliar.enuns.Tabelas;
import br.rpp.ficha.Ficha;

import java.sql.*;
import java.util.Objects;

public abstract class SQLFicha {
    public static void createFicha(Ficha ficha){
        SQLFicha.saveFicha(ficha, "criar");
    }

    public static void updateFicha(Ficha ficha){
        SQLFicha.saveFicha(ficha, "atualizar");
    }

    private static void saveFicha(Ficha ficha, String metodo){
        Connection connection = BD.getConnection();
        String sql = null;
        switch (metodo) {
            case("criar"): {
                sql = "INSERT INTO " + Tabelas.FICHA + " (" +
                    "  idFicha, user_idUser, vivo, nivel, deslocamento, dadoDeVida, pontosDeVidaBase, pontosDeVidaTemporario, inspiracao," +
                    "  nomePersonagem, classe_idClasse, raca_idRaca, antecedente, tendencia, xp, idade, altura, peso," +
                    "  olho, pele, cabelo," +
                    "  forca, destreza, constituicao, inteligencia, sabedoria, carisma," +
                    "  p_forca, p_destreza, p_constituicao, p_inteligencia, p_sabedoria, p_carisma," +
                    "  p_acrobacia, p_arcanismo, p_atletismo, p_atuacao, p_blefar, p_furtividade," +
                    "  p_historia, p_intimidacao, p_intuicao, p_investigacao, p_lidarComAnimais, p_medicina," +
                    "  p_natureza, p_percepcao, p_persuasao, p_prestigitacao, p_religiao, p_sobrevivencia," +
                    "  historia, aparencia, personalidade, ideal, ligacao, defeitos, proficiencias, idiomas," +
                    "  inventario_idInventario, magiaUser_idMagiaUser" +
                    ") VALUES (" +
                    "  ?, ?, ?, ?, ?, ?, ?, ?, ?," +    // 9
                    "  ?, ?, ?, ?, ?, ?, ?, ?, ?," +    // 18
                    "  ?, ?, ?," +                      // 21
                    "  ?, ?, ?, ?, ?, ?," +             // 27
                    "  ?, ?, ?, ?, ?, ?," +             // 33
                    "  ?, ?, ?, ?, ?, ?," +             // 39
                    "  ?, ?, ?, ?, ?, ?," +             // 45
                    "  ?, ?, ?, ?, ?, ?," +             // 51
                    "  ?, ?, ?, ?, ?, ?, ?, ?," +       // 59
                    "  ?, ?" +                          // 61
                    ")";
                break;
            }

            case ("atualizar"): {
                sql = "UPDATE " + Tabelas.FICHA + " SET " +
                    "user_idUser = ?, vivo = ?, nivel = ?, deslocamento = ?, dadoDeVida = ?, " +
                    "pontosDeVidaBase = ?, pontosDeVidaTemporario = ?, inspiracao = ?, " +
                    "nomePersonagem = ?, classe_idClasse = ?, raca_idRaca = ?, antecedente = ?, " +
                    "tendencia = ?, xp = ?, idade = ?, altura = ?, peso = ?, olho = ?, pele = ?, " +
                    "cabelo = ?, forca = ?, destreza = ?, constituicao = ?, inteligencia = ?, " +
                    "sabedoria = ?, carisma = ?, p_forca = ?, p_destreza = ?, p_constituicao = ?, " +
                    "p_inteligencia = ?, p_sabedoria = ?, p_carisma = ?, p_acrobacia = ?, " +
                    "p_arcanismo = ?, p_atletismo = ?, p_atuacao = ?, p_blefar = ?, p_furtividade = ?, " +
                    "p_historia = ?, p_intimidacao = ?, p_intuicao = ?, p_investigacao = ?, " +
                    "p_lidarComAnimais = ?, p_medicina = ?, p_natureza = ?, p_percepcao = ?, " +
                    "p_persuasao = ?, p_prestigitacao = ?, p_religiao = ?, p_sobrevivencia = ?, " +
                    "historia = ?, aparencia = ?, personalidade = ?, ideal = ?, ligacao = ?, " +
                    "defeitos = ?, proficiencias = ?, idiomas = ?, inventario_idInventario = ?, " +
                    "magiaUser_idMagiaUser = ?" +
                    "WHERE idFicha = ?";  // Condição para identificar qual ficha atualizar
                break;
            }
            default: System.out.println("metodo invalido para salvar ficha" + metodo);
        }

        if (sql != null) {
            try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                int index = 1;

                // Dados básicos (8 parâmetros)
                if(metodo.equals("criar")){
                    stmt.setInt(index++, ficha.getIdFicha()); // idFicha (auto-increment)
                }
                stmt.setInt(index++, ficha.getIdUser()); // user_idUser (deve ser fornecido)
                stmt.setBoolean(index++, ficha.getEstado());
                stmt.setInt(index++, ficha.nivel);
                stmt.setFloat(index++, ficha.deslocamento);
                stmt.setInt(index++, ficha.dadoDeVida);
                stmt.setInt(index++, ficha.pontosVidaBase);
                stmt.setInt(index++, ficha.vidaTemporaria);
                stmt.setBoolean(index++, ficha.inspiracao);

                // Info personagem (7 parâmetros)
                stmt.setString(index++, ficha.caracteristicas.nomePersonagem);
                stmt.setString(index++, ficha.caracteristicas.idClasse);
                stmt.setString(index++, ficha.caracteristicas.idRaca);
                stmt.setString(index++, ficha.caracteristicas.antecedente);
                stmt.setString(index++, ficha.caracteristicas.tendencia);
                stmt.setInt(index++, ficha.caracteristicas.xp);
                stmt.setInt(index++, ficha.caracteristicas.idade);

                // Aparência (5 parâmetros)
                stmt.setFloat(index++, ficha.caracteristicas.altura);
                stmt.setFloat(index++, ficha.caracteristicas.peso);
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

                // proficiencias (2 parâmetros)
                stmt.setString(index++, ficha.caracteristicas.idiomas);
                stmt.setString(index++, ficha.caracteristicas.proeficiencias);

                // inventario (1 parâmetro)
                stmt.setInt(index++, ficha.inventario.getId());

                // tabelaMagia ()
                stmt.setInt(index++, ficha.magias.getId());

                // se for uma atualizacao o id fica no final
                if(metodo.equals("atualizar")){
                    stmt.setInt(index, ficha.getIdFicha());
                }

                stmt.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Ficha readFicha(int idFicha){
        Connection connection = BD.getConnection();

        String sql = "SELECT * FROM " + Tabelas.FICHA + " WHERE idFicha = ?";

        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            stmt.setInt(1, idFicha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Criar a ficha com todos os parâmetros do construtor
                    Ficha ficha = new Ficha(
                            rs.getInt("idFicha"),
                            rs.getInt("user_idUser"),
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
                            rs.getString("idiomas"),
                            rs.getString("proficiencias"),
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
                            rs.getString("defeitos"),
                            true
                    );

                    ficha.criarInventario(rs.getInt("inventario_idInventario"));
                    ficha.criarMagias(rs.getInt("magiaUser_idMagiaUser"));

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null; // Retorna null se a ficha não for encontrada
    }

    public static void deleteFicha(int id){
        Connection connection = BD.getConnection();

        String sql = "DELETE FROM " + Tabelas.FICHA + " WHERE idFicha = ?";
        try (PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sql)) {
            stmt.setInt(1, id); // Define o parâmetro ID na query
            stmt.executeUpdate(); // Executa a exclusão
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // O PreparedStatement é automaticamente fechado ao sair do bloco try
    }
}
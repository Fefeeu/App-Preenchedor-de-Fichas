package br.rpp.sql;

import br.rpp.inventario.item.*;

import java.sql.*;

public abstract class SQLItem {
    public static int createItem(Item item, int idUser, int idFicha) throws SQLException {
        Connection connection = BD.getConnection();

        String sql = "INSERT INTO item (" +
                "iditem, ficha_user_idUser, ficha_idFicha, tipo, " +
                "nome, descricao, peso, moeda, preco, " +
                "usosMaximo, usos, efeito, bonus, bonus_ca, " +
                "proficiencia, numeroDeDados, dadoDeDano, atributo" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;

            // Dados básicos (3 parâmetros - chaves primárias)
            stmt.setInt(index++, item.getId()); // iditem (auto-increment)
            stmt.setInt(index++, idUser); // ficha_user_idUser (deve ser fornecido)
            stmt.setInt(index++, idFicha); // ficha_idFicha (deve ser fornecido)

            // Informações principais (6 parâmetros)
            stmt.setString(index++, item.getTipo());
            stmt.setString(index++, item.nome);
            stmt.setString(index++, item.descricao);
            stmt.setFloat(index++, item.peso);
            stmt.setString(index++, String.valueOf(item.moeda));
            stmt.setInt(index++, item.preco);

            switch (item) {
                // -------------------------- ITEM CONSUMIVEL --------------------------
                case ItemConsumivel itemConsumivel -> {
                    stmt.setInt(index++, itemConsumivel.getUsosMaximo());
                    stmt.setInt(index++, itemConsumivel.getUsos());
                    stmt.setString(index++, "");// efeito

                    stmt.setInt(index++, -1);   // bonus

                    stmt.setInt(index++, -1); // bonus CA

                    stmt.setBoolean(index++, false);
                    stmt.setInt(index++, -1);
                    stmt.setInt(index++, -1);
                    stmt.setString(index, "");
                }

                // -------------------------- ITEM MAGICO ------------------------------
                case ItemMagico itemMagico -> {
                    stmt.setInt(index++, itemMagico.getCargasMaxima());
                    stmt.setInt(index++, itemMagico.getCargas());
                    stmt.setString(index++, itemMagico.efeito);
                    stmt.setInt(index++, -1);   // bonus

                    stmt.setInt(index++, -1); // bonus CA

                    stmt.setBoolean(index++, false);
                    stmt.setInt(index++, -1);
                    stmt.setInt(index++, -1);
                    stmt.setString(index, "");
                }

                // -------------------------- ITEM EQUIPAVEL ---------------------------
                case Equipavel itemEquipavel -> {
                    if (item instanceof EquipavelMagico itemEquipavelMagico) {
                        stmt.setInt(index++, itemEquipavelMagico.getCargasMaxima());
                        stmt.setInt(index++, itemEquipavelMagico.getCargas());
                        stmt.setString(index++, itemEquipavelMagico.efeito);
                        stmt.setInt(index++, itemEquipavelMagico.bonus);
                    } else {
                        stmt.setInt(index++, -1);   // cargas maximas
                        stmt.setInt(index++, -1);   // cargas
                        stmt.setString(index++, "");// efeito
                        stmt.setInt(index++, -1);   // bonus
                    }
                    stmt.setInt(index++, itemEquipavel.bonusCA);
                    stmt.setBoolean(index++, itemEquipavel.proficiencia);
                    stmt.setInt(index++, -1);
                    stmt.setInt(index++, 1);
                    stmt.setString(index, "");
                }

                // -------------------------- ITEM ARMA --------------------------------
                case Arma itemArma -> {
                    if (itemArma instanceof ArmaMagica itemArmaMagica) {
                        stmt.setInt(index++, itemArmaMagica.getCargasMaxima());
                        stmt.setInt(index++, itemArmaMagica.getCargas());
                        stmt.setString(index++, itemArmaMagica.efeito);
                        stmt.setInt(index++, itemArmaMagica.bonus);
                    } else {
                        stmt.setInt(index++, -1);   // cargas maximas
                        stmt.setInt(index++, -1);   // cargas
                        stmt.setString(index++, "");// efeito
                        stmt.setInt(index++, -1);   // bonus
                    }
                    stmt.setInt(index++, -1); // bonus CA

                    stmt.setBoolean(index++, itemArma.proficiencia);
                    stmt.setInt(index++, itemArma.quantidadeDeDados);
                    stmt.setInt(index++, itemArma.dadoDeDano);
                    stmt.setString(index, itemArma.atributo);
                }

                // -------------------------- ITEM PADRAO ------------------------------
                default -> {
                    stmt.setInt(index++, -1);   // cargas maximas
                    stmt.setInt(index++, -1);   // cargas
                    stmt.setString(index++, "");// efeito
                    stmt.setInt(index++, -1);   // bonus
                    stmt.setInt(index++, -1); // bonus CA
                    stmt.setBoolean(index++, false);
                    stmt.setInt(index++, -1);
                    stmt.setInt(index++, -1);
                    stmt.setString(index, "");
                }
            }

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }
        return -1;
    }

    public static Item readItem(int idItem) throws SQLException {
        Connection connection = BD.getConnection();
        String sql = "SELECT * FROM item WHERE idItem = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idItem);

            try (ResultSet rs = stmt.executeQuery()) {
                Item item;
                if (rs.next()) {
                    switch (rs.getString("tipo")) {
                        // -------------------------- ITEM CONSUMIVEL --------------------------
                        case "consumivel": {
                            item = new ItemConsumivel(
                                rs.getInt("idItem"),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getFloat("peso"),
                                rs.getString("moeda").charAt(0),
                                rs.getInt("preco"),
                                rs.getInt("usos")
                            );
                        }

                        // -------------------------- ITEM MAGICO ------------------------------
                        case "magico": {
                            item = new ItemMagico(
                                rs.getInt("idItem"),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getFloat("peso"),
                                rs.getString("moeda").charAt(0),
                                rs.getInt("preco"),
                                rs.getString("efeito"),
                                rs.getInt("usos")
                            );
                        }

                        // -------------------------- ITEM EQUIPAVEL ---------------------------
                        case "equipavel": {
                            item = new Equipavel(
                                rs.getInt("idItem"),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getFloat("peso"),
                                rs.getString("moeda").charAt(0),
                                rs.getInt("preco"),
                                rs.getInt("bonus_ca"),
                                rs.getBoolean("proficiencia")
                            );
                        }

                        // -------------------------- ITEM EQUIPAVEL MAGICO --------------------
                        case "equipavelMagico": {
                            item = new EquipavelMagico(
                                rs.getInt("idItem"),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getFloat("peso"),
                                rs.getString("moeda").charAt(0),
                                rs.getInt("preco"),
                                rs.getInt("bonus_ca"),
                                rs.getBoolean("proficiencia"),
                                rs.getString("efeito"),
                                rs.getInt("usos"),
                                rs.getInt("bonus")
                            );
                        }

                        // -------------------------- ITEM ARMA --------------------------------
                        case "arma": {
                            item = new Arma(
                                rs.getInt("idItem"),
                                SQLFicha.readFicha(idItem),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getFloat("peso"),
                                rs.getString("moeda").charAt(0),
                                rs.getInt("preco"),
                                rs.getInt("dadoDeDano"),
                                rs.getInt("numeroDeDados"),
                                rs.getString("atributo"),
                                rs.getBoolean("proficiencia")
                            );
                        }

                        // -------------------------- ITEM ARMA MAGICA -------------------------
                        case "armaMagica": {
                            item = new ArmaMagica(
                                rs.getInt("idItem"),
                                SQLFicha.readFicha(idItem),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getFloat("peso"),
                                rs.getString("moeda").charAt(0),
                                rs.getInt("preco"),
                                rs.getInt("dadoDeDano"),
                                rs.getInt("numeroDeDados"),
                                rs.getString("atributo"),
                                rs.getBoolean("proficiencia"),
                                rs.getString("efeito"),
                                rs.getInt("usos"),
                                rs.getInt("bonus")
                            );
                        }

                        // -------------------------- ITEM PADRAO ------------------------------
                        default: {
                            item = new Item(
                                rs.getInt("idItem"),
                                rs.getString("nome"),
                                rs.getString("descricao"),
                                rs.getFloat("peso"),
                                rs.getString("moeda").charAt(0),
                                rs.getInt("preco")
                            );
                        }
                    }

                    return item;
                }
            }
        }
        return null; // Retorna null se a ficha não for encontrada
    }

    public static void deleteItem(int id) throws SQLException {
        Connection connection = BD.getConnection();
        String sql = "DELETE FROM item WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id); // Define o parâmetro ID na query
            stmt.executeUpdate(); // Executa a exclusão
        }
        // O PreparedStatement é automaticamente fechado ao sair do bloco try
    }

    public static int gerarIdItem() {
        final String sql = "SELECT COALESCE(MAX(idItem), 0) + 1 FROM item";

        try (Connection connection = BD.getConnection()) {
            assert connection != null;
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                if (!rs.next()) {
                    throw new SQLException("Nenhum resultado encontrado ao gerar ID para item");
                }
                return rs.getInt(1);
            }catch (SQLException e) {
                System.err.println("Erro ao executar consulta SQL: " + e.getMessage());
                throw new SQLException("Falha ao executar consulta para gerar ID", e);
            }

        } catch (SQLException e) {
            System.err.println("Erro de conexão com o banco de dados: " + e.getMessage());
            try {
                throw new SQLException("Falha na conexão ao tentar gerar ID para item", e);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
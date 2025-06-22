/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.util;

/**
 *
 * @author Athos
 */
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TesteConexao {

    public static void main(String[] args) throws Exception {
        Connection conexao = null;
        try {
            conexao = DatabaseUtil.getConnection();
            System.out.println("SUCESSO: Conexão com o banco de dados estabelecida!");

            // --- INÍCIO DO CÓDIGO PARA LISTAR TABELAS ---

            // 1. Obtém os metadados da conexão
            DatabaseMetaData metaData = conexao.getMetaData();

            try ( // 2. Pede ao metadados uma lista de tabelas.
            // Parâmetros: (catalog, schemaPattern, tableNamePattern, types)
            // - conexao.getCatalog(): Pega o nome do banco de dados atual da conexão.
            // - null, null: Não aplicamos filtros de schema ou nome de tabela.
            // - new String[]{"TABLE"}: Dizemos que queremos apenas as "Tabelas" (e não Views, etc.).
                    ResultSet tables = metaData.getTables(conexao.getCatalog(), null, null, new String[]{"TABLE"})) {
                System.out.println("\n--- TABELAS ENCONTRADAS NO BANCO DE DADOS ---");
                // 3. Percorre o resultado (ResultSet) que contém a lista de tabelas
                while (tables.next()) {
                    // A coluna que contém o nome da tabela se chama "TABLE_NAME"
                    String nomeTabela = tables.getString("TABLE_NAME");
                    System.out.println(nomeTabela);
                }   System.out.println("--- FIM DA LISTA ---");
                // O try-with-resources fechará o ResultSet e a Conexão automaticamente.
                // --- FIM DO CÓDIGO ---
            }
            
        } catch (SQLException e) {
            System.err.println("FALHA: Ocorreu um erro ao tentar conectar ao banco de dados.");
            e.printStackTrace();

        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                    System.out.println("INFO: Conexão fechada com sucesso.");
                    
                   
                } catch (SQLException e) {
                    System.err.println("ERRO AO FECHAR: Não foi possível fechar a conexão.");
                    e.printStackTrace();
                }
            }
        }
    }
}

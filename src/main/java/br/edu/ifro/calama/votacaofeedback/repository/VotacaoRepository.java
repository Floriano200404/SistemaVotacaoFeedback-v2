/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.repository;

import br.edu.ifro.calama.votacaofeedback.model.Votacao;
import br.edu.ifro.calama.votacaofeedback.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Athos
 */
public class VotacaoRepository {
    

    public int criar(Votacao votacao) throws SQLException, Exception {
        
        String sql = "INSERT INTO Votacao (titulo, descricao, data_inicio, data_fim, data_Resultado, id_grupo_destino, id_Criador, status, pergunta) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        int idGerado = -1;
        
        try (Connection conexao = DatabaseUtil.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, votacao.getTitulo());
            ps.setString(2, votacao.getDescricao());
            ps.setDate(3, new java.sql.Date(votacao.getDataInicial().getTime()));
            ps.setDate(4, new java.sql.Date(votacao.getDataFinal().getTime()));
            ps.setDate(5, new java.sql.Date(votacao.getDataResultado().getTime()));
            ps.setInt(6, votacao.getIdGrupoDestino());
            ps.setInt(7, votacao.getIdCriador());
            ps.setString(8, votacao.getStatus());
            ps.setString(9, votacao.getPergunta());

            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idGerado = rs.getInt(1);
                }   
            }   
            
        }
        return idGerado;
    }
   

public java.util.List<Votacao> buscarPendentes() throws Exception {
    String sql = "SELECT * FROM votacao WHERE status = 'PENDENTE'";
    java.util.List<Votacao> votacoes = new java.util.ArrayList<>();

    try (java.sql.Connection conexao = br.edu.ifro.calama.votacaofeedback.util.DatabaseUtil.getConnection();
         java.sql.PreparedStatement ps = conexao.prepareStatement(sql);
         java.sql.ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Votacao votacao = new Votacao();
           
            votacao.setIdVotacao(rs.getInt("id_Votacao"));
            votacao.setTitulo(rs.getString("titulo"));
            votacao.setDescricao(rs.getString("descricao"));
            votacao.setDataInicial(rs.getDate("data_inicio"));
            votacao.setDataFinal(rs.getDate("data_fim"));
            votacao.setDataResultado(rs.getDate("data_Resultado"));
            votacao.setStatus(rs.getString("status"));
            votacao.setPergunta(rs.getString("pergunta"));
            votacao.setIdCriador(rs.getInt("id_Criador"));
            votacao.setIdGrupoDestino(rs.getInt("id_grupo_destino"));

            votacoes.add(votacao);
        }
    }
    return votacoes;
}

public List<Votacao> buscarPorIdCriador(int idCriador) throws Exception {
        List<Votacao> votacoes = new ArrayList<>();
        // ATENÇÃO: Verifique se o nome da coluna 'id_criador' está correto no seu banco.
        String sql = "SELECT * FROM votacao WHERE id_criador = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCriador);
            
            try (ResultSet rs = stmt.executeQuery()) {
                // Reutilizando a mesma lógica do seu método buscarPendentes
                while (rs.next()) {
                    Votacao votacao = new Votacao();
                    votacao.setIdVotacao(rs.getInt("id_votacao"));
                    votacao.setTitulo(rs.getString("titulo"));
                    votacao.setDescricao(rs.getString("descricao"));
                    votacao.setPergunta(rs.getString("pergunta"));
                    votacao.setStatus(rs.getString("status"));
                    votacao.setDataInicial(rs.getTimestamp("data_inicio"));
                    votacao.setDataFinal(rs.getTimestamp("data_fim"));
                    votacao.setDataResultado(rs.getTimestamp("data_resultado"));
                    votacao.setIdCriador(rs.getInt("id_criador"));
                    votacao.setIdGrupoDestino(rs.getInt("id_grupo_destino"));
                    votacoes.add(votacao);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar votações por criador: " + e.getMessage());
            e.printStackTrace();
        }
        return votacoes;
    }

    /**
     * Atualiza os dados de uma votação existente no banco.
     * @param votacao O objeto Votacao com os dados atualizados.
     */
    public void atualizar(Votacao votacao) throws Exception {
        // ATENÇÃO: Verifique se os nomes das colunas correspondem ao seu banco de dados.
        String sql = "UPDATE votacao SET titulo = ?, descricao = ?, data_inicio = ?, " +
                     "data_final = ?, data_resultado = ?, id_grupo_destino = ?, status = ? " +
                     "WHERE id_votacao = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, votacao.getTitulo());
            stmt.setString(2, votacao.getDescricao());
            // Usando setTimestamp para preservar a informação de hora, caso exista.
            stmt.setTimestamp(3, new java.sql.Timestamp(votacao.getDataInicial().getTime()));
            stmt.setTimestamp(4, new java.sql.Timestamp(votacao.getDataFinal().getTime()));
            stmt.setTimestamp(5, new java.sql.Timestamp(votacao.getDataResultado().getTime()));
            stmt.setInt(6, votacao.getIdGrupoDestino());
            stmt.setString(7, votacao.getStatus());
            stmt.setInt(8, votacao.getIdVotacao()); // ID para a cláusula WHERE

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                 System.out.println("Votação com ID " + votacao.getIdVotacao() + " atualizada com sucesso.");
            } else {
                 System.out.println("Nenhuma votação encontrada com o ID " + votacao.getIdVotacao() + " para atualizar.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar a votação: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

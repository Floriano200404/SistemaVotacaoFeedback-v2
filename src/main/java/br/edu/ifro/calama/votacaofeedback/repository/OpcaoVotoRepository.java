/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.repository;

import br.edu.ifro.calama.votacaofeedback.model.OpcaoVoto;
import br.edu.ifro.calama.votacaofeedback.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Athos
 */
public class OpcaoVotoRepository {

    public void criar(OpcaoVoto opcao) throws SQLException, Exception {
        String sql = "INSERT INTO opcao_voto (descricao, id_votacao) VALUES (?, ?)";

        try (Connection conexao = DatabaseUtil.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, opcao.getDescricao());
            ps.setInt(2, opcao.getIdVotacao());

            ps.executeUpdate();
        }
    }
  
    public List<OpcaoVoto> buscarPorIdVotacao(int idVotacao) {
    List<OpcaoVoto> opcoes = new ArrayList<>();
    String sql = "SELECT * FROM opcao_voto WHERE id_votacao = ?";

    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idVotacao);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                OpcaoVoto opcao = new OpcaoVoto();
                opcao.setIdOpcaoVoto(rs.getInt("id_opcao_voto"));
                opcao.setDescricao(rs.getString("descricao"));
                opcao.setIdVotacao(rs.getInt("id_votacao"));
                opcoes.add(opcao);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return opcoes;
}
 
    public void deletarPorIdVotacao(int idVotacao) {
    String sql = "DELETE FROM opcao_voto WHERE id_votacao = ?";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idVotacao);
        stmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    } 
}

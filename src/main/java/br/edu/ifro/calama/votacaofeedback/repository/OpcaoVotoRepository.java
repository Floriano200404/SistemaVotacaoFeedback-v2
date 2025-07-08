/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.repository;

import br.edu.ifro.calama.votacaofeedback.model.OpcaoVoto;
import br.edu.ifro.calama.votacaofeedback.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Athos
 */
public class OpcaoVotoRepository {

    public void criar(OpcaoVoto opcao) throws SQLException, Exception {
        // Adapte o nome da tabela e colunas se forem diferentes no seu banco
        String sql = "INSERT INTO opcao_voto (descricao, id_votacao) VALUES (?, ?)";

        try (Connection conexao = DatabaseUtil.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, opcao.getDescricao());
            ps.setInt(2, opcao.getIdVotacao());

            ps.executeUpdate();
        }
    }
}

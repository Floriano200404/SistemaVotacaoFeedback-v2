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

/**
 *
 * @author Athos
 */
public class VotacaoRepository {

    public int criar(Votacao votacao) throws SQLException, Exception {
        // SQL correspondente à sua tabela
        String sql = "INSERT INTO Votacao (titulo, descricao, data_inicio, data_fim, data_Resultado, id_grupo_destino, id_Criador, status, pergunta) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        int idGerado = -1;
        
        try (Connection conexao = DatabaseUtil.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, votacao.getTitulo());
            ps.setString(2, votacao.getDescricao());
            ps.setDate(3, new java.sql.Date(votacao.getDataInicio().getTime()));
            ps.setDate(4, new java.sql.Date(votacao.getDataFim().getTime()));
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
}

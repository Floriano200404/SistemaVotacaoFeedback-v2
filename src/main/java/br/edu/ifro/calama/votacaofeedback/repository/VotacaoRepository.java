/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.repository;

import br.edu.ifro.calama.votacaofeedback.model.Usuario;
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
public void atualizarStatus(int idVotacao, String novoStatus) throws Exception {
    
    String sql = "UPDATE votacao SET status = ? WHERE id_Votacao = ?";

    try (java.sql.Connection conn = br.edu.ifro.calama.votacaofeedback.util.DatabaseUtil.getConnection();
         java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, novoStatus); 
        ps.setInt(2, idVotacao);   

        ps.executeUpdate(); // Executa o comando de atualização no banco
    }
}
public java.util.List<Votacao> buscarAtivasPorUsuario(Usuario usuario) throws Exception {
    String sql;
    java.util.List<Votacao> votacoes = new java.util.ArrayList<>();

    
    if ("ADMIN".equals(usuario.getTipo_usuario())) {
       
        sql = "SELECT * FROM votacao WHERE status = 'APROVADA'"; 
    } else {
        
        sql = "SELECT v.* FROM votacao v " +
              "INNER JOIN usuario_grupos ug ON v.id_grupo_destino = ug.id_grupo " +
              "WHERE ug.id_usuario = ? AND v.status = 'APROVADA'";
    }

    try (java.sql.Connection conn = br.edu.ifro.calama.votacaofeedback.util.DatabaseUtil.getConnection();
         java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {

        
        if (!"ADMIN".equals(usuario.getTipo_usuario())) {
            
            ps.setInt(1, usuario.getId()); 
        }

        try (java.sql.ResultSet rs = ps.executeQuery()) {
            
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
    }
    return votacoes; 
}
}

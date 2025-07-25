/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.repository;

import br.edu.ifro.calama.votacaofeedback.model.StatusVotacao;
import br.edu.ifro.calama.votacaofeedback.model.Usuario;
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
   

    public List<Votacao> buscarTodosPendentes() throws SQLException, Exception {
        String sql = "SELECT * FROM votacao WHERE status = 'PENDENTE'";
        List<Votacao> votacoes = new ArrayList<>();
        try (Connection conexao = DatabaseUtil.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                votacoes.add(mapRowToVotacao(rs));
            }
        }
        return votacoes;
    }

    public List<Votacao> buscarPendentesPorCriador(int idCriador) throws SQLException, Exception {
        List<Votacao> votacoes = new ArrayList<>();
        String sql = "SELECT * FROM votacao WHERE id_criador = ? AND status = 'PENDENTE'";
        try (Connection conexao = DatabaseUtil.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idCriador);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    votacoes.add(mapRowToVotacao(rs));
                }
            }
        }
        return votacoes;
    }

    public void atualizar(Votacao votacao) throws SQLException, Exception {
    String sql = "UPDATE votacao SET " +
                 "titulo = ?, descricao = ?, data_inicio = ?, " +
                 "data_fim = ?, data_Resultado = ?, id_grupo_destino = ?, " +
                 "status = ?, pergunta = ? " +
                 "WHERE id_votacao = ?";

    Connection conn = DatabaseUtil.getConnection();
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, votacao.getTitulo());
        stmt.setString(2, votacao.getDescricao());
        stmt.setTimestamp(3, new java.sql.Timestamp(votacao.getDataInicial().getTime()));
        stmt.setTimestamp(4, new java.sql.Timestamp(votacao.getDataFinal().getTime()));
        stmt.setTimestamp(5, new java.sql.Timestamp(votacao.getDataResultado().getTime()));
        stmt.setInt(6, votacao.getIdGrupoDestino());
        stmt.setString(7, votacao.getStatus());
        stmt.setString(8, votacao.getPergunta());
        stmt.setInt(9, votacao.getIdVotacao());

        stmt.executeUpdate();
    } finally {
        if (conn != null) {
            conn.close();
        }
    }
}

    public void atualizarStatus(int idVotacao, String novoStatus) throws Exception {

        String sql = "UPDATE votacao SET status = ? WHERE id_Votacao = ?";

        try (java.sql.Connection conn = br.edu.ifro.calama.votacaofeedback.util.DatabaseUtil.getConnection();
             java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, novoStatus); 
            ps.setInt(2, idVotacao);   

            ps.executeUpdate();
        }
    }
  
    
    public java.util.List<Votacao> buscarVotacoesComResultadoDisponivel() throws Exception {
    // Este SQL seleciona todas as votações onde a data de resultado é hoje ou já passou
    String sql = "SELECT * FROM votacao WHERE data_Resultado <= CURDATE()";

    java.util.List<Votacao> votacoes = new java.util.ArrayList<>();

    try (java.sql.Connection conn = br.edu.ifro.calama.votacaofeedback.util.DatabaseUtil.getConnection();
         java.sql.PreparedStatement ps = conn.prepareStatement(sql);
         java.sql.ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            // Aqui usamos o método 'mapRowToVotacao' que já criamos para evitar repetir código
            Votacao votacao = mapRowToVotacao(rs);
            votacoes.add(votacao);
        }
    }
    return votacoes;
}
    
  
public java.util.List<Votacao> buscarAtivasPorUsuario(Usuario usuario) throws Exception {
    String sql;
    java.util.List<Votacao> votacoes = new java.util.ArrayList<>();
    java.util.Date hoje = new java.util.Date(); // Pega a data atual para comparação

    
    if ("ADMIN".equals(usuario.getTipo_usuario())) {
        sql = "SELECT * FROM votacao WHERE status = 'APROVADA' AND ? BETWEEN data_inicio AND data_fim";
    } else {
         
        sql = "SELECT v.* FROM votacao v " +
              "INNER JOIN usuario_grupos ug ON v.id_grupo_destino = ug.id_grupo " +
              "WHERE ug.id_usuario = ? AND v.status = 'APROVADA' AND ? BETWEEN v.data_inicio AND v.data_fim";
    }

    try (java.sql.Connection conn = br.edu.ifro.calama.votacaofeedback.util.DatabaseUtil.getConnection();
         java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {

        
        if ("ADMIN".equals(usuario.getTipo_usuario())) {
            ps.setDate(1, new java.sql.Date(hoje.getTime()));
        } else {
            ps.setInt(1, usuario.getId());
            ps.setDate(2, new java.sql.Date(hoje.getTime()));
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
    return votacoes; // Retorna a lista de votações encontradas
}
    
    
    
    private Votacao mapRowToVotacao(ResultSet rs) throws SQLException {
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
        return votacao;
    }
    
    //contagem das votações pelo status, mais para pendente
    public int countByStatus(String status) throws Exception {
        String sql = "SELECT COUNT(*) FROM votacao WHERE status = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //contagem das votações ativas
    public int countAtivas() throws Exception {
        // NOW() é uma função do MySQL para pegar a data e hora atuais.
        String sql = "SELECT COUNT(*) FROM votacao WHERE status = 'APROVADA' AND NOW() BETWEEN data_inicio AND data_fim";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int countPendentesPorCriador(int idCriador) throws Exception {
    String sql = "SELECT COUNT(*) FROM votacao WHERE status = 'PENDENTE' AND id_criador = ?";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idCriador);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}
    
    public List<Votacao> buscarArquivadasPorUsuario(Usuario usuario) throws Exception {
        List<Votacao> votacoes = new ArrayList<>();
        String sql;

        // Se for admin, mostra todas as concluídas.
        if ("ADMIN".equals(usuario.getTipo_usuario())) {
            sql = "SELECT * FROM votacao WHERE status = '" + StatusVotacao.CONCLUIDA + "' OR status = '" + StatusVotacao.REPROVADA + "'";
        } else {
            // Se for usuário comum, mostra as concluídas/reprovadas que ele podia ver (dos grupos dele)
            sql = "SELECT v.* FROM votacao v " +
                  "INNER JOIN usuario_grupos ug ON v.id_grupo_destino = ug.id_grupo " +
                  "WHERE ug.id_usuario = ? AND (v.status = '" + StatusVotacao.CONCLUIDA + "' OR v.status = '" + StatusVotacao.REPROVADA + "')";
        }

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (!"ADMIN".equals(usuario.getTipo_usuario())) {
                ps.setInt(1, usuario.getId());
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    votacoes.add(mapRowToVotacao(rs));
                }
            }
        }
        return votacoes;
    }

}

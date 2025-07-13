package br.edu.ifro.calama.votacaofeedback.repository;

import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class UsuarioRepository {

    public int cadastrar(Usuario usuario) throws SQLException, Exception {
        String sql = "INSERT INTO Usuarios (nome, CPF, matricula, email, senha, Tipo_usuario) VALUES (?, ?, ?, ?, SHA2(?, 256), ?)";
        int idGerado = -1;

        try (Connection conexao = DatabaseUtil.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getCpf());
            ps.setString(3, usuario.getMatricula());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getSenha());
            ps.setString(6, usuario.getTipo_usuario());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idGerado = rs.getInt(1);
                }
            }
        }
        return idGerado;
    }

    public void associarUsuarioAGrupo(int idUsuario, int idGrupo) throws SQLException, Exception {
        String sql = "INSERT INTO Usuario_Grupos (id_usuario, id_grupo) VALUES (?, ?)";
        try (Connection conexao = DatabaseUtil.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setInt(2, idGrupo);
            ps.executeUpdate();
        }
    }

    public Usuario autenticar(String email, String senha) throws SQLException, Exception {
        String sql = "SELECT " +
                     "    u.id_usuario, u.nome, u.CPF, u.matricula, u.email, u.Tipo_usuario, " +
                     "    CASE " +
                     "        WHEN u.Tipo_usuario IN ('ADMIN', 'PROFESSOR', 'SERVIDOR') THEN u.Tipo_usuario " +
                     "        WHEN u.Tipo_usuario = 'DISCENTE' THEN " +
                     "            CASE g.nome " +
                     "                WHEN 'ANÁLISE E DESENVOLVIMENTO DE SISTEMAS' THEN 'ANÁLISE E DESENV. DE SISTEMAS' " +
                     "                WHEN 'ENGENHARIA DE CONTROLE E AUTOMAÇÃO' THEN 'ENG. DE CONTROLE E AUTOMAÇÃO' " +
                     "                ELSE g.nome " +
                     "            END " +
                     "        ELSE u.Tipo_usuario " +
                     "    END AS nome_curso " +
                     "FROM " +
                     "    Usuarios u " +
                     "LEFT JOIN " +
                     "    Usuario_Grupos ug ON u.id_usuario = ug.id_usuario " +
                     "LEFT JOIN " +
                     "    Grupos g ON ug.id_grupo = g.id_Grupos AND g.tipo_grupo = 'CURSO' " +
                     "WHERE " +
                     "    u.email = ? AND u.senha = SHA2(?, 256)";

        Usuario usuarioEncontrado = null;

        try (Connection conexao = DatabaseUtil.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, senha);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    usuarioEncontrado = new Usuario();

                    usuarioEncontrado.setId(rs.getInt("id_usuario"));
                    usuarioEncontrado.setNome(rs.getString("nome"));
                    usuarioEncontrado.setEmail(rs.getString("email"));
                    usuarioEncontrado.setTipo_usuario(rs.getString("Tipo_usuario"));
                    usuarioEncontrado.setCpf(rs.getString("CPF")); 
                    usuarioEncontrado.setMatricula(rs.getString("matricula"));
                    usuarioEncontrado.setCurso(rs.getString("nome_curso"));
                }
            }
        }

        // O seu debug continua útil aqui
        if (usuarioEncontrado != null) {
            System.out.println("--- DEBUG REPOSITORY (CORRIGIDO) ---");
            System.out.println("Usuário ENCONTRADO e dados completos carregados.");
            System.out.println("Nome: " + usuarioEncontrado.getNome());
            System.out.println("CPF: " + usuarioEncontrado.getCpf());
            System.out.println("Matrícula: " + usuarioEncontrado.getMatricula());
            System.out.println("Curso: " + usuarioEncontrado.getCurso());
        } else {
            System.out.println("--- DEBUG REPOSITORY ---");
            System.out.println("Usuário NÃO ENCONTRADO no banco com as credenciais fornecidas.");
        }
    
        return usuarioEncontrado;
    }
    
    public Usuario buscarPorEmail(String email) throws SQLException, Exception {
        String sql = "SELECT * FROM Usuarios WHERE email = ?";
        Usuario usuarioEncontrado = null;
        
        try (Connection conexao = DatabaseUtil.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuarioEncontrado = new Usuario();
                    usuarioEncontrado.setId(rs.getInt("id_usuario"));
                    usuarioEncontrado.setNome(rs.getString("nome"));
                    usuarioEncontrado.setEmail(rs.getString("email"));
                    usuarioEncontrado.setToken(rs.getString("token"));
                    usuarioEncontrado.setTokenExpiracao(rs.getTimestamp("token_expiracao"));
                    // Carregue outros campos se necessário
                }
            }
        }
        return usuarioEncontrado;
    }
    
    public void salvarToken(int usuarioId, String token, Date dataExpiracao) throws SQLException, Exception {
        String sql = "UPDATE Usuarios SET token = ?, token_expiracao = ? WHERE id_usuario = ?";
        try (Connection conexao = DatabaseUtil.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            
            ps.setString(1, token);
            ps.setTimestamp(2, new java.sql.Timestamp(dataExpiracao.getTime()));
            ps.setInt(3, usuarioId);
            ps.executeUpdate();
        }
    }
    
    public void atualizarSenha(int usuarioId, String novaSenha) throws SQLException, Exception {
    String sql = "UPDATE Usuarios SET senha = SHA2(?, 256), token = NULL, token_expiracao = NULL WHERE id_usuario = ?";
    
    try (Connection conexao = DatabaseUtil.getConnection();
         PreparedStatement ps = conexao.prepareStatement(sql)) {
        
        ps.setString(1, novaSenha);
        ps.setInt(2, usuarioId);
        ps.executeUpdate();
    }
}
    
}
package br.edu.ifro.calama.votacaofeedback.repository;

import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRepository {

    public void cadastrar(Usuario usuario) throws SQLException, Exception {
        String sql = "INSERT INTO Usuarios (nome, email, senha) VALUES (?, ?, ?)";

        try (Connection conexao = DatabaseUtil.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());

            ps.executeUpdate();
        }
    }
    
    public Usuario autenticar(String email, String senha) throws SQLException, Exception {
    String sql = "SELECT * FROM Usuarios WHERE email = ? AND senha = ?";
    Usuario usuarioEncontrado = null;

    try (Connection conexao = DatabaseUtil.getConnection();
         PreparedStatement ps = conexao.prepareStatement(sql)) {

        ps.setString(1, email);
        ps.setString(2, senha);

        try (ResultSet rs = ps.executeQuery()) {
            // Se o ResultSet encontrar uma linha, significa que o usuário existe
            if (rs.next()) {
                usuarioEncontrado = new Usuario();
                usuarioEncontrado.setId(rs.getInt("id_usuario")); // Use o nome exato da sua coluna
                usuarioEncontrado.setNome(rs.getString("nome"));
                usuarioEncontrado.setEmail(rs.getString("email"));
                // Não pegamos a senha por segurança
            }
        }
    }
    return usuarioEncontrado;
}
    
}
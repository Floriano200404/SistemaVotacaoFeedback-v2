package br.edu.ifro.calama.votacaofeedback.repository;

import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        String sql = "SELECT * FROM Usuarios WHERE email = ? AND senha = SHA2(?, 256)";
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
                }
            }
        }
        return usuarioEncontrado;
    }
}
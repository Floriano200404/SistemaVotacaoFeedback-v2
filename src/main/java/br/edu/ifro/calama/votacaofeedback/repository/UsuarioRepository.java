package br.edu.ifro.calama.votacaofeedback.repository;

import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioRepository {

    /**
     * Cadastra um novo usuário e retorna o ID gerado.
     * @param usuario O objeto Usuario com os dados a serem inseridos.
     * @return O ID (int) do usuário recém-criado.
     * @throws java.sql.SQLException
     */
    public int cadastrar(Usuario usuario) throws SQLException, Exception {
        String sql = "INSERT INTO Usuarios (nome, CPF, matricula, email, senha, Tipo_usuario) VALUES (?, ?, ?, ?, ?, ?)";
        int idGerado = -1;

        // Usamos Statement.RETURN_GENERATED_KEYS para poder pegar o ID de volta
        try (Connection conexao = DatabaseUtil.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getCpf());
            ps.setString(3, usuario.getMatricula());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getSenha());
            ps.setString(6, usuario.getTipo_usuario());

            ps.executeUpdate();

            // Pega o ID que o banco gerou para o novo usuário
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idGerado = rs.getInt(1);
                }
            }
        }
        return idGerado;
    }

    /**
     * Associa um usuário a um grupo na tabela de junção.
     * @param idUsuario O ID do usuário.
     * @param idGrupo O ID do grupo.
     * @throws java.sql.SQLException
     */
    public void associarUsuarioAGrupo(int idUsuario, int idGrupo) throws SQLException, Exception {
        String sql = "INSERT INTO Usuario_Grupos (id_usuario, id_grupo) VALUES (?, ?)";

        try (Connection conexao = DatabaseUtil.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setInt(2, idGrupo);
            ps.executeUpdate();
        }
    }
    
    // ... seu método autenticar continua aqui ...

    public Usuario autenticar(String login, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
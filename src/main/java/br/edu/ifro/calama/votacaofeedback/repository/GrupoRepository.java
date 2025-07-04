/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.repository;

import br.edu.ifro.calama.votacaofeedback.model.Grupo;
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
public class GrupoRepository {

    /**
     * Busca todos os grupos cadastrados no banco de dados.
     * @return Uma lista de objetos do tipo Grupo.
     */
    public List<Grupo> buscarTodos() throws SQLException, Exception {
        // Adapte o nome da tabela e colunas se forem diferentes
        String sql = "SELECT id_Grupos, nome FROM grupos ORDER BY nome";
        List<Grupo> grupos = new ArrayList<>();

        try (Connection conexao = DatabaseUtil.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Grupo grupo = new Grupo();
                grupo.setIdGrupo(rs.getInt("id_Grupos"));
                grupo.setNome(rs.getString("nome"));
                grupos.add(grupo);
            }
        }
        return grupos;
    }
}

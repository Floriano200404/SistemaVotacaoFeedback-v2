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
    // Agora o SELECT também busca a nova coluna
    String sql = "SELECT id_Grupos, nome, tipo_grupo FROM grupos WHERE UPPER(nome) NOT IN ('PROFESSORES', 'SERVIDORES') ORDER BY nome";

    System.out.println("Executando SQL para buscar grupos: " + sql);
    
    List<Grupo> grupos = new ArrayList<>();

    try (Connection conexao = DatabaseUtil.getConnection();
         PreparedStatement ps = conexao.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        while (rs.next()) {
            Grupo grupo = new Grupo();
            grupo.setIdGrupo(rs.getInt("id_Grupos"));
            grupo.setNome(rs.getString("nome"));
            grupo.setTipo_grupo(rs.getString("tipo_grupo"));
            grupos.add(grupo);
        }
    }
        return grupos;
    }
}
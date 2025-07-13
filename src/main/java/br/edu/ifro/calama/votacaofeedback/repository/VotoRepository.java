package br.edu.ifro.calama.votacaofeedback.repository;


import br.edu.ifro.calama.votacaofeedback.model.Voto;
import br.edu.ifro.calama.votacaofeedback.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;


public class VotoRepository {
 public void salvar(Voto voto) throws Exception {
        System.out.println("--- 4. VotoRepository.salvar chamado. Executando INSERT... ---");
        String sql = "INSERT INTO voto (id_Votacao, id_Usuario, id_Opcao, data) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, voto.getIdVotacao());
            ps.setInt(2, voto.getIdUsuario());
            ps.setInt(3, voto.getIdOpcao());
            ps.setDate(4, new java.sql.Date(new Date().getTime()));

            ps.executeUpdate();
            System.out.println("--- 5. INSERT executado com sucesso! ---");
        }
    }

    public boolean usuarioJaVotou(int idUsuario, int idVotacao) throws Exception {
        String sql = "SELECT COUNT(*) FROM voto WHERE id_Usuario = ? AND id_Votacao = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setInt(2, idVotacao);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
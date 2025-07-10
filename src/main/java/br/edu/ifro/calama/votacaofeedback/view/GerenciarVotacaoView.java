/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;

import br.edu.ifro.calama.votacaofeedback.controller.VotacaoController;
import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.model.Votacao;
import br.edu.ifro.calama.votacaofeedback.repository.VotacaoRepository;
import br.edu.ifro.calama.votacaofeedback.service.VotacaoService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Athos
 */
public final class GerenciarVotacaoView extends javax.swing.JPanel {

    // NOVO: Campo para armazenar o usuário que está usando a tela
    private Usuario usuarioLogado;

    /**
     * Creates new form GerenciarVotacaoView
     * ALTERADO: O construtor agora recebe o usuário logado.
     */
    public GerenciarVotacaoView(Usuario usuario) {
        initComponents();
        this.usuarioLogado = usuario;

        // Verifica se o usuário não é nulo antes de carregar
        if (this.usuarioLogado == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro: Usuário não identificado.", "Erro de Sessão", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        carregarVotacoesDoUsuario();
    }

    // ALTERADO: O nome do método para maior clareza
    public void carregarVotacoesDoUsuario() {
        VotacaoRepository votacaoRepo = new VotacaoRepository();

        try {
            // ALTERADO: Lógica de busca. Em vez de buscar pendentes, busca as votações criadas pelo usuário logado.
            // Você precisará criar o método 'buscarPorIdCriador' em seu VotacaoRepository.
            VotacaoController controller = new VotacaoController();
            List<Votacao> votacoesDoUsuario = controller.buscarVotacoesPorCriador(this.usuarioLogado.getId());
        
            painelDaGrade.removeAll();
            
            // ALTERADO: O laço agora configura cada card para o modo de GERENCIAMENTO.
            for (Votacao votacao : votacoesDoUsuario) {
                CardView card = new CardView();
                card.setDados(votacao);
                
                // --- Ponto Chave da Lógica ---
                // NOVO: Passa o usuário logado para o card.
                card.setUsuario(this.usuarioLogado); 
                // NOVO: Define o modo do card, para que ele saiba qual botão mostrar no diálogo.
                card.setModo(DetalhesVotacaoDialog.ModoDialogo.GERENCIAMENTO);
                // -----------------------------

                painelDaGrade.add(card);
            }
            
            painelDaGrade.revalidate();
            painelDaGrade.repaint();

        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao buscar suas votações.", "Erro de Conexão", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelDaGrade = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());
        add(painelDaGrade, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel painelDaGrade;
    // End of variables declaration//GEN-END:variables
}

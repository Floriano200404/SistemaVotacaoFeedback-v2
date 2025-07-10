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
 * @author floriano
 */
public final class AprovarVotacaoView extends javax.swing.JPanel {

    private Usuario usuarioLogado;
    /**
     * Creates new form AprovarVotacaoView
     */
    public AprovarVotacaoView(Usuario usuario) {
        initComponents();
        this.usuarioLogado = usuario;

        if (this.usuarioLogado == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro fatal: Usuário não fornecido para a tela de aprovação.", "Erro de Sessão", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        carregarVotacoesParaAprovacao();
    }

    public void carregarVotacoesParaAprovacao() {
        try {
            VotacaoController controller = new VotacaoController();
            List<Votacao> votacoesPendentes = controller.buscarVotacoesPendentes();

            painelDaGrade.removeAll();
            
            for (Votacao votacao : votacoesPendentes) {
                CardView card = new CardView();
                card.setDados(votacao);
                card.setUsuario(this.usuarioLogado);
                card.setModo(DetalhesVotacaoDialog.ModoDialogo.APROVACAO);
                
                painelDaGrade.add(card);
            }

            painelDaGrade.revalidate();
            painelDaGrade.repaint();

        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao buscar votações para aprovação.", "Erro de Conexão", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelDaGrade = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        painelDaGrade.setLayout(new java.awt.GridLayout());
        add(painelDaGrade, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel painelDaGrade;
    // End of variables declaration//GEN-END:variables
}

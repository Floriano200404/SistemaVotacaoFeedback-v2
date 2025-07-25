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

    private Usuario usuarioLogado;
    private MenuPrincipalView menuPrincipal;
    
    public GerenciarVotacaoView(MenuPrincipalView menuPrincipal, Usuario usuario) {
        initComponents();
        this.usuarioLogado = usuario;
        this.menuPrincipal = menuPrincipal;

        if (this.usuarioLogado == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro: Usuário não identificado.", "Erro de Sessão", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        carregarVotacoesDoUsuario();
    }

    public void carregarVotacoesDoUsuario() {
        try {
        VotacaoController controller = new VotacaoController();
        
        // ALTERADO: Chamando o novo método que busca apenas as pendentes do usuário
        List<Votacao> votacoesDoUsuario = controller.buscarPendentesPorCriador(this.usuarioLogado.getId());
        
        painelDaGrade.removeAll();
        for (Votacao votacao : votacoesDoUsuario) {
            CardView card = new CardView();
            card.setDados(votacao);
            card.setUsuario(this.usuarioLogado);
            card.setModo(DetalhesVotacaoDialog.ModoDialogo.GERENCIAMENTO);
            card.setTelaDeOrigem(this); // Passa a própria tela para poder ser atualizada
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

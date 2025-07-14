/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;

import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.model.Votacao;
import br.edu.ifro.calama.votacaofeedback.service.VotacaoService;
import java.util.List;

/**
 *
 * @author floriano
 */

public class VotacoesArquivadasView extends javax.swing.JPanel {

    /**
     * Creates new form VotacoesArquivadasView
     */
    
    public VotacoesArquivadasView() {
        initComponents();
       
    
    }
        public void carregarVotacoesArquivadas(Usuario usuarioLogado) {
    // 1. Cria uma instância do nosso serviço
    VotacaoService votacaoService = new VotacaoService();

    // 2. Limpa a grade antes de adicionar novos cards
    this.removeAll(); // ou painelDaGrade.removeAll(); se você criou um painel específico

    // 3. Busca os resultados apurados
    List<Votacao> votacoesArquivadas = votacaoService.buscarVotacoesComResultadoDisponivel();

    // 4. Para cada votação arquivada, cria um card e o adiciona
   for (Votacao votacao : votacoesArquivadas) {
    CardView card = new CardView();
    card.setDados(votacao);
    card.setUsuario(usuarioLogado); // Passa o usuário para o card
    card.setTelaDeOrigem(this);
    card.setModo(DetalhesVotacaoDialog.ModoDialogo.RESULTADO); // << MODO DE RESULTADO
    this.add(card);
}

    // 5. "Redesenha" a tela para mostrar as mudanças
    this.revalidate();
    this.repaint();
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.GridLayout(0, 3, 15, 15));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

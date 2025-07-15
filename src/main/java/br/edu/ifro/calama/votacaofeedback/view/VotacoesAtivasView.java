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
public class VotacoesAtivasView extends javax.swing.JPanel {

    private final MenuPrincipalView menuPrincipal;
    private Usuario usuarioLogado;
    
    public VotacoesAtivasView(MenuPrincipalView menuPrincipal, Usuario usuario) {
        initComponents();
        this.menuPrincipal = menuPrincipal;
        this.usuarioLogado = usuario;
    }
    public void carregarVotacoes(Usuario usuarioLogado) {
    
    this.removeAll();

   
    VotacaoService votacaoService = new VotacaoService();

    try {
        
        List<Votacao> votacoesAtivas = votacaoService.buscarAtivasPorUsuario(usuarioLogado);

       
        for (Votacao votacao : votacoesAtivas) {
            CardView card = new CardView();
            card.setDados(votacao);
            card.setUsuario(usuarioLogado);
            card.setTelaDeOrigem(this); // Define o contexto
            card.setModo(DetalhesVotacaoDialog.ModoDialogo.PARTICIPACAO);
            this.add(card);
        }

    } catch (Exception e) {
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this,
            "Erro ao carregar as votações ativas.",
            "Erro de Conexão",
            javax.swing.JOptionPane.ERROR_MESSAGE);
    }

   
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

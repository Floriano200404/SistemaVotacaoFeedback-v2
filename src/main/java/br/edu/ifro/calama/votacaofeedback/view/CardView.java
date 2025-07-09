/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;
import br.edu.ifro.calama.votacaofeedback.model.Votacao;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
/**
 *
 * @author floriano
 */
public class CardView extends javax.swing.JPanel {
    private Votacao votacaoAtual;
   
    public CardView() {
        initComponents();
    }

public void setDados(Votacao votacao) {
      
    this.votacaoAtual = votacao; 

   
    lblTituloVotacao.setText(votacao.getTitulo());

   
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");

    
    if (votacao.getDataInicio() != null) {
        returdadosdt.setText(sdf.format(votacao.getDataInicio()));
    } else {
        returdadosdt.setText("N/A"); // Mostra "N/A" se a data for nula
    }

    if (votacao.getDataFim() != null) {
        returdadosdt2.setText(sdf.format(votacao.getDataFim()));
    } else {
        returdadosdt2.setText("N/A");
    }

    if (votacao.getDataResultado() != null) {
        returdadosdt3.setText(sdf.format(votacao.getDataResultado()));
    } else {
        returdadosdt3.setText("N/A");
    }

   
}
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblInstituicao = new javax.swing.JLabel();
        lblCampus = new javax.swing.JLabel();
        Local = new javax.swing.JLabel();
        lblTituloVotacao = new javax.swing.JLabel();
        dtinicio = new javax.swing.JLabel();
        returdadosdt = new javax.swing.JLabel();
        dtfim = new javax.swing.JLabel();
        returdadosdt2 = new javax.swing.JLabel();
        dtresult = new javax.swing.JLabel();
        returdadosdt3 = new javax.swing.JLabel();
        btnVerVotacao = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblInstituicao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/IF.png"))); // NOI18N
        add(lblInstituicao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 120, 100));

        lblCampus.setText("campus");
        add(lblCampus, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, -1, 20));

        Local.setText("Porto Velho Calama");
        add(Local, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, -1));

        lblTituloVotacao.setText("VOTAÇÃO PARA DIREÇÃO-GERAL");
        add(lblTituloVotacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, -1, -1));

        dtinicio.setText("Data inicio");
        add(dtinicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, -1, -1));

        returdadosdt.setText("resultData");
        add(returdadosdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, -1, -1));

        dtfim.setText("Data fim");
        add(dtfim, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, -1, -1));

        returdadosdt2.setText("resultDataF");
        add(returdadosdt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, -1, -1));

        dtresult.setText("Data Resultado");
        add(dtresult, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, -1, -1));

        returdadosdt3.setText("resultDataR");
        add(returdadosdt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, -1, -1));

        btnVerVotacao.setText("Ver Votação");
        btnVerVotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerVotacaoActionPerformed(evt);
            }
        });
        add(btnVerVotacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerVotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerVotacaoActionPerformed
                                              
    
    
    java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);

    
    AprovarVotacaoView telaDeAprovacao = (AprovarVotacaoView) javax.swing.SwingUtilities.getAncestorOfClass(AprovarVotacaoView.class, this);

    
    DetalhesVotacaoDialog dialog = new DetalhesVotacaoDialog((java.awt.Frame) parentWindow, true, telaDeAprovacao);

   
    dialog.setDados(this.votacaoAtual); 

    
    dialog.setLocationRelativeTo(parentWindow);
    dialog.setVisible(true);

    }//GEN-LAST:event_btnVerVotacaoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Local;
    private javax.swing.JButton btnVerVotacao;
    private javax.swing.JLabel dtfim;
    private javax.swing.JLabel dtinicio;
    private javax.swing.JLabel dtresult;
    private javax.swing.JLabel lblCampus;
    private javax.swing.JLabel lblInstituicao;
    private javax.swing.JLabel lblTituloVotacao;
    private javax.swing.JLabel returdadosdt;
    private javax.swing.JLabel returdadosdt2;
    private javax.swing.JLabel returdadosdt3;
    // End of variables declaration//GEN-END:variables

   
}

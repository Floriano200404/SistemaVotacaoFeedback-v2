/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;
import br.edu.ifro.calama.votacaofeedback.model.Votacao;
import java.text.SimpleDateFormat;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import javax.swing.JButton;

/**
 *
 * @author floriano
 */
    
public class CardView extends javax.swing.JPanel {
    private static class RoundedButton extends JButton {

        private int arcWidth = 20;
        private int arcHeight = 20;

        public RoundedButton(String text) {
            super(text);
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Define a cor de fundo com base no estado do botão
            if (getModel().isPressed()) {
                g2.setColor(getBackground().darker());
            } else if (getModel().isRollover()) {
                g2.setColor(getBackground().brighter());
            } else {
                g2.setColor(getBackground());
            }

            // Desenha o retângulo arredondado que servirá como fundo
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight));
            g2.dispose();

            // Pinta o texto ("Ver Votação") por cima do fundo que desenhamos
            super.paintComponent(g);
        }
    }
    // --- Fim da Lógica do Botão Arredondado ---
    
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
        btnVerVotacao = new RoundedButton("Ver Votação");
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblInstituicao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/WhatsApp Image 2025-07-10 at 00.50.52-Photoroom.png"))); // NOI18N
        add(lblInstituicao, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 100, 120));

        lblCampus.setBackground(new java.awt.Color(102, 102, 102));
        lblCampus.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        lblCampus.setForeground(new java.awt.Color(102, 102, 102));
        lblCampus.setText("Campus");
        add(lblCampus, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, -1, 20));

        Local.setBackground(new java.awt.Color(153, 153, 153));
        Local.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Local.setForeground(new java.awt.Color(102, 102, 102));
        Local.setText("Porto Velho Calama");
        add(Local, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, -1, -1));

        lblTituloVotacao.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTituloVotacao.setForeground(new java.awt.Color(0, 0, 0));
        lblTituloVotacao.setText("VOTAÇÃO PARA DIREÇÃO-GERAL ");
        add(lblTituloVotacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        dtinicio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dtinicio.setForeground(new java.awt.Color(153, 153, 153));
        dtinicio.setText("Data Início:");
        add(dtinicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 100, 20));

        returdadosdt.setForeground(new java.awt.Color(153, 153, 153));
        returdadosdt.setText("resultData");
        add(returdadosdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, -1, 20));

        dtfim.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dtfim.setForeground(new java.awt.Color(153, 153, 153));
        dtfim.setText("Data Fim:");
        add(dtfim, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, 10));

        returdadosdt2.setForeground(new java.awt.Color(153, 153, 153));
        returdadosdt2.setText("resultDataF");
        add(returdadosdt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, 10));

        dtresult.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dtresult.setForeground(new java.awt.Color(153, 153, 153));
        dtresult.setText("Data Resultado:");
        add(dtresult, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        returdadosdt3.setForeground(new java.awt.Color(153, 153, 153));
        returdadosdt3.setText("resultDataR");
        add(returdadosdt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, -1, -1));

        btnVerVotacao.setBackground(new java.awt.Color(0, 153, 204));
        btnVerVotacao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVerVotacao.setForeground(new java.awt.Color(255, 255, 255));
        btnVerVotacao.setText("Ver Votação");
        btnVerVotacao.setBorder(null);
        btnVerVotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerVotacaoActionPerformed(evt);
            }
        });
        add(btnVerVotacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 290, 30));

        jSeparator1.setForeground(new java.awt.Color(51, 153, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 10, 110));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 10, 308, 10));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 11, 20, 270));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 11, 10, 270));

        jSeparator5.setForeground(new java.awt.Color(102, 102, 102));
        add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 275, 10));

        jSeparator6.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 280, 308, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerVotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerVotacaoActionPerformed
                                              
    
    java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);

   
    DetalhesVotacaoDialog dialog = new DetalhesVotacaoDialog((java.awt.Frame) parentWindow, true);

    
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lblCampus;
    private javax.swing.JLabel lblInstituicao;
    private javax.swing.JLabel lblTituloVotacao;
    private javax.swing.JLabel returdadosdt;
    private javax.swing.JLabel returdadosdt2;
    private javax.swing.JLabel returdadosdt3;
    // End of variables declaration//GEN-END:variables
  
}

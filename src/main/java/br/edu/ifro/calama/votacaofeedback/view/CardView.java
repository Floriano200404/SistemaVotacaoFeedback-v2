/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;

import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.model.Votacao;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author floriano, Athos
 */
    public class CardView extends javax.swing.JPanel {
        private Votacao votacaoAtual;
        private DetalhesVotacaoDialog.ModoDialogo modo;
        private Usuario usuarioLogado;
        private java.awt.Component telaDeOrigem;
        private javax.swing.JTextArea tituloTextArea;
     
        public CardView() {
            initComponents();
            this.setOpaque(false);
            this.modo = DetalhesVotacaoDialog.ModoDialogo.APROVACAO;
        }

        public void setTelaDeOrigem(java.awt.Component tela) {
            this.telaDeOrigem = tela;
        }
        
        public void setModo(DetalhesVotacaoDialog.ModoDialogo modo) {
            this.modo = modo;
        }

        public void setUsuario(Usuario usuario) {
            this.usuarioLogado = usuario;
        }

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

            if (getModel().isPressed()) {
                g2.setColor(getBackground().darker());
            } else if (getModel().isRollover()) {
                g2.setColor(getBackground().brighter());
            } else {
                g2.setColor(getBackground());
            }

            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight));
            g2.dispose();
            super.paintComponent(g);
        }
    }
    
    class ShadowPanel extends JPanel {
        private int shadowSize = 5;
        private float shadowOpacity = 0.4f;
        private int arc = 20;

        public ShadowPanel() {
            super();
            setOpaque(false);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            // Desenha os componentes filhos (labels, etc.) por baixo
            super.paintComponent(g); 
            
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            int width = getWidth();
            int height = getHeight();

            // Desenha a sombra
            for (int i = 0; i < shadowSize; i++) {
                g2.setColor(new Color(180, 180, 180, (int) (shadowOpacity * 255 * (1.0f - (float) i / shadowSize))));
                g2.fillRoundRect(i, i, width - i * 2, height - i * 2, arc, arc);
            }

            // Desenha o painel principal
            g2.setColor(getBackground());
            g2.fillRoundRect(shadowSize, shadowSize, width - shadowSize * 2, height - shadowSize * 2, arc, arc);

            g2.dispose();
        }

        @Override
        public java.awt.Insets getInsets() {
            return new java.awt.Insets(shadowSize, shadowSize, shadowSize, shadowSize);
        }
    }
    
    private void inicializarTituloCustomizado() {
        java.awt.Rectangle bounds = lblTituloVotacao.getBounds();
        java.awt.Font fonte = lblTituloVotacao.getFont();
        java.awt.Color corTexto = lblTituloVotacao.getForeground();
        java.awt.Container pai = lblTituloVotacao.getParent();

        lblTituloVotacao.setVisible(false);

        tituloTextArea = new javax.swing.JTextArea();
        tituloTextArea.setFont(fonte);
        tituloTextArea.setForeground(corTexto);
        tituloTextArea.setEditable(false);
        tituloTextArea.setFocusable(false);
        tituloTextArea.setOpaque(false);
        tituloTextArea.setBorder(null);
        tituloTextArea.setLineWrap(true);
        tituloTextArea.setWrapStyleWord(true);

        if (pai != null) {
            pai.add(tituloTextArea);
            tituloTextArea.setBounds(bounds);
        }
    }

        public void setDados(Votacao votacao) {

            this.votacaoAtual = votacao; 

           lblTituloVotacao.setText(votacao.getTitulo());

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");

            if (votacao.getDataInicial() != null) {
                returdadosdt.setText(sdf.format(votacao.getDataInicial()));
            } else {
                returdadosdt.setText("N/A");
            }

            if (votacao.getDataFinal() != null) {
                returdadosdt2.setText(sdf.format(votacao.getDataFinal()));
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

        jPanel1 = new ShadowPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        btnVerVotacao = new RoundedButton("Ver Votação");
        returdadosdt3 = new javax.swing.JLabel();
        dtresult = new javax.swing.JLabel();
        returdadosdt2 = new javax.swing.JLabel();
        dtfim = new javax.swing.JLabel();
        dtinicio = new javax.swing.JLabel();
        returdadosdt = new javax.swing.JLabel();
        Local = new javax.swing.JLabel();
        lblCampus = new javax.swing.JLabel();
        lblInstituicao = new javax.swing.JLabel();
        lblTituloVotacao = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator5.setForeground(new java.awt.Color(102, 102, 102));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 275, 10));

        jSeparator1.setForeground(new java.awt.Color(51, 153, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 10, 100));

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
        jPanel1.add(btnVerVotacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 290, 30));

        returdadosdt3.setForeground(new java.awt.Color(153, 153, 153));
        returdadosdt3.setText("resultDataR");
        jPanel1.add(returdadosdt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, -1, -1));

        dtresult.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dtresult.setForeground(new java.awt.Color(153, 153, 153));
        dtresult.setText("Data Resultado:");
        jPanel1.add(dtresult, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        returdadosdt2.setForeground(new java.awt.Color(153, 153, 153));
        returdadosdt2.setText("resultDataF");
        jPanel1.add(returdadosdt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, 10));

        dtfim.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dtfim.setForeground(new java.awt.Color(153, 153, 153));
        dtfim.setText("Data Fim:");
        jPanel1.add(dtfim, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, 10));

        dtinicio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dtinicio.setForeground(new java.awt.Color(153, 153, 153));
        dtinicio.setText("Data Início:");
        jPanel1.add(dtinicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 100, 20));

        returdadosdt.setForeground(new java.awt.Color(153, 153, 153));
        returdadosdt.setText("resultData");
        jPanel1.add(returdadosdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, -1, 20));

        Local.setBackground(new java.awt.Color(153, 153, 153));
        Local.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Local.setForeground(new java.awt.Color(102, 102, 102));
        Local.setText("Porto Velho Calama");
        jPanel1.add(Local, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, -1, -1));

        lblCampus.setBackground(new java.awt.Color(102, 102, 102));
        lblCampus.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        lblCampus.setForeground(new java.awt.Color(102, 102, 102));
        lblCampus.setText("Campus");
        jPanel1.add(lblCampus, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, -1, 20));

        lblInstituicao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/WhatsApp Image 2025-07-10 at 00.50.52-Photoroom.png"))); // NOI18N
        jPanel1.add(lblInstituicao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 100, 120));

        lblTituloVotacao.setEditable(false);
        lblTituloVotacao.setColumns(20);
        lblTituloVotacao.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTituloVotacao.setForeground(new java.awt.Color(0, 0, 0));
        lblTituloVotacao.setLineWrap(true);
        lblTituloVotacao.setRows(5);
        lblTituloVotacao.setWrapStyleWord(true);
        lblTituloVotacao.setBorder(null);
        lblTituloVotacao.setFocusable(false);
        lblTituloVotacao.setOpaque(false);
        jPanel1.add(lblTituloVotacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 280, 50));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 2, 310, 280));
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerVotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerVotacaoActionPerformed

   
    java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);

    
    DetalhesVotacaoDialog dialog = new DetalhesVotacaoDialog(
        (java.awt.Frame) parentWindow, 
        true, 
        this.usuarioLogado,
        this 
    );

    
    dialog.setDados(this.votacaoAtual);

    
    dialog.setModo(this.modo); 

    dialog.setLocationRelativeTo(parentWindow);
    dialog.setVisible(true);
    }//GEN-LAST:event_btnVerVotacaoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Local;
    private javax.swing.JButton btnVerVotacao;
    private javax.swing.JLabel dtfim;
    private javax.swing.JLabel dtinicio;
    private javax.swing.JLabel dtresult;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lblCampus;
    private javax.swing.JLabel lblInstituicao;
    private javax.swing.JTextArea lblTituloVotacao;
    private javax.swing.JLabel returdadosdt;
    private javax.swing.JLabel returdadosdt2;
    private javax.swing.JLabel returdadosdt3;
    // End of variables declaration//GEN-END:variables
 
}


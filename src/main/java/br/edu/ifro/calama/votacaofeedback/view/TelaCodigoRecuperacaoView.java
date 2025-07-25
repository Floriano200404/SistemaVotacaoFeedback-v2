/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import br.edu.ifro.calama.votacaofeedback.controller.RecuperacaoSenhaController; 
import br.edu.ifro.calama.votacaofeedback.util.ToastUtil;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author manoe
 */
public class TelaCodigoRecuperacaoView extends javax.swing.JFrame {
    
    private String email;
    
    public TelaCodigoRecuperacaoView(String email) {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.email = email;
    }
    
    public void exibirMensagem(String mensagem) {
        ToastUtil toast = new ToastUtil(this, mensagem, ToastUtil.ToastType.ERROR, ToastUtil.ToastPosition.TOP_RIGHT);
        toast.display();
    }

    public void exibirMensagemDeSucesso(String mensagem) {
        ToastUtil toast = new ToastUtil(this, mensagem, ToastUtil.ToastType.SUCCESS, ToastUtil.ToastPosition.TOP_RIGHT);
        toast.display();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabIF1 = new javax.swing.JLabel();
        jPanelFundoPrincipal = new GradientPanel();
        jLabInfo = new javax.swing.JLabel();
        jLabIF2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        botão = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        jLabIF1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabIF1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/TEXTO IF.png"))); // NOI18N
        jLabIF1.setMaximumSize(new java.awt.Dimension(100, 160));

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(9, 32, 63));

        jPanelFundoPrincipal.setBackground(new java.awt.Color(9, 32, 63));
        jPanelFundoPrincipal.setPreferredSize(new java.awt.Dimension(1507, 720));
        jPanelFundoPrincipal.setLayout(new java.awt.GridBagLayout());

        jLabInfo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/img-Photoroom.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(61, 113, 0, 143);
        jPanelFundoPrincipal.add(jLabInfo, gridBagConstraints);

        jLabIF2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabIF2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/TEXTO IF.png"))); // NOI18N
        jLabIF2.setMaximumSize(new java.awt.Dimension(100, 160));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 42;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(176, 12, 0, 143);
        jPanelFundoPrincipal.add(jLabIF2, gridBagConstraints);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/IF.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = -15;
        gridBagConstraints.ipady = -22;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(176, 97, 0, 0);
        jPanelFundoPrincipal.add(jLabel2, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(11, 41, 81));
        jPanel1.setPreferredSize(new java.awt.Dimension(436, 489));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Código:");
        jLabel1.setToolTipText("");

        jButton1.setBackground(new java.awt.Color(0, 153, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("ENVIAR CÓDIGO");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        botão.setBackground(new java.awt.Color(153, 153, 153));
        botão.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botão.setForeground(new java.awt.Color(255, 255, 255));
        botão.setText("VOLTAR");
        botão.setBorderPainted(false);
        botão.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botãoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("CÓDIGO DE RECUPERAÇÃO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel3))
                    .addComponent(botão, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel3)
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(botão, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipadx = 34;
        gridBagConstraints.ipady = 93;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(150, 174, 150, 0);
        jPanelFundoPrincipal.add(jPanel1, gridBagConstraints);

        getContentPane().add(jPanelFundoPrincipal, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void botãoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botãoActionPerformed
        new LoginView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botãoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String codigoDigitado = jTextField2.getText().trim();
        if (codigoDigitado.isEmpty()) {
            exibirMensagem("Por favor, digite o código de verificação.");
            return;
        }

        try {
            RecuperacaoSenhaController controller = new RecuperacaoSenhaController();
            boolean codigoEhValido = controller.verificarToken(this.email, codigoDigitado);

            if (codigoEhValido) {
                exibirMensagemDeSucesso("Código verificado com sucesso!");
                jButton1.setEnabled(false);
                new javax.swing.Timer(1500, e -> {
                    new AlterarSenhaView(this.email).setVisible(true);
                    this.dispose();
                }){{setRepeats(false);}}.start();
            } else {
                exibirMensagem("Código inválido ou expirado. Tente novamente.");
            }
        } catch (Exception e) {
            exibirMensagem("Ocorreu um erro ao verificar o código.");
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botão;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabIF1;
    private javax.swing.JLabel jLabIF2;
    private javax.swing.JLabel jLabInfo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelFundoPrincipal;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    private class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            Color color1 = new Color(9, 32, 63);  
            Color color2 = new Color(0, 0, 0);  

            int pontoTransicaoX = (int) (width * 0.25);

            g2d.setColor(color1);
            g2d.fillRect(0, 0, pontoTransicaoX, height);
            
            GradientPaint gp = new GradientPaint(pontoTransicaoX, 0, color1, width, 0, color2);
            g2d.setPaint(gp);
            g2d.fillRect(pontoTransicaoX, 0, width - pontoTransicaoX, height);
        }
    }

    private class RoundedButton extends JButton {
        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
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
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            super.paintComponent(g);
            g2.dispose();
        }
    }

    private class RoundedTextField extends JTextField {
        public RoundedTextField() {
            setOpaque(false);
            setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Padding interno
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
            super.paintComponent(g);
            g2.dispose();
        }
    }

}

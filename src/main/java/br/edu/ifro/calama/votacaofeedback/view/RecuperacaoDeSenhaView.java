/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;

import br.edu.ifro.calama.votacaofeedback.controller.RecuperacaoSenhaController;
import br.edu.ifro.calama.votacaofeedback.util.ToastUtil;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
/**
 *
 * @author USER
 */
public class RecuperacaoDeSenhaView extends javax.swing.JFrame {

    private String email;
    
    public RecuperacaoDeSenhaView() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); 
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

        jPanelFundoPrincipal = new GradientPanel();
        jPanelCaixaTexto = new javax.swing.JPanel();
        bntEnviarEmail = new RoundedButton("ENVIAR E-MAIL");
        txtEmail = new RoundedTextField(20);
        jLaRelembrarSenha = new javax.swing.JLabel();
        jLabEmail = new javax.swing.JLabel();
        bntVoltar = new RoundedButton("VOLTAR");
        jLabOrientacoes = new javax.swing.JLabel();
        jLabIF = new javax.swing.JLabel();
        jLabLogo = new javax.swing.JLabel();
        jLabInfo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanelFundoPrincipal.setBackground(new java.awt.Color(9, 32, 63));
        jPanelFundoPrincipal.setLayout(new java.awt.GridBagLayout());

        jPanelCaixaTexto.setBackground(new java.awt.Color(11, 41, 81));

        bntEnviarEmail.setBackground(new java.awt.Color(0, 153, 255));
        bntEnviarEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bntEnviarEmail.setForeground(new java.awt.Color(255, 255, 255));
        bntEnviarEmail.setText("ENVIAR E-MAIL");
        bntEnviarEmail.setBorder(null);
        bntEnviarEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntEnviarEmailActionPerformed(evt);
            }
        });

        txtEmail.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 8, 4, 15));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        jLaRelembrarSenha.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLaRelembrarSenha.setForeground(new java.awt.Color(255, 255, 255));
        jLaRelembrarSenha.setText("RELEMBRAR A SENHA");
        jLaRelembrarSenha.setName(""); // NOI18N

        jLabEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabEmail.setForeground(new java.awt.Color(255, 255, 255));
        jLabEmail.setText("E-mail:");

        bntVoltar.setBackground(new java.awt.Color(153, 153, 153));
        bntVoltar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bntVoltar.setForeground(new java.awt.Color(255, 255, 255));
        bntVoltar.setText("VOLTAR");
        bntVoltar.setBorder(null);
        bntVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntVoltarActionPerformed(evt);
            }
        });

        jLabOrientacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ori-Photoroom.png"))); // NOI18N

        javax.swing.GroupLayout jPanelCaixaTextoLayout = new javax.swing.GroupLayout(jPanelCaixaTexto);
        jPanelCaixaTexto.setLayout(jPanelCaixaTextoLayout);
        jPanelCaixaTextoLayout.setHorizontalGroup(
            jPanelCaixaTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaixaTextoLayout.createSequentialGroup()
                .addGroup(jPanelCaixaTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCaixaTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelCaixaTextoLayout.createSequentialGroup()
                            .addGap(51, 51, 51)
                            .addGroup(jPanelCaixaTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabEmail)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bntEnviarEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabOrientacoes)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCaixaTextoLayout.createSequentialGroup()
                            .addGap(45, 45, 45)
                            .addComponent(bntVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelCaixaTextoLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jLaRelembrarSenha)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanelCaixaTextoLayout.setVerticalGroup(
            jPanelCaixaTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCaixaTextoLayout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addComponent(jLaRelembrarSenha)
                .addGap(54, 54, 54)
                .addComponent(jLabEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bntEnviarEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabOrientacoes)
                .addGap(43, 43, 43)
                .addComponent(bntVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 60;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(170, 175, 151, 0);
        jPanelFundoPrincipal.add(jPanelCaixaTexto, gridBagConstraints);

        jLabIF.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabIF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/TEXTO IF.png"))); // NOI18N
        jLabIF.setMaximumSize(new java.awt.Dimension(100, 160));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 116;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(170, 6, 0, 0);
        jPanelFundoPrincipal.add(jLabIF, gridBagConstraints);

        jLabLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/IF.png"))); // NOI18N
        jLabLogo.setMaximumSize(new java.awt.Dimension(203, 230));
        jLabLogo.setMinimumSize(new java.awt.Dimension(203, 230));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = -15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(170, 67, 0, 0);
        jPanelFundoPrincipal.add(jLabLogo, gridBagConstraints);

        jLabInfo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/img-Photoroom.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 81, 0, 161);
        jPanelFundoPrincipal.add(jLabInfo, gridBagConstraints);

        getContentPane().add(jPanelFundoPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        bntEnviarEmail.doClick();                                       
    }//GEN-LAST:event_txtEmailActionPerformed

    private void bntEnviarEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEnviarEmailActionPerformed
        String email = txtEmail.getText().trim();
        if (email.isEmpty()) {
            exibirMensagem("Por favor, insira seu e-mail.");
            return;
        }
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            exibirMensagem("Formato de e-mail inválido.");
            return;
        }
        bntEnviarEmail.setEnabled(false);
        bntEnviarEmail.setText("ENVIANDO...");
        new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                return new RecuperacaoSenhaController().iniciarRecuperacao(email);
            }

            @Override
            protected void done() {
                try {
                    boolean emailFoiEncontrado = get();
                    if (emailFoiEncontrado) {
                        exibirMensagemDeSucesso("Código de recuperação enviado!");
                        new javax.swing.Timer(1500, e -> {
                            new TelaCodigoRecuperacaoView(email).setVisible(true);
                            dispose();
                        }){{setRepeats(false);}}.start();
                    } else {
                        exibirMensagem("E-mail não cadastrado no sistema.");
                        bntEnviarEmail.setText("ENVIAR E-MAIL");
                        bntEnviarEmail.setEnabled(true);
                    }
                } catch (Exception e) {
                    exibirMensagem("Erro ao tentar conectar com o serviço.");
                    e.printStackTrace();
                    bntEnviarEmail.setText("ENVIAR E-MAIL");
                    bntEnviarEmail.setEnabled(true);
                }
            }
        }.execute();
    }//GEN-LAST:event_bntEnviarEmailActionPerformed

    private void bntVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntVoltarActionPerformed
        LoginView telaLogin = new LoginView();
        telaLogin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bntVoltarActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntEnviarEmail;
    private javax.swing.JButton bntVoltar;
    private javax.swing.JLabel jLaRelembrarSenha;
    private javax.swing.JLabel jLabEmail;
    private javax.swing.JLabel jLabIF;
    private javax.swing.JLabel jLabInfo;
    private javax.swing.JLabel jLabLogo;
    private javax.swing.JLabel jLabOrientacoes;
    private javax.swing.JPanel jPanelCaixaTexto;
    private javax.swing.JPanel jPanelFundoPrincipal;
    private javax.swing.JTextField txtEmail;
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
    private int cornerRadius = 15;

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
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        super.paintComponent(g);

        g2.dispose();
    }
}

private class RoundedTextField extends JTextField {
    private Shape shape;
    private int cornerRadius = 15;

    public RoundedTextField(int size) {
        super(size);
        setOpaque(false); 
        setBorder(new RoundedBorder(cornerRadius)); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        super.paintComponent(g);
        g2.dispose();
    }
    
    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        }
        return shape.contains(x, y);
    }
    
    private class RoundedBorder extends AbstractBorder {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(4, 15, 4, 15);
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        }
    }
}  
}


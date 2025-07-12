/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;

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
import javax.swing.JTextField;
/**
 *
 * @author USER
 */
public class RecuperacaoDeSenhaView extends javax.swing.JFrame {

    public RecuperacaoDeSenhaView() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void enviarEmailRecuperacao() {
        String email = txtEmail.getText().trim();

        // 1. Validações do e-mail
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira seu endereço de e-mail.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            JOptionPane.showMessageDialog(this, "Formato de e-mail inválido. Por favor, verifique.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Prepara a interface para o envio
        bntEnviarEmail.setEnabled(false);
        bntEnviarEmail.setText("ENVIANDO...");

        // 3. Executa a tarefa demorada em segundo plano
        EmailSenderWorker worker = new EmailSenderWorker(email);
        worker.execute();
    }
    

    private boolean simularEnvioDeEmail(String email) {
        // Em uma aplicação real, aqui você chamaria sua API ou serviço de e-mail.
        // Simula uma falha para um e-mail específico para teste.
        return !email.equalsIgnoreCase("erro@teste.com");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
                    .addGroup(jPanelCaixaTextoLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLaRelembrarSenha))
                    .addGroup(jPanelCaixaTextoLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanelCaixaTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bntVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelCaixaTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabEmail)
                                .addComponent(bntEnviarEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabOrientacoes)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanelCaixaTextoLayout.setVerticalGroup(
            jPanelCaixaTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCaixaTextoLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLaRelembrarSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jLabEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bntEnviarEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabOrientacoes)
                .addGap(56, 56, 56)
                .addComponent(bntVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        jLabIF.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabIF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/TEXTO IF.png"))); // NOI18N
        jLabIF.setMaximumSize(new java.awt.Dimension(100, 160));

        jLabLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/IF.png"))); // NOI18N
        jLabLogo.setMaximumSize(new java.awt.Dimension(203, 230));
        jLabLogo.setMinimumSize(new java.awt.Dimension(203, 230));

        jLabInfo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/img-Photoroom.png"))); // NOI18N

        javax.swing.GroupLayout jPanelFundoPrincipalLayout = new javax.swing.GroupLayout(jPanelFundoPrincipal);
        jPanelFundoPrincipal.setLayout(jPanelFundoPrincipalLayout);
        jPanelFundoPrincipalLayout.setHorizontalGroup(
            jPanelFundoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFundoPrincipalLayout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jPanelCaixaTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(jPanelFundoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelFundoPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabIF, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        jPanelFundoPrincipalLayout.setVerticalGroup(
            jPanelFundoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFundoPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelFundoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFundoPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabIF, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)))
                .addComponent(jLabInfo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelFundoPrincipalLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jPanelCaixaTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelFundoPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
                enviarEmailRecuperacao();
                                            
    }//GEN-LAST:event_txtEmailActionPerformed

    private void bntEnviarEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEnviarEmailActionPerformed
              enviarEmailRecuperacao();
    }//GEN-LAST:event_bntEnviarEmailActionPerformed

    private void bntVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntVoltarActionPerformed
        
        LoginView telaLogin = new LoginView();
        
        telaLogin.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_bntVoltarActionPerformed
    

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) { // Usar um L&F mais moderno
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(RecuperacaoDeSenhaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecuperacaoDeSenhaView().setVisible(true);
            }
        });
    }

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
            Color color2 = new Color(21, 52, 98);
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }
  private class EmailSenderWorker extends SwingWorker<Boolean, Void> {
        private final String email;

        public EmailSenderWorker(String email) {
            this.email = email;
        }

        @Override
        protected Boolean doInBackground() throws Exception {
            try {
                Thread.sleep(2000); // Simula espera de 2 segundos
            } catch (InterruptedException e) {
                // Tratamento de exceção
            }
            return simularEnvioDeEmail(email);
        }

        @Override
        protected void done() {
            try {
                boolean emailEnviadoComSucesso = get();
                if (emailEnviadoComSucesso) {
                    JOptionPane.showMessageDialog(RecuperacaoDeSenhaView.this,
                            "Um e-mail com as instruções de recuperação foi enviado para:\n" + email,
                            "E-mail Enviado!", JOptionPane.INFORMATION_MESSAGE);
                    txtEmail.setText("");
                } else {
                    JOptionPane.showMessageDialog(RecuperacaoDeSenhaView.this,
                            "Não foi possível enviar o e-mail de recuperação.",
                            "Erro ao Enviar E-mail", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(RecuperacaoDeSenhaView.this, "Ocorreu um erro inesperado.", "Erro", JOptionPane.ERROR_MESSAGE);
            } finally {
                bntEnviarEmail.setText("ENVIAR E-MAIL");
                bntEnviarEmail.setEnabled(true);
            }
        }
    }
  private class RoundedButton extends JButton {
    private int cornerRadius = 15; // Raio dos cantos

    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false); // Não pinta a área de conteúdo padrão
        setFocusPainted(false);      // Não pinta o foco
        setBorderPainted(false);     // Não pinta a borda
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Define a cor de fundo baseada no estado do botão (pressionado, hover, normal)
        if (getModel().isPressed()) {
            g2.setColor(getBackground().darker());
        } else if (getModel().isRollover()) {
            g2.setColor(getBackground().brighter());
        } else {
            g2.setColor(getBackground());
        }

        // Desenha o retângulo com cantos arredondados
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // Deixa o método pai pintar o texto (label) do botão por cima
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
    
    // Este método ajuda a definir a área de colisão do componente
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


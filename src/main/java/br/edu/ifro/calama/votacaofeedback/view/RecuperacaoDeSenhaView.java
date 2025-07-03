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

/**
 *
 * @author USER
 */
public class RecuperacaoDeSenhaView extends javax.swing.JFrame {

    /**
     * Creates new form RecuperacaoDeSenhaView
     */
    public RecuperacaoDeSenhaView() {
        initComponents();
        setLocationRelativeTo(null);
    }

        /**
     * Inicia o processo de validação e envio de e-mail com feedback visual.
     */
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
    
    /**
     * Simula a lógica de negócio de enviar um e-mail.
     * @param email O e-mail para o qual tentar enviar.
     * @return true se a simulação for bem-sucedida, false caso contrário.
     */
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
        bntEnviarEmail = new javax.swing.JButton();
        txtEmail = new javax.swing.JTextField();
        jLaRelembrarSenha = new javax.swing.JLabel();
        jLabEmail = new javax.swing.JLabel();
        bntVoltar = new javax.swing.JButton();
        jLabOrientacoes = new javax.swing.JLabel();
        jLabIF = new javax.swing.JLabel();
        jLabLogo = new javax.swing.JLabel();
        jLabInfo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanelFundoPrincipal.setBackground(new java.awt.Color(9, 32, 63));

        jPanelCaixaTexto.setBackground(new java.awt.Color(11, 41, 81));

        bntEnviarEmail.setBackground(new java.awt.Color(0, 149, 255));
        bntEnviarEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bntEnviarEmail.setForeground(new java.awt.Color(255, 255, 255));
        bntEnviarEmail.setText("ENVIAR E-MAIL");
        bntEnviarEmail.setBorder(null);
        bntEnviarEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntEnviarEmailActionPerformed(evt);
            }
        });

        txtEmail.setBorder(null);
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        jLaRelembrarSenha.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLaRelembrarSenha.setForeground(new java.awt.Color(255, 255, 255));
        jLaRelembrarSenha.setText("RELEMBRAR SENHA");
        jLaRelembrarSenha.setName(""); // NOI18N

        jLabEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabEmail.setForeground(new java.awt.Color(255, 255, 255));
        jLabEmail.setText("E-mail:");

        bntVoltar.setBackground(new java.awt.Color(153, 153, 153));
        bntVoltar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bntVoltar.setForeground(new java.awt.Color(255, 255, 255));
        bntVoltar.setText("VOLTAR");
        bntVoltar.setBorder(null);

        jLabOrientacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ori-Photoroom.png"))); // NOI18N

        javax.swing.GroupLayout jPanelCaixaTextoLayout = new javax.swing.GroupLayout(jPanelCaixaTexto);
        jPanelCaixaTexto.setLayout(jPanelCaixaTextoLayout);
        jPanelCaixaTextoLayout.setHorizontalGroup(
            jPanelCaixaTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaixaTextoLayout.createSequentialGroup()
                .addGroup(jPanelCaixaTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCaixaTextoLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanelCaixaTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bntVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelCaixaTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabEmail)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bntEnviarEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabOrientacoes))))
                    .addGroup(jPanelCaixaTextoLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLaRelembrarSenha)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanelCaixaTextoLayout.setVerticalGroup(
            jPanelCaixaTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCaixaTextoLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLaRelembrarSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jLabEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bntEnviarEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabOrientacoes)
                .addGap(54, 54, 54)
                .addComponent(bntVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        jLabIF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/TEXTO IF.png"))); // NOI18N
        jLabIF.setMaximumSize(new java.awt.Dimension(100, 160));

        jLabLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/IF.png"))); // NOI18N
        jLabLogo.setMaximumSize(new java.awt.Dimension(203, 230));
        jLabLogo.setMinimumSize(new java.awt.Dimension(203, 230));

        jLabInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/img-Photoroom.png"))); // NOI18N

        javax.swing.GroupLayout jPanelFundoPrincipalLayout = new javax.swing.GroupLayout(jPanelFundoPrincipal);
        jPanelFundoPrincipal.setLayout(jPanelFundoPrincipalLayout);
        jPanelFundoPrincipalLayout.setHorizontalGroup(
            jPanelFundoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFundoPrincipalLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jPanelCaixaTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelFundoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFundoPrincipalLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabIF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(9, Short.MAX_VALUE))
                    .addGroup(jPanelFundoPrincipalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanelFundoPrincipalLayout.setVerticalGroup(
            jPanelFundoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFundoPrincipalLayout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addGroup(jPanelFundoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelCaixaTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelFundoPrincipalLayout.createSequentialGroup()
                        .addGroup(jPanelFundoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabIF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabInfo)))
                .addGap(77, 77, 77))
        );

        getContentPane().add(jPanelFundoPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
                enviarEmailRecuperacao();
    //GEN-LAST:event_txtEmailActionPerformed
    }//GEN-LAST:event_txtEmailActionPerformed

    private void bntEnviarEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEnviarEmailActionPerformed
              enviarEmailRecuperacao();
    }//GEN-LAST:event_bntEnviarEmailActionPerformed

    private void bntVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntVoltarActionPerformed
        // Funcionalidade do Botão Voltar: fecha esta janela
        this.dispose();
    }//GEN-LAST:event_bntVoltarActionPerformed
    
    /**
     * @param args the command line arguments
     */
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

        /* Create and display the form */
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
}


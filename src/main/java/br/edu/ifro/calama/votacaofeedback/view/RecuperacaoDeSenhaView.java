/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;

import javax.swing.JOptionPane;
import java.awt.Component;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 *
 * @author USER
 */


public class RecuperacaoDeSenhaView extends javax.swing.JPanel {

private javax.swing.JPanel painelDeCartoes;
private java.awt.CardLayout gerenciadorDeLayouts;

    /**
     * Creates new form RecuperacaoDeSenhaView
     */
    public RecuperacaoDeSenhaView() {
        initComponents();
        addCustomEventListeners(); // Chamar o método para adicionar listeners
    }
public RecuperacaoDeSenhaView(javax.swing.JPanel painelDeCartoes, java.awt.CardLayout gerenciadorDeLayouts) {

    this(); 

    this.painelDeCartoes = painelDeCartoes;
    this.gerenciadorDeLayouts = gerenciadorDeLayouts; 

    }
    
    private void addCustomEventListeners() {
        // Adicionar ActionListener para o btnVoltar
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt); // Chama o método para o botão Voltar
            }
        });

        // Opcional: Você pode adicionar um ActionListener para o txtEmail aqui
        // se quiser que algo aconteça quando o usuário pressionar Enter no campo de e-mail.
        // Se a funcionalidade principal de "enviar e-mail" já está no botão, talvez não precise aqui.
        // Se precisar, descomente e use:
        // txtEmail.addActionListener(new java.awt.event.ActionListener() {
        //     public void actionPerformed(java.awt.event.ActionEvent evt) {
        //         enviarEmailRecuperacao(); // Envia o e-mail ao pressionar Enter
        //     }
        // });
    }

    /**
     * Lógica para enviar o e-mail de recuperação.
     * Este método é chamado quando btnEnviarEmail é clicado.
     */
    private void enviarEmailRecuperacao() {
        // Obter o texto do JTextField
        String email = txtEmail.getText().trim(); // Pega o texto e remove espaços em branco

        // 1. Validação Simples do E-mail (se está vazio e um formato básico)
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, // Usar 'this' (o JPanel) como pai da mensagem
                    "Por favor, insira seu endereço de e-mail.",
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE);
            return; // Sai do método se o e-mail estiver vazio
        }

        // Validação de formato de e-mail (regex simples)
        // Permite caracteres alfanuméricos, ponto, hífen no nome de usuário e domínio.
        // E termina com 2 a 6 letras para a extensão (ex: .com, .br, .org).
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            JOptionPane.showMessageDialog(this,
                    "Formato de e-mail inválido. Por favor, verifique.",
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE);
            return; // Sai do método se o formato for inválido
        }

        // 2. Simulação de Envio de E-mail (Lógica de Negócio Real aqui)
        // ATENÇÃO: Esta é apenas uma simulação.
        // Em uma aplicação real, você faria:
        // - Chamada a um serviço/API para enviar o e-mail.
        // - Verificação no banco de dados se o e-mail existe.
        // - Geração de um token de recuperação e armazenamento.
        // - Envio do e-mail real com o link do token.

        boolean emailEnviadoComSucesso = simularEnvioEmail(email); // Chame sua lógica de backend aqui

        // 3. Exibir Mensagem de Sucesso ou Erro
        if (emailEnviadoComSucesso) {
            JOptionPane.showMessageDialog(this,
                    "Um e-mail com as instruções de recuperação foi enviado para:\n" + email +
                    "\nVerifique sua caixa de entrada, incluindo a pasta de SPAM.",
                    "E-mail Enviado!",
                    JOptionPane.INFORMATION_MESSAGE);
            // Opcional: Limpar o campo de e-mail após o sucesso
            txtEmail.setText(""); // Limpa o JTextField
        } else {
            // Este erro pode ser devido a: e-mail não encontrado no sistema, falha no servidor de e-mail, etc.
            JOptionPane.showMessageDialog(this,
                    "Não foi possível enviar o e-mail de recuperação. Por favor, tente novamente." +
                    "\nSe o problema persistir, entre em contato com o suporte.",
                    "Erro ao Enviar E-mail",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Simula o envio de e-mail. Em uma aplicação real, isso seria uma chamada a um serviço.
     * @param email O e-mail para o qual tentar enviar.
     * @return true se a simulação for bem-sucedida, false caso contrário.
     */
    private boolean simularEnvioEmail(String email) {
        // Exemplo: Simula falha para um e-mail específico ou se for muito curto
        if (email.equalsIgnoreCase("erro@teste.com") || email.length() < 10) {
            return false; // Simula um erro
        }
        // Simula sucesso na maioria dos casos
        return true;
    }

    /**
     * Lógica para voltar para a tela anterior (ex: tela de Login).
     * Este método é chamado quando btnVoltar é clicado.
     */
    private void voltarParaTelaAnterior() {
        System.out.println("Botão Voltar clicado. Lógica para navegar para a tela anterior precisa ser implementada aqui.");
    }
        
    private class jPanelGradient extends javax.swing.JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // Linha importante!
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        pnlFundoPrincipal = new jPanelGradient();
        pnlCaixaFundo = new javax.swing.JPanel();
        lblTituloTela = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnEnviarEmail = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        lblOrientacoes = new javax.swing.JLabel();
        lblLogoIF = new javax.swing.JLabel();
        lblIF = new javax.swing.JLabel();
        lblInformativo = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setName("pnlFundoPrincipal"); // NOI18N

        pnlFundoPrincipal.setBackground(new java.awt.Color(0, 0, 51));
        pnlFundoPrincipal.setForeground(new java.awt.Color(0, 0, 51));

        pnlCaixaFundo.setBackground(new java.awt.Color(0, 0, 153));

        lblTituloTela.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTituloTela.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloTela.setText("RELEMBRAR A SENHA");

        lblEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail.setText("E-mail");

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        btnEnviarEmail.setBackground(new java.awt.Color(0, 102, 255));
        btnEnviarEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEnviarEmail.setForeground(new java.awt.Color(255, 255, 255));
        btnEnviarEmail.setBorder(null);
        btnEnviarEmail.setLabel("ENVIAR E-MAIL");
        btnEnviarEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarEmailActionPerformed(evt);
            }
        });

        btnVoltar.setBackground(new java.awt.Color(153, 153, 153));
        btnVoltar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVoltar.setForeground(new java.awt.Color(255, 255, 255));
        btnVoltar.setBorder(null);
        btnVoltar.setLabel("VOLTAR");
        btnVoltar.setName("btnVoltar"); // NOI18N

        lblOrientacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ori-Photoroom.png"))); // NOI18N
        lblOrientacoes.setName("lblOrientacoes"); // NOI18N

        javax.swing.GroupLayout pnlCaixaFundoLayout = new javax.swing.GroupLayout(pnlCaixaFundo);
        pnlCaixaFundo.setLayout(pnlCaixaFundoLayout);
        pnlCaixaFundoLayout.setHorizontalGroup(
            pnlCaixaFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCaixaFundoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTituloTela)
                .addGap(68, 68, 68))
            .addGroup(pnlCaixaFundoLayout.createSequentialGroup()
                .addGroup(pnlCaixaFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCaixaFundoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlCaixaFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblOrientacoes)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCaixaFundoLayout.createSequentialGroup()
                                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))))
                    .addGroup(pnlCaixaFundoLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(pnlCaixaFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblEmail)
                            .addComponent(btnEnviarEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(13, 13, 13)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        pnlCaixaFundoLayout.setVerticalGroup(
            pnlCaixaFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCaixaFundoLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(lblTituloTela, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(40, 40, 40)
                .addComponent(lblEmail)
                .addGap(2, 2, 2)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEnviarEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(lblOrientacoes)
                .addGap(67, 67, 67)
                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        lblOrientacoes.getAccessibleContext().setAccessibleName("lblOrientacoes");
        lblOrientacoes.getAccessibleContext().setAccessibleParent(lblOrientacoes);

        lblLogoIF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Screenshot_2-Photoroom.png"))); // NOI18N

        lblIF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Screenshot_1-Photoroom.png"))); // NOI18N

        lblInformativo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/img-Photoroom.png"))); // NOI18N

        javax.swing.GroupLayout pnlFundoPrincipalLayout = new javax.swing.GroupLayout(pnlFundoPrincipal);
        pnlFundoPrincipal.setLayout(pnlFundoPrincipalLayout);
        pnlFundoPrincipalLayout.setHorizontalGroup(
            pnlFundoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFundoPrincipalLayout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(pnlCaixaFundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116)
                .addGroup(pnlFundoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInformativo)
                    .addGroup(pnlFundoPrincipalLayout.createSequentialGroup()
                        .addComponent(lblLogoIF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIF, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        pnlFundoPrincipalLayout.setVerticalGroup(
            pnlFundoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFundoPrincipalLayout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addGroup(pnlFundoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogoIF, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFundoPrincipalLayout.createSequentialGroup()
                        .addComponent(lblIF, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addComponent(lblInformativo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFundoPrincipalLayout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(pnlCaixaFundo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFundoPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFundoPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("pnlFundoPrincipal");
        getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarEmailActionPerformed
        enviarEmailRecuperacao();    // TODO add your handling code here:
    }//GEN-LAST:event_btnEnviarEmailActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {
       voltarParaTelaAnterior(); // <<< ADICIONE ESTA LINHA OU SUBSTITUA O CONTEÚDO
}
    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviarEmail;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblIF;
    private javax.swing.JLabel lblInformativo;
    private javax.swing.JLabel lblLogoIF;
    private javax.swing.JLabel lblOrientacoes;
    private javax.swing.JLabel lblTituloTela;
    private javax.swing.JPanel pnlCaixaFundo;
    private javax.swing.JPanel pnlFundoPrincipal;
    private javax.swing.JTextField txtEmail;
    // End of variables declaration//GEN-END:variables
}

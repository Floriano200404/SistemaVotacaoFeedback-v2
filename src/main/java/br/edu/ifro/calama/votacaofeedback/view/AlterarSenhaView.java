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
import java.awt.RenderingHints; 
import java.awt.geom.RoundRectangle2D; 
import javax.swing.JButton; 
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author USER
 */
public class AlterarSenhaView extends javax.swing.JFrame {

    private boolean senhaVisivel = false;
    private boolean confirmarSenhaVisivel = false;
    private String email;
    
    private final javax.swing.ImageIcon eyeOpenIcon = new javax.swing.ImageIcon(getClass().getResource("/icons/eye_open.png"));
    private final javax.swing.ImageIcon eyeClosedIcon = new javax.swing.ImageIcon(getClass().getResource("/icons/eye_closed.png"));
    

    public AlterarSenhaView(String email) {
        initComponents();
        setSize(1280, 720);
        setLocationRelativeTo(null); 
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.email = email;
        jLabEyeSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                senhaVisivel = !senhaVisivel;
                if (senhaVisivel) {
                    pwdNovaSenha.setEchoChar((char) 0);
                    jLabEyeSenha.setIcon(eyeOpenIcon);
                } else {
                    pwdNovaSenha.setEchoChar('●');
                    jLabEyeSenha.setIcon(eyeClosedIcon);
                }
            }
        });

        jLabEyeConfirmarSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmarSenhaVisivel = !confirmarSenhaVisivel;
                if (confirmarSenhaVisivel) {
                    pwdConfirmarSenha.setEchoChar((char) 0);
                    jLabEyeConfirmarSenha.setIcon(eyeOpenIcon);
                } else {
                    pwdConfirmarSenha.setEchoChar('●');
                    jLabEyeConfirmarSenha.setIcon(eyeClosedIcon);
                }
            }
        });
        
        pwdNovaSenha.setEchoChar('●');
        pwdConfirmarSenha.setEchoChar('●');
    }
    
    public void exibirMensagem(String mensagem) {
        ToastUtil toast = new ToastUtil(this, mensagem, ToastUtil.ToastType.ERROR, ToastUtil.ToastPosition.TOP_RIGHT);
        toast.display();
    }

    public void exibirMensagemDeSucesso(String mensagem) {
        ToastUtil toast = new ToastUtil(this, mensagem, ToastUtil.ToastType.SUCCESS, ToastUtil.ToastPosition.TOP_RIGHT);
        toast.display();
    }
   

    class RoundedButton extends JButton {
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
            
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));

            g2.setColor(getForeground());
            g2.drawString(getText(), (getWidth() - g2.getFontMetrics().stringWidth(getText())) / 2,
                    (getHeight() + g2.getFontMetrics().getAscent()) / 2 - g2.getFontMetrics().getDescent());
            g2.dispose();
        }
    }

    class RoundedPanel extends JPanel {
        public RoundedPanel() {
            super();
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
            g2.dispose();
            super.paintComponent(g);
        }
    }
    
    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            Color color1 = new Color(9, 32, 63);   // Azul escuro
            Color color2 = new Color(0, 0, 0);     // Preto

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
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelFundoPrincipal = new GradientPanel();
        jPanelCaixaTexto = new javax.swing.JPanel();
        jLabelAlterarSenha = new javax.swing.JLabel();
        jLabelSenha = new javax.swing.JLabel();
        jLabelConfirmarSenha = new javax.swing.JLabel();
        bntCadastrarNovaSenha = new RoundedButton("CADASTRAR NOVA SENHA")
        ;
        bntVoltar = new RoundedButton("VOLTAR");
        jPanel1 = new RoundedPanel();
        pwdNovaSenha = new javax.swing.JPasswordField();
        jLabEyeSenha = new javax.swing.JLabel();
        jPanel2 = new RoundedPanel()
        ;
        jLabEyeConfirmarSenha = new javax.swing.JLabel();
        pwdConfirmarSenha = new javax.swing.JPasswordField();
        jLabLogo = new javax.swing.JLabel();
        jLabIF = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelFundoPrincipal.setLayout(new java.awt.GridBagLayout());

        jPanelCaixaTexto.setBackground(new java.awt.Color(11, 41, 81));

        jLabelAlterarSenha.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabelAlterarSenha.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAlterarSenha.setText("ALTERAR SENHA");

        jLabelSenha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelSenha.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSenha.setText("Senha:");

        jLabelConfirmarSenha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelConfirmarSenha.setForeground(new java.awt.Color(255, 255, 255));
        jLabelConfirmarSenha.setText("Confirmar Senha:");

        bntCadastrarNovaSenha.setBackground(new java.awt.Color(0, 149, 255));
        bntCadastrarNovaSenha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bntCadastrarNovaSenha.setForeground(new java.awt.Color(255, 255, 255));
        bntCadastrarNovaSenha.setText("CADASTRAR NOVA SENHA");
        bntCadastrarNovaSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCadastrarNovaSenhaActionPerformed(evt);
            }
        });

        bntVoltar.setBackground(new java.awt.Color(153, 153, 153));
        bntVoltar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bntVoltar.setForeground(new java.awt.Color(255, 255, 255));
        bntVoltar.setText("VOLTAR");
        bntVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntVoltarActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        pwdNovaSenha.setBorder(null);

        jLabEyeSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eye_closed.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pwdNovaSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabEyeSenha)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(pwdNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabEyeSenha)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabEyeConfirmarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eye_closed.png"))); // NOI18N

        pwdConfirmarSenha.setBorder(null);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pwdConfirmarSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabEyeConfirmarSenha)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabEyeConfirmarSenha))
            .addComponent(pwdConfirmarSenha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanelCaixaTextoLayout = new javax.swing.GroupLayout(jPanelCaixaTexto);
        jPanelCaixaTexto.setLayout(jPanelCaixaTextoLayout);
        jPanelCaixaTextoLayout.setHorizontalGroup(
            jPanelCaixaTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaixaTextoLayout.createSequentialGroup()
                .addGroup(jPanelCaixaTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCaixaTextoLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanelCaixaTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSenha)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelConfirmarSenha)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelCaixaTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(bntVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bntCadastrarNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelCaixaTextoLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jLabelAlterarSenha)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanelCaixaTextoLayout.setVerticalGroup(
            jPanelCaixaTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCaixaTextoLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabelAlterarSenha)
                .addGap(41, 41, 41)
                .addComponent(jLabelSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabelConfirmarSenha)
                .addGap(33, 33, 33)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(bntCadastrarNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(bntVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipadx = 59;
        gridBagConstraints.ipady = 72;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(147, 112, 162, 0);
        jPanelFundoPrincipal.add(jPanelCaixaTexto, gridBagConstraints);

        jLabLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/IF.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(175, 45, 0, 0);
        jPanelFundoPrincipal.add(jLabLogo, gridBagConstraints);

        jLabIF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/TEXTO IF.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(201, 6, 0, 233);
        jPanelFundoPrincipal.add(jLabIF, gridBagConstraints);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/img-Photoroom.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 60, 0, 233);
        jPanelFundoPrincipal.add(jLabel1, gridBagConstraints);

        getContentPane().add(jPanelFundoPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntCadastrarNovaSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCadastrarNovaSenhaActionPerformed
        String novaSenha = new String(pwdNovaSenha.getPassword());
        String confirmarSenha = new String(pwdConfirmarSenha.getPassword());

        if (novaSenha.isEmpty() || confirmarSenha.isEmpty()) {
            exibirMensagem("Por favor, preencha ambos os campos de senha.");
            return;
        }
        if (novaSenha.length() < 6) { // Exemplo de validação de tamanho
            exibirMensagem("A senha deve ter no mínimo 6 caracteres.");
            return;
        }
        if (!novaSenha.equals(confirmarSenha)) {
            exibirMensagem("As senhas não coincidem. Tente novamente.");
            return;
        }
        
        try {
            RecuperacaoSenhaController controller = new RecuperacaoSenhaController();
            if (controller.isSenhaAnterior(this.email, novaSenha)) {
                exibirMensagem("A nova senha não pode ser igual à anterior.");
                return;
            }
            boolean sucesso = controller.redefinirSenha(this.email, novaSenha);

            if (sucesso) {
                exibirMensagemDeSucesso("Senha alterada com sucesso!");
                new Timer(1500, e -> {
                    new LoginView().setVisible(true);
                    this.dispose();
                }){{setRepeats(false);}}.start();
            } else {
                exibirMensagem("Não foi possível alterar a senha.");
            }
        } catch (Exception e) {
            exibirMensagem("Ocorreu um erro ao salvar a nova senha.");
            e.printStackTrace();
        }
    }//GEN-LAST:event_bntCadastrarNovaSenhaActionPerformed

    private void bntVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntVoltarActionPerformed
        LoginView telaLogin = new LoginView();
        telaLogin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bntVoltarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntCadastrarNovaSenha;
    private javax.swing.JButton bntVoltar;
    private javax.swing.JLabel jLabEyeConfirmarSenha;
    private javax.swing.JLabel jLabEyeSenha;
    private javax.swing.JLabel jLabIF;
    private javax.swing.JLabel jLabLogo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelAlterarSenha;
    private javax.swing.JLabel jLabelConfirmarSenha;
    private javax.swing.JLabel jLabelSenha;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelCaixaTexto;
    private javax.swing.JPanel jPanelFundoPrincipal;
    private javax.swing.JPasswordField pwdConfirmarSenha;
    private javax.swing.JPasswordField pwdNovaSenha;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;

import br.edu.ifro.calama.votacaofeedback.controller.LoginController;
import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.util.PlaceHolderUtil;
import br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil;
import br.edu.ifro.calama.votacaofeedback.util.ToastUtil;
import br.edu.ifro.calama.votacaofeedback.view.MenuPrincipalView;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Aluno
 */


public class LoginView extends javax.swing.JFrame {

    private ImageIcon iconeOlhoAberto;
    private ImageIcon iconeOlhoFechado;
    private boolean senhaVisivel = false;
    
    
    private final String PLACEHOLDER_SENHA = "Digite sua senha";
    private Usuario usuarioLogado;
    
    public LoginView() {
        initComponents();

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        txtLogin.setMargin(new java.awt.Insets(2, 10, 2, 2));
        pwdSenha.setMargin(new java.awt.Insets(2, 10, 2, 2));
        jPanelImages.setOpaque(false);
        PlaceHolderUtil.setPlaceholder(pwdSenha, PLACEHOLDER_SENHA);
        
        PlaceHolderUtil.setPlaceholder(txtLogin, "aluno@estudante.ifro.edu.br");
        PlaceHolderUtil.setPlaceholder(pwdSenha, "Digite sua senha");
        
        
        jPanel1.setFocusable(true);
        
        try {
            iconeOlhoAberto = new ImageIcon(getClass().getResource("/icons/eye_open.png"));
            iconeOlhoFechado = new ImageIcon(getClass().getResource("/icons/eye_closed.png"));
        } catch (Exception e) {
            System.err.println("Erro ao carregar os ícones de olho.");
            e.printStackTrace();
        }
        RoundedButtonUtil BotaoRedondo = (RoundedButtonUtil) btnEntrar;
        BotaoRedondo.setRadius(20); 
        
        RoundedButtonUtil btnCadastrarCustom = (RoundedButtonUtil) btnCadastrar;
        btnCadastrarCustom.setRadius(20);
        btnCadastrarCustom.setForeground(Color.WHITE);
        
        btnCadastrarCustom.setColor(new Color(127, 140, 141));
        btnCadastrarCustom.setColorOver(new Color(149, 165, 166));
        btnCadastrarCustom.setColorClick(new Color(93, 109, 126));
        btnCadastrarCustom.setBorderColor(new Color(127, 140, 141));
        
        RoundedButtonUtil btnEsqueciSenhaCustom = (RoundedButtonUtil) btnEsqueciSenha;
        btnEsqueciSenhaCustom.setRadius(20);       
        btnEsqueciSenhaCustom.setForeground(Color.WHITE);
        
        btnEsqueciSenhaCustom.setColor(new Color(127, 140, 141));
        btnEsqueciSenhaCustom.setColorOver(new Color(149, 165, 166));
        btnEsqueciSenhaCustom.setColorClick(new Color(93, 109, 126));
        btnEsqueciSenhaCustom.setBorderColor(new Color(127, 140, 141));
        
        txtLogin.setBackground(Color.WHITE);
        txtLogin.setForeground(new Color(127, 140, 141));
        txtLogin.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        PlaceHolderUtil.setPlaceholder(txtLogin, "Digite seu e-mail");
    }
    
    public javax.swing.JTextField getTxtLogin() {
    // IMPORTANTE: Se o nome da sua variável não for 'txtLogin', 
    // troque pelo nome correto aqui.
        return txtLogin; 
    }
    
    public javax.swing.JPasswordField getTxtSenha() {
    // IMPORTANTE: Se o nome da sua variável não for 'txtSenha', 
    // troque pelo nome correto aqui.
        return pwdSenha;
    }
    
    public void exibirMensagem(String mensagem) {
        ToastUtil toast = new ToastUtil(
            this, mensagem, ToastUtil.ToastType.ERROR, ToastUtil.ToastPosition.TOP_RIGHT
        );

        toast.display();
        
    }
    
    public void exibirMensagemDeSucesso(String mensagem) {
        ToastUtil toast = new ToastUtil(
            this, mensagem, ToastUtil.ToastType.SUCCESS, ToastUtil.ToastPosition.TOP_RIGHT
    );
    
    toast.display();

    }

    
    class  jPanelGradient extends JPanel {
        @Override
        protected void paintComponent (Graphics g){
            Graphics2D g2d = (Graphics2D)g;
            int width = getWidth();
            int height = getHeight();
            
            Color color1 = new Color(9, 32, 63);
            Color color2 = new Color(0, 0, 0);
            
            int pontoTransicaoX = (int) (width * 0.25);

            g2d.setColor(color1);
            g2d.fillRect(0, 0, pontoTransicaoX, height);
            
            GradientPaint gp = new GradientPaint (pontoTransicaoX, 0, color1, width, 0, color2);
            g2d.setPaint(gp);
            g2d.fillRect(pontoTransicaoX, 0, width - pontoTransicaoX, height);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new jPanelGradient();
        jPanel1 = new javax.swing.JPanel();
        jLabLogin = new javax.swing.JLabel();
        jLabEmail = new javax.swing.JLabel();
        txtLogin = new br.edu.ifro.calama.votacaofeedback.util.RoundedTextFieldUtil();
        jLabSenha = new javax.swing.JLabel();
        painelSenha = new br.edu.ifro.calama.votacaofeedback.util.RoundedPanelUtil(15, new java.awt.Color(255, 255, 255));
        pwdSenha = new javax.swing.JPasswordField();
        jLabEye = new javax.swing.JLabel();
        btnEntrar = new br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil();
        jSeparator1 = new javax.swing.JSeparator();
        painelBotoes = new javax.swing.JPanel();
        btnCadastrar = new br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil();
        btnEsqueciSenha = new br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil();
        jPanelImages = new javax.swing.JPanel();
        jLabTextIF = new javax.swing.JLabel();
        jLabLogo = new javax.swing.JLabel();
        jLabInfo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(9, 32, 63));
        getContentPane().setLayout(new java.awt.GridLayout());

        jPanel2.setBackground(new java.awt.Color(9, 32, 63));
        jPanel2.setAutoscrolls(true);
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(11, 41, 81));
        jPanel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jPanel1.setMinimumSize(new java.awt.Dimension(450, 600));
        jPanel1.setName("DivLogin"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(643, 567));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabLogin.setBackground(new java.awt.Color(255, 255, 255));
        jLabLogin.setFont(new java.awt.Font("Arial Black", 1, 32)); // NOI18N
        jLabLogin.setForeground(new java.awt.Color(255, 255, 255));
        jLabLogin.setText("LOGIN");
        jLabLogin.setAlignmentY(0.0F);
        jLabLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabLoginMouseClicked(evt);
            }
        });

        jLabEmail.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabEmail.setForeground(new java.awt.Color(255, 255, 255));
        jLabEmail.setText("E-mail Institucional:");

        txtLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtLogin.setName(""); // NOI18N
        txtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginActionPerformed(evt);
            }
        });

        jLabSenha.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabSenha.setForeground(new java.awt.Color(255, 255, 255));
        jLabSenha.setText("Senha:");
        jLabSenha.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        painelSenha.setBackground(new java.awt.Color(255, 255, 255));
        painelSenha.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        painelSenha.setMinimumSize(new java.awt.Dimension(24, 40));
        painelSenha.setPreferredSize(new java.awt.Dimension(403, 39));
        painelSenha.setLayout(new java.awt.BorderLayout());

        pwdSenha.setBorder(null);
        pwdSenha.setMargin(new java.awt.Insets(5, 10, 5, 10));
        pwdSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwdSenhaActionPerformed(evt);
            }
        });
        pwdSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pwdSenhaKeyPressed(evt);
            }
        });
        painelSenha.add(pwdSenha, java.awt.BorderLayout.CENTER);

        jLabEye.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eye_closed.png"))); // NOI18N
        jLabEye.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabEye.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabEyeMouseClicked(evt);
            }
        });
        painelSenha.add(jLabEye, java.awt.BorderLayout.EAST);

        btnEntrar.setBackground(new java.awt.Color(0, 149, 255));
        btnEntrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEntrar.setForeground(new java.awt.Color(255, 255, 255));
        btnEntrar.setText("ACESSAR");
        btnEntrar.setBorder(null);
        btnEntrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEntrar.setName("btnEntrar"); // NOI18N
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        painelBotoes.setOpaque(false);
        painelBotoes.setLayout(new java.awt.GridLayout(1, 2, 60, 0));

        btnCadastrar.setText("CADASTRAR");
        btnCadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });
        painelBotoes.add(btnCadastrar);

        btnEsqueciSenha.setText("ESQUECI A SENHA");
        btnEsqueciSenha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEsqueciSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEsqueciSenhaActionPerformed(evt);
            }
        });
        painelBotoes.add(btnEsqueciSenha);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(80, 80, 80)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabSenha)
                                .addComponent(painelSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabEmail)))))
                .addGap(80, 80, 80))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabLogin)
                .addGap(219, 219, 219))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabLogin)
                .addGap(29, 29, 29)
                .addComponent(jLabEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabTextIF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/TEXTO IF.png"))); // NOI18N
        jLabTextIF.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabTextIF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabTextIFMouseClicked(evt);
            }
        });

        jLabLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/IF.png"))); // NOI18N
        jLabLogo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabLogo.setName("FOTO DO IF"); // NOI18N
        jLabLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabLogoMouseClicked(evt);
            }
        });

        jLabInfo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/img-Photoroom.png"))); // NOI18N

        javax.swing.GroupLayout jPanelImagesLayout = new javax.swing.GroupLayout(jPanelImages);
        jPanelImages.setLayout(jPanelImagesLayout);
        jPanelImagesLayout.setHorizontalGroup(
            jPanelImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImagesLayout.createSequentialGroup()
                .addComponent(jLabLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabTextIF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelImagesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelImagesLayout.setVerticalGroup(
            jPanelImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImagesLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanelImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelImagesLayout.createSequentialGroup()
                        .addComponent(jLabTextIF)
                        .addGap(19, 19, 19)))
                .addGap(67, 67, 67)
                .addComponent(jLabInfo)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(jPanelImages, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelImages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(125, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pwdSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pwdSenhaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pwdSenhaKeyPressed

    private void pwdSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwdSenhaActionPerformed
        // TODO add your handling code here:
        btnEntrar.doClick();
    }//GEN-LAST:event_pwdSenhaActionPerformed

    private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginActionPerformed
        // TODO add your handling code here:
        btnEntrar.doClick();
    }//GEN-LAST:event_txtLoginActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        LoginController controller = new LoginController(this);
    
        controller.autenticar();
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        try {
        CadastroView telaDeCadastro = new CadastroView();

        telaDeCadastro.setVisible(true);

        this.dispose();

    } catch (java.text.ParseException e) {
        
        JOptionPane.showMessageDialog(this, "Erro ao abrir a tela de cadastro.", "Erro Inesperado", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void jLabLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabLogoMouseClicked
        try {
        java.net.URI link = new java.net.URI("https://portal.ifro.edu.br/");
        java.awt.Desktop.getDesktop().browse(link);
        } catch (java.net.URISyntaxException | java.io.IOException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Não foi possível abrir o link.", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jLabLogoMouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        jPanel1.requestFocusInWindow();
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        jPanel1.requestFocusInWindow();
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jLabTextIFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabTextIFMouseClicked
        try {
        java.net.URI link = new java.net.URI("https://portal.ifro.edu.br/");
        java.awt.Desktop.getDesktop().browse(link);
        } catch (java.net.URISyntaxException | java.io.IOException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Não foi possível abrir o link.", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jLabTextIFMouseClicked

    private void jLabEyeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabEyeMouseClicked
        System.out.println("Lógica de clique iniciada. Estado atual de 'senhaVisivel': " + senhaVisivel);
        
        String textoAtual = String.valueOf(pwdSenha.getPassword());
        if (textoAtual.equals(PLACEHOLDER_SENHA)) {
        return; 
        }
        
        senhaVisivel = !senhaVisivel;
        
        if (senhaVisivel) {

            pwdSenha.setEchoChar((char) 0); 

            jLabEye.setIcon(iconeOlhoAberto);

            System.out.println("A senha agora está visível.");

        } else {

            pwdSenha.setEchoChar('*');

            jLabEye.setIcon(iconeOlhoFechado);

            System.out.println("A senha agora está escondida.");
        }
    }//GEN-LAST:event_jLabEyeMouseClicked

    private void jLabLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabLoginMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jLabLoginMouseClicked

    private void btnEsqueciSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEsqueciSenhaActionPerformed
        RecuperacaoDeSenhaView telaRecuperacao = new RecuperacaoDeSenhaView();
        
        telaRecuperacao.setVisible(true);
        
        this.dispose();
        
    }//GEN-LAST:event_btnEsqueciSenhaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnEsqueciSenha;
    private javax.swing.JLabel jLabEmail;
    private javax.swing.JLabel jLabEye;
    private javax.swing.JLabel jLabInfo;
    private javax.swing.JLabel jLabLogin;
    private javax.swing.JLabel jLabLogo;
    private javax.swing.JLabel jLabSenha;
    private javax.swing.JLabel jLabTextIF;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelImages;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JPanel painelSenha;
    private javax.swing.JPasswordField pwdSenha;
    private javax.swing.JTextField txtLogin;
    // End of variables declaration//GEN-END:variables
}
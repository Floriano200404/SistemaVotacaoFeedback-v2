/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;

import br.edu.ifro.calama.votacaofeedback.controller.CadastroController;
import br.edu.ifro.calama.votacaofeedback.util.FiltroNumerosUtil;
import br.edu.ifro.calama.votacaofeedback.util.PlaceHolderUtil;
import br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil;
import br.edu.ifro.calama.votacaofeedback.util.ToastUtil;
import com.formdev.flatlaf.json.ParseException;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Athos
 */
public class CadastroView extends javax.swing.JFrame {

    public CadastroView() throws java.text.ParseException {
    initComponents();
    
    javax.swing.border.Border padding = BorderFactory.createEmptyBorder(5, 10, 5, 10);

    txtNome.setBorder(padding);
    txtCpf.setBorder(padding);
    txtEmail.setBorder(padding);
    txtMatricula.setBorder(padding);
    pwdSenha.setBorder(padding);
    pwdConfirmarSenha.setBorder(padding);
    
    this.setLocationRelativeTo(null); 

    jPanelDegrade.setFocusable(true);

    jPanelDegrade.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            jPanelDegrade.requestFocusInWindow();
        }
    });
    
        RoundedButtonUtil btnCadastrarCustom = (RoundedButtonUtil) btnCadastrar;
        btnCadastrarCustom.setRadius(20);
        btnCadastrarCustom.setForeground(Color.WHITE);
        
        btnCadastrarCustom.setColor(new Color(0, 149, 255));
        btnCadastrarCustom.setColorOver(new Color(30, 169, 255));
        btnCadastrarCustom.setColorClick(new Color(0, 129, 225));
        btnCadastrarCustom.setBorderColor(new Color(0, 149, 255));
        
        RoundedButtonUtil btnCancelarCustom = (RoundedButtonUtil) btnCancelar;
        btnCancelarCustom.setRadius(20);       
        btnCancelarCustom.setForeground(Color.WHITE);
        
        btnCancelarCustom.setColor(new Color(127, 140, 141));
        btnCancelarCustom.setColorOver(new Color(149, 165, 166));
        btnCancelarCustom.setColorClick(new Color(93, 109, 126));
        btnCancelarCustom.setBorderColor(new Color(127, 140, 141));
    
    cbxPerfil.removeAllItems(); 

    cbxPerfil.addItem("Selecionar Perfil Institucional");

    cbxPerfil.addItem("ANÁLISE E DESENVOLVIMENTO DE SISTEMAS");
    cbxPerfil.addItem("ENGENHARIA CIVIL");
    cbxPerfil.addItem("ENGENHARIA DE CONTROLE E AUTOMAÇÃO");
    cbxPerfil.addItem("ENGENHARIA QUÍMICA");
    cbxPerfil.addItem("FÍSICA");
    cbxPerfil.addItem("PROFESSOR");
    cbxPerfil.addItem("SERVIDORES DO CAMPUS");
        
    PlaceHolderUtil.setPlaceholder(txtNome, "Digite seu Nome Completo");
    PlaceHolderUtil.setPlaceholder(pwdSenha, "************");
    PlaceHolderUtil.setPlaceholder(pwdConfirmarSenha, "************");
    PlaceHolderUtil.setPlaceholder(txtEmail, "aluno@estudante.ifro.edu.br");
    
    PlainDocument docMatricula = (PlainDocument) txtMatricula.getDocument();
    docMatricula.setDocumentFilter(new FiltroNumerosUtil());
    
    final String placeholderMatricula = "0000000000000";
    final Color corPlaceholder = Color.GRAY;
    final Color corDigitacao = Color.BLACK;

    txtMatricula.setText(placeholderMatricula);
    txtMatricula.setForeground(corPlaceholder);

    txtMatricula.addFocusListener(new FocusAdapter() {
    @Override
    public void focusGained(FocusEvent e) {

        if (txtMatricula.getForeground().equals(corPlaceholder)) {
            txtMatricula.setText("");
            txtMatricula.setForeground(corDigitacao);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (txtMatricula.getText().isEmpty()) {
            txtMatricula.setForeground(corPlaceholder);
            txtMatricula.setText(placeholderMatricula);
        }
    }
    });
    
    try {
       MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
       mascaraCpf.setPlaceholderCharacter('_');      
       mascaraCpf.install(txtCpf);
        
    } catch (ParseException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erro ao criar a máscara de CPF: " + e.getMessage());
    }
    
    final String placeholderCpf = "000.000.000-00";
    
    txtCpf.setText(placeholderCpf);
    txtCpf.setForeground(Color.GRAY);

    txtCpf.addFocusListener(new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e) {

            if (txtCpf.getText().equals(placeholderCpf)) {
                txtCpf.setText("");
                txtCpf.setForeground(Color.BLACK);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            String textoSemMascara = txtCpf.getText().replaceAll("[^0-9]", "");
            
            if (textoSemMascara.isEmpty()) {
                txtCpf.setForeground(Color.GRAY);
                txtCpf.setText(placeholderCpf);
            }
        }
    });
    SwingUtilities.invokeLater(() -> {
    jPanelDegrade.requestFocusInWindow();
    });
    
}
    class  jPainelGradient extends JPanel {
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
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelDegrade = new jPainelGradient();
        jPanelForm = new javax.swing.JPanel();
        JlabNome = new javax.swing.JLabel();
        JlabEmail = new javax.swing.JLabel();
        JlabSenha = new javax.swing.JLabel();
        JlabPerfil = new javax.swing.JLabel();
        txtNome = new br.edu.ifro.calama.votacaofeedback.util.RoundedTextFieldUtil();
        pwdSenha = new br.edu.ifro.calama.votacaofeedback.util.RoundedPasswordFieldUtil();
        pwdConfirmarSenha = new br.edu.ifro.calama.votacaofeedback.util.RoundedPasswordFieldUtil();
        txtCpf = new br.edu.ifro.calama.votacaofeedback.util.RoundedFormattedTextFieldUtil();
        txtEmail = new br.edu.ifro.calama.votacaofeedback.util.RoundedTextFieldUtil();
        txtMatricula = new br.edu.ifro.calama.votacaofeedback.util.RoundedTextFieldUtil();
        cbxPerfil = new br.edu.ifro.calama.votacaofeedback.util.RoundedComboBoxUtil();
        JlabCpf = new javax.swing.JLabel();
        JlabMatricula = new javax.swing.JLabel();
        JlabConfirmarSenha = new javax.swing.JLabel();
        jLabTitulo = new javax.swing.JLabel();
        jPanelBotoes = new javax.swing.JPanel();
        btnCancelar = new br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil();
        btnCadastrar = new br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanelDegrade.setBackground(new java.awt.Color(9, 32, 63));
        jPanelDegrade.setLayout(new java.awt.GridBagLayout());

        jPanelForm.setBackground(new java.awt.Color(11, 20, 81));

        JlabNome.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        JlabNome.setForeground(new java.awt.Color(255, 255, 255));
        JlabNome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JlabNome.setText("Nome Completo:");

        JlabEmail.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        JlabEmail.setForeground(new java.awt.Color(255, 255, 255));
        JlabEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JlabEmail.setText("E-mail Institucional:");

        JlabSenha.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        JlabSenha.setForeground(new java.awt.Color(255, 255, 255));
        JlabSenha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JlabSenha.setText("Senha:");

        JlabPerfil.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        JlabPerfil.setForeground(new java.awt.Color(255, 255, 255));
        JlabPerfil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JlabPerfil.setText("Perfil Institucional:");

        txtNome.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        pwdSenha.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));

        pwdConfirmarSenha.setBorder(null);

        txtCpf.setBorder(null);

        txtEmail.setBorder(null);

        txtMatricula.setBorder(null);
        txtMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatriculaActionPerformed(evt);
            }
        });

        JlabCpf.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        JlabCpf.setForeground(new java.awt.Color(255, 255, 255));
        JlabCpf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JlabCpf.setText("CPF:");

        JlabMatricula.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        JlabMatricula.setForeground(new java.awt.Color(255, 255, 255));
        JlabMatricula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JlabMatricula.setText("Matrícula:");

        JlabConfirmarSenha.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        JlabConfirmarSenha.setForeground(new java.awt.Color(255, 255, 255));
        JlabConfirmarSenha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JlabConfirmarSenha.setText("Confimar Senha:");

        jLabTitulo.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabTitulo.setText("CADASTRO");

        jPanelBotoes.setBackground(new java.awt.Color(11, 20, 81));
        jPanelBotoes.setOpaque(false);

        btnCancelar.setBorder(null);
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnCadastrar.setBorder(null);
        btnCadastrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastrar.setText("CADASTRAR");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotoesLayout = new javax.swing.GroupLayout(jPanelBotoes);
        jPanelBotoes.setLayout(jPanelBotoesLayout);
        jPanelBotoesLayout.setHorizontalGroup(
            jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotoesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanelBotoesLayout.setVerticalGroup(
            jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotoesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanelFormLayout = new javax.swing.GroupLayout(jPanelForm);
        jPanelForm.setLayout(jPanelFormLayout);
        jPanelFormLayout.setHorizontalGroup(
            jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelFormLayout.createSequentialGroup()
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JlabConfirmarSenha)
                            .addComponent(pwdConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85)
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JlabMatricula)
                            .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85)
                        .addComponent(jPanelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelFormLayout.createSequentialGroup()
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelFormLayout.createSequentialGroup()
                                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(JlabNome, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(85, 85, 85)
                                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JlabCpf)
                                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelFormLayout.createSequentialGroup()
                                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JlabSenha)
                                    .addComponent(pwdSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(85, 85, 85)
                                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JlabEmail))))
                        .addGap(85, 85, 85)
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelFormLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(cbxPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(JlabPerfil))))
                .addGap(0, 100, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFormLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabTitulo)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelFormLayout.setVerticalGroup(
            jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabTitulo)
                .addGap(55, 55, 55)
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JlabNome)
                    .addComponent(JlabCpf)
                    .addComponent(JlabPerfil))
                .addGap(12, 12, 12)
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelFormLayout.createSequentialGroup()
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JlabSenha)
                            .addComponent(JlabEmail))
                        .addGap(12, 12, 12)
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pwdSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelFormLayout.createSequentialGroup()
                                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(JlabConfirmarSenha)
                                    .addComponent(JlabMatricula))
                                .addGap(12, 12, 12)
                                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pwdConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 120, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 120;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(100, 120, 100, 120);
        jPanelDegrade.add(jPanelForm, gridBagConstraints);

        getContentPane().add(jPanelDegrade, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatriculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatriculaActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed

    String nome = txtNome.getText();
    String cpf = txtCpf.getText();
    String matricula = txtMatricula.getText();
    String email = txtEmail.getText();
    String senha = new String(pwdSenha.getPassword());
    String confirmarSenha = new String (pwdConfirmarSenha.getPassword());
    String perfilSelecionado = (String) cbxPerfil.getSelectedItem();

    if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || cpf.isEmpty() || matricula.isEmpty() || perfilSelecionado.contains("Selecionar")) {
        new ToastUtil(this, jPanelBotoes, "Preencha todos os campos.", ToastUtil.ToastType.ERROR).display();
        return;
    }

    if (confirmarSenha.isEmpty()) {
        new ToastUtil(this, jPanelBotoes, "Confirme sua senha.", ToastUtil.ToastType.ERROR).display();
        return; 
    }

    if (!senha.equals(confirmarSenha)) {
        new ToastUtil(this, jPanelBotoes, "As senhas não conferem. Tente novamente.", ToastUtil.ToastType.ERROR).display();
        pwdSenha.setText("");
        pwdConfirmarSenha.setText("");
        return;
    }

    String emailMinusculo = email.toLowerCase();
    if (!emailMinusculo.endsWith("@estudante.ifro.edu.br") && !emailMinusculo.endsWith("@ifro.edu.br")) {
        new ToastUtil(this, jPanelBotoes, "Utilize um email institucional válido.", ToastUtil.ToastType.ERROR).display();
        return;
    }

    if (perfilSelecionado.equals("PROFESSOR") || perfilSelecionado.equals("SERVIDORES DO CAMPUS")) {
        if (emailMinusculo.contains("@estudante.ifro.edu.br")) {
            new ToastUtil(this, jPanelBotoes, "Perfil e email são incompatíveis.", ToastUtil.ToastType.ERROR).display();
            return;
        }
    } else { 
        if (!emailMinusculo.contains("@estudante.ifro.edu.br")) {
            new ToastUtil(this, jPanelBotoes, "Perfil e email são incompatíveis", ToastUtil.ToastType.ERROR).display();
            return;
        }
    }
    
    String cpfApenasDigitos = txtCpf.getText().replaceAll("[^0-9]", "");
    if (cpfApenasDigitos.length() != 11) {
        new ToastUtil(this, jPanelBotoes, "O CPF é inválido.", ToastUtil.ToastType.ERROR).display();
        return;
    }
    
    String matriculaApenasDigitos = matricula.replaceAll("[^0-9]", "");
    if (matriculaApenasDigitos.length() != 13) { 
        new ToastUtil(this, jPanelBotoes, "A Matrícula parece ser inválida.", ToastUtil.ToastType.ERROR).display();
        return;
    }
    
        try {
            CadastroController controller = new CadastroController();
            controller.processarCadastro(nome, cpf, matricula, email, senha, perfilSelecionado);
 
            ToastUtil toast = new ToastUtil(this, jPanelBotoes, "Parabéns! Cadastro Feito com Sucesso!", ToastUtil.ToastType.SUCCESS);
            toast.display();

            LoginView telaLogin = new LoginView();
            telaLogin.setVisible(true);
            this.dispose();

        } catch (SQLException e) {

          if (e.getErrorCode() == 1062) {
        
          String mensagemTecnica = e.getMessage().toLowerCase();
          
          if (mensagemTecnica.contains("usuarios.cpf_unique")) { // Supondo que sua constraint se chame 'cpf_unique'
            new ToastUtil(this, jPanelBotoes, "Este CPF já está cadastrado.", ToastUtil.ToastType.ERROR).display();
          
          } else if (mensagemTecnica.contains("usuarios.un_email_usuario")) {
            new ToastUtil(this, jPanelBotoes, "Este e-mail já está em uso.", ToastUtil.ToastType.ERROR).display();
        
          } else if (mensagemTecnica.contains("usuarios.matricula_unique")) {
            new ToastUtil(this, jPanelBotoes, "Esta matrícula já está cadastrada.", ToastUtil.ToastType.ERROR).display();
        
          } else {
            new ToastUtil(this, jPanelBotoes, "Dados duplicados. Tente novamente.", ToastUtil.ToastType.ERROR).display();
          }
        
          } else {
           new ToastUtil(this, jPanelBotoes, "Erro inesperado no o banco de dados.", ToastUtil.ToastType.ERROR).display();
    }

          e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(CadastroView.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
    LoginView telaLogin = new LoginView();

    telaLogin.setVisible(true);

    this.dispose();    }//GEN-LAST:event_btnCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new CadastroView().setVisible(true);
                } catch (java.text.ParseException ex) {
                    Logger.getLogger(CadastroView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JlabConfirmarSenha;
    private javax.swing.JLabel JlabCpf;
    private javax.swing.JLabel JlabEmail;
    private javax.swing.JLabel JlabMatricula;
    private javax.swing.JLabel JlabNome;
    private javax.swing.JLabel JlabPerfil;
    private javax.swing.JLabel JlabSenha;
    private br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil btnCadastrar;
    private br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil btnCancelar;
    private br.edu.ifro.calama.votacaofeedback.util.RoundedComboBoxUtil cbxPerfil;
    private javax.swing.JLabel jLabTitulo;
    private javax.swing.JPanel jPanelBotoes;
    private javax.swing.JPanel jPanelDegrade;
    private javax.swing.JPanel jPanelForm;
    private br.edu.ifro.calama.votacaofeedback.util.RoundedPasswordFieldUtil pwdConfirmarSenha;
    private br.edu.ifro.calama.votacaofeedback.util.RoundedPasswordFieldUtil pwdSenha;
    private br.edu.ifro.calama.votacaofeedback.util.RoundedFormattedTextFieldUtil txtCpf;
    private br.edu.ifro.calama.votacaofeedback.util.RoundedTextFieldUtil txtEmail;
    private br.edu.ifro.calama.votacaofeedback.util.RoundedTextFieldUtil txtMatricula;
    private br.edu.ifro.calama.votacaofeedback.util.RoundedTextFieldUtil txtNome;
    // End of variables declaration//GEN-END:variables
}

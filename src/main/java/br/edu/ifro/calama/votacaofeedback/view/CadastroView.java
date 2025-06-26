/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;

import br.edu.ifro.calama.votacaofeedback.controller.CadastroController;
import br.edu.ifro.calama.votacaofeedback.util.FiltroNumerosUtil;
import br.edu.ifro.calama.votacaofeedback.util.PlaceHolderUtil;
import br.edu.ifro.calama.votacaofeedback.util.ToastUtil;
import com.formdev.flatlaf.json.ParseException;
import java.awt.Color;
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

    /**
     * Creates new form CadastroView
     */
    public CadastroView() throws java.text.ParseException {
    initComponents(); 

    this.setLocationRelativeTo(null); 

    jPanelDegrade.setFocusable(true);

    jPanelDegrade.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            jPanelDegrade.requestFocusInWindow();
        }
    });
    
    cbxPerfilInstitucional.removeAllItems(); 

    cbxPerfilInstitucional.addItem("Selecionar Perfil Institucional");

    cbxPerfilInstitucional.addItem("ANÁLISE E DESENVOLVIMENTO DE SISTEMAS");
    cbxPerfilInstitucional.addItem("ENGENHARIA CIVIL");
    cbxPerfilInstitucional.addItem("ENGENHARIA DE CONTROLE E AUTOMAÇÃO");
    cbxPerfilInstitucional.addItem("ENGENHARIA QUÍMICA");
    cbxPerfilInstitucional.addItem("FÍSICA");
    cbxPerfilInstitucional.addItem("PROFESSOR");
    cbxPerfilInstitucional.addItem("SERVIDORES DO CAMPUS");
        
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
        
    txtNome.setMargin(new java.awt.Insets(2, 10, 2, 2));
    pwdSenha.setMargin(new java.awt.Insets(2, 10, 2, 2));
    pwdConfirmarSenha.setMargin(new java.awt.Insets(2, 10, 2, 2));
    txtCpf.setMargin(new java.awt.Insets(2, 10, 2, 2));
    txtEmail.setMargin(new java.awt.Insets(2, 10, 2, 2));
    txtMatricula.setMargin(new java.awt.Insets(2, 10, 2, 2));
    
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
        txtNome = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        pwdSenha = new javax.swing.JPasswordField();
        cbxPerfilInstitucional = new javax.swing.JComboBox<>();
        JlabCpf = new javax.swing.JLabel();
        JlabMatricula = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        txtMatricula = new javax.swing.JTextField();
        JlabConfirmarSenha = new javax.swing.JLabel();
        pwdConfirmarSenha = new javax.swing.JPasswordField();
        jLabTitulo = new javax.swing.JLabel();
        jPanelBotoes = new javax.swing.JPanel();
        btnCadastrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

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

        txtNome.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        txtEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        pwdSenha.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        cbxPerfilInstitucional.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxPerfilInstitucional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPerfilInstitucionalActionPerformed(evt);
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

        txtCpf.setMinimumSize(new java.awt.Dimension(64, 23));
        txtCpf.setPreferredSize(new java.awt.Dimension(64, 23));
        txtCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCpfActionPerformed(evt);
            }
        });

        txtMatricula.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        JlabConfirmarSenha.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        JlabConfirmarSenha.setForeground(new java.awt.Color(255, 255, 255));
        JlabConfirmarSenha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JlabConfirmarSenha.setText("Confimar Senha:");

        pwdConfirmarSenha.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabTitulo.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabTitulo.setText("CADASTRO");

        jPanelBotoes.setBackground(new java.awt.Color(11, 20, 81));
        jPanelBotoes.setOpaque(false);

        btnCadastrar.setBackground(new java.awt.Color(0, 149, 255));
        btnCadastrar.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        btnCadastrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(157, 157, 157));
        btnCancelar.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setMaximumSize(new java.awt.Dimension(80, 23));
        btnCancelar.setMinimumSize(new java.awt.Dimension(80, 23));
        btnCancelar.setPreferredSize(new java.awt.Dimension(80, 23));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotoesLayout = new javax.swing.GroupLayout(jPanelBotoes);
        jPanelBotoes.setLayout(jPanelBotoesLayout);
        jPanelBotoesLayout.setHorizontalGroup(
            jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBotoesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanelBotoesLayout.setVerticalGroup(
            jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotoesLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelFormLayout = new javax.swing.GroupLayout(jPanelForm);
        jPanelForm.setLayout(jPanelFormLayout);
        jPanelFormLayout.setHorizontalGroup(
            jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormLayout.createSequentialGroup()
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFormLayout.createSequentialGroup()
                        .addGap(600, 600, 600)
                        .addComponent(jLabTitulo))
                    .addGroup(jPanelFormLayout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(JlabSenha)
                        .addGap(329, 329, 329)
                        .addComponent(JlabEmail))
                    .addGroup(jPanelFormLayout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(pwdSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanelFormLayout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFormLayout.createSequentialGroup()
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JlabNome)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85)
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelFormLayout.createSequentialGroup()
                                .addComponent(JlabCpf)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanelFormLayout.createSequentialGroup()
                                .addComponent(txtCpf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(85, 85, 85)))
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxPerfilInstitucional, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JlabPerfil))
                        .addGap(87, 87, 87))
                    .addGroup(jPanelFormLayout.createSequentialGroup()
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelFormLayout.createSequentialGroup()
                                .addComponent(pwdConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)
                                .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelFormLayout.createSequentialGroup()
                                .addComponent(JlabConfirmarSenha)
                                .addGap(263, 263, 263)
                                .addComponent(JlabMatricula)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                        .addComponent(jPanelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104))))
        );
        jPanelFormLayout.setVerticalGroup(
            jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormLayout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jLabTitulo)
                .addGap(69, 69, 69)
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JlabNome)
                    .addComponent(JlabCpf)
                    .addComponent(JlabPerfil))
                .addGap(12, 12, 12)
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNome)
                    .addComponent(cbxPerfilInstitucional)
                    .addComponent(txtCpf, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFormLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JlabSenha)
                            .addComponent(JlabEmail))
                        .addGap(12, 12, 12)
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pwdSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JlabConfirmarSenha)
                            .addComponent(JlabMatricula))
                        .addGap(12, 12, 12)
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pwdConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelFormLayout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jPanelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 79;
        gridBagConstraints.ipady = 94;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(100, 120, 100, 120);
        jPanelDegrade.add(jPanelForm, gridBagConstraints);

        getContentPane().add(jPanelDegrade, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
    String nome = txtNome.getText();
    String cpf = txtCpf.getText();
    String matricula = txtMatricula.getText();
    String email = txtEmail.getText();
    String senha = new String(pwdSenha.getPassword());
    String confirmarSenha = new String (pwdConfirmarSenha.getPassword());
    String perfilSelecionado = (String) cbxPerfilInstitucional.getSelectedItem();

    if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || cpf.isEmpty() || matricula.isEmpty() || perfilSelecionado.contains("Selecionar")) {
        new ToastUtil(this, jPanelBotoes, "Por favor, preencha todos os campos e selecione um perfil.", ToastUtil.ToastType.ERROR).display();
        return;
    }

    if (confirmarSenha.isEmpty()) {
        new ToastUtil(this, jPanelBotoes, "Por favor, confirme sua senha.", ToastUtil.ToastType.ERROR).display();
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
        new ToastUtil(this, jPanelBotoes, "Por favor, utilize um email institucional válido.", ToastUtil.ToastType.ERROR).display();
        return;
    }

    if (perfilSelecionado.equals("PROFESSOR") || perfilSelecionado.equals("SERVIDORES DO CAMPUS")) {
        if (emailMinusculo.contains("@estudante.ifro.edu.br")) {
            new ToastUtil(this, jPanelBotoes, "Perfil e email são incompatíveis.", ToastUtil.ToastType.ERROR).display();
            return;
        }
    } else { 
        if (!emailMinusculo.contains("@estudante.ifro.edu.br")) {
            new ToastUtil(this, jPanelBotoes, "Alunos devem usar um email @estudante.ifro.edu.br.", ToastUtil.ToastType.ERROR).display();
            return;
        }
    }
    
    String cpfApenasDigitos = txtCpf.getText().replaceAll("[^0-9]", "");
    if (cpfApenasDigitos.length() != 11) {
        new ToastUtil(this, jPanelBotoes, "O CPF é inválido. Deve conter 11 dígitos.", ToastUtil.ToastType.ERROR).display();
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

        } catch (Exception e) {

            ToastUtil toast = new ToastUtil(this, jPanelBotoes, "Erro ao cadastrar: " + e.getMessage(), ToastUtil.ToastType.ERROR);
            toast.display();
            e.printStackTrace();
}
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void cbxPerfilInstitucionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPerfilInstitucionalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxPerfilInstitucionalActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
    LoginView telaLogin = new LoginView();

    telaLogin.setVisible(true);

    this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfActionPerformed

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
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cbxPerfilInstitucional;
    private javax.swing.JLabel jLabTitulo;
    private javax.swing.JPanel jPanelBotoes;
    private javax.swing.JPanel jPanelDegrade;
    private javax.swing.JPanel jPanelForm;
    private javax.swing.JPasswordField pwdConfirmarSenha;
    private javax.swing.JPasswordField pwdSenha;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}

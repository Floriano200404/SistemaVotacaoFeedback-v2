/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;

/**
 *
 * @author esten
 */

import br.edu.ifro.calama.votacaofeedback.util.AlinhamentoSelectUtil;
import br.edu.ifro.calama.votacaofeedback.util.RotatingArrowUtil;
import br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil; 
import br.edu.ifro.calama.votacaofeedback.util.RoundedPanelUtil; 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class PerfilView extends JDialog {

    private final Font FONT_INPUT = new Font("Segoe UI", Font.BOLD, 14);
    private final Font FONT_LABEL = new Font("Segoe UI", Font.BOLD, 16);
    
    private boolean popupVisivel = false;
    
    public PerfilView(JFrame parent, String nome, String email, String cpf, String matricula, String cursoAtual) {
        super(parent, true);

        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        
        RoundedPanelUtil mainPanel = new RoundedPanelUtil(25, new Color(245, 245, 245));
        mainPanel.setFocusable(true); // Permite que o painel receba foco
        mainPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainPanel.requestFocusInWindow();
            }
        });
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        
        setContentPane(mainPanel);

        JPanel headerPanel = createHeaderPanel(nome, email);
        JPanel formPanel = createFormPanel(nome, email, cpf, matricula, cursoAtual);
        JPanel footerPanel = createFooterPanel();
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }
    
    private JPanel createHeaderPanel(String nome, String email) {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.setOpaque(false);
        
        JLabel lblNome = new JLabel(nome);
        lblNome.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        JLabel lblEmail = new JLabel(email);
        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblEmail.setForeground(Color.GRAY);
        
        userInfoPanel.add(lblNome);
        userInfoPanel.add(lblEmail);
        
        RoundedButtonUtil btnClose = new RoundedButtonUtil();
        btnClose.setText("X");
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.setFont(new Font("Arial", Font.BOLD, 14));
        btnClose.setForeground(Color.GRAY);
        btnClose.setColor(new Color(245, 245, 245));
        btnClose.setColorOver(new Color(220, 220, 220));
        btnClose.setColorClick(new Color(210, 210, 210));
        btnClose.setRadius(999);
        btnClose.setBorderColor(new Color(245, 245, 245));
        
        btnClose.addActionListener(e -> dispose());
        
        headerPanel.add(userInfoPanel, BorderLayout.WEST);
        headerPanel.add(btnClose, BorderLayout.EAST);
        
        return headerPanel;
    }
    
    private JPanel createFormPanel(String nome, String email, String cpf, String matricula, String cursoAtual) {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(new EmptyBorder(15, 0, 15, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(new JSeparator(), gbc);

        gbc.gridwidth = 1;
        gbc.weightx = 1.0; 

        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblNome = new JLabel("Nome do Usuário:");
        lblNome.setFont(FONT_LABEL);
        formPanel.add(lblNome, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JTextField txtNome = new JTextField(nome, 20);
        styleTextField(txtNome);
        formPanel.add(txtNome, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblEmail = new JLabel("Email Institucional:");
        lblEmail.setFont(FONT_LABEL);
        formPanel.add(lblEmail, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JTextField txtEmail = new JTextField(email, 20);
        styleTextField(txtEmail);
        formPanel.add(txtEmail, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setFont(FONT_LABEL);
        formPanel.add(lblCpf, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JTextField txtCpf = new JTextField(cpf, 20);
        styleTextField(txtCpf);
        formPanel.add(txtCpf, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblMatricula = new JLabel("Matrícula:");
        lblMatricula.setFont(FONT_LABEL);
        formPanel.add(lblMatricula, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JTextField txtMatricula = new JTextField(matricula, 20);
        txtMatricula.setEditable(false);
        txtMatricula.setOpaque(false);
        txtMatricula.setBorder(null);
        txtMatricula.setHorizontalAlignment(JTextField.RIGHT);
        txtMatricula.setFont(FONT_INPUT);
        txtMatricula.setForeground(new Color(110, 110, 110));
        RoundedPanelUtil matriculaPanel = new RoundedPanelUtil(15, new Color(220, 220, 220));
        matriculaPanel.setLayout(new BorderLayout());
        matriculaPanel.add(txtMatricula, BorderLayout.CENTER);
        formPanel.add(matriculaPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel lblCurso = new JLabel("Curso:");
        lblCurso.setFont(FONT_LABEL);
        formPanel.add(lblCurso, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        
        gbc.anchor = GridBagConstraints.WEST;
        String[] cursos = {"Análise e Desenv. de Sistemas", "Engenharia Civil", "Engenharia Química", "Licenciatura em Física"};
        final JComboBox<String> comboCurso = new JComboBox<>(cursos);
        comboCurso.setFont(FONT_INPUT);
        comboCurso.setSelectedItem(cursoAtual);
        comboCurso.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        comboCurso.setRenderer(new AlinhamentoSelectUtil());
        comboCurso.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            @Override
            protected javax.swing.JButton createArrowButton() {
                return new javax.swing.JButton() {
                    @Override
                    public int getWidth() {
                        return 0;
                    }
                };
            }

            @Override
            public void paintCurrentValueBackground(java.awt.Graphics g, java.awt.Rectangle bounds, boolean hasFocus) {
                
            }
        });
        
        comboCurso.setBorder(null);
        
        
        final RotatingArrowUtil lblArrow = new RotatingArrowUtil();
        lblArrow.setForeground(new Color(100, 100, 100));
        lblArrow.setPreferredSize(new java.awt.Dimension(30, 0));
        lblArrow.setCursor(new Cursor(Cursor.HAND_CURSOR));

        lblArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (popupVisivel) {
                    comboCurso.setPopupVisible(false);
                } else {
                    comboCurso.showPopup();
                }
                e.consume();
            }
        });
        
        // Forçando o Hand Cursor na lista que abre (popup)
        comboCurso.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) {
                popupVisivel = true;
                lblArrow.rotateTo(180, 200);
            }
            @Override
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {
                popupVisivel = false;
                lblArrow.rotateTo(0, 200);
            }
            @Override
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {
                popupVisivel = false;
                lblArrow.rotateTo(0, 200);
            }
        });

        Object popup = comboCurso.getUI().getAccessibleChild(comboCurso, 0);
        if (popup instanceof javax.swing.plaf.basic.BasicComboPopup) {
            javax.swing.JList list = ((javax.swing.plaf.basic.BasicComboPopup) popup).getList();
            list.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                @Override
                public void mouseMoved(java.awt.event.MouseEvent e) {
                    list.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            });
        }

        RoundedPanelUtil comboPanel = new RoundedPanelUtil(15, Color.WHITE);
        comboPanel.setLayout(new BorderLayout());

        
        //comboCurso.setBorder(new EmptyBorder(0, 5, 0, 0));
        comboPanel.add(lblArrow, BorderLayout.EAST);
        comboPanel.add(comboCurso, BorderLayout.CENTER);
        formPanel.add(comboPanel, gbc);

        return formPanel;
}
    
    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setOpaque(false);
        
        RoundedButtonUtil btnSair = new RoundedButtonUtil();
        btnSair.setText("Sair");
        btnSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSair.setForeground(Color.WHITE);
        btnSair.setRadius(15);
        btnSair.setColor(new Color(200, 0, 0));
        btnSair.setColorOver(new Color(231, 76, 60));
        btnSair.setColorClick(new Color(192, 57, 43));
        btnSair.setBorderColor(new Color(200, 0, 0));
        btnSair.addActionListener(e -> System.out.println("Ação de Logout..."));
        btnSair.setPreferredSize(new java.awt.Dimension(100, 35));

        RoundedButtonUtil btnSalvar = new RoundedButtonUtil();
        btnSalvar.setText("Salvar");
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setRadius(15);
        btnSalvar.setColor(new Color(0, 149, 255));
        btnSalvar.setColorOver(new Color(52, 152, 219));
        btnSalvar.setColorClick(new Color(41, 128, 185));
        btnSalvar.setBorderColor(new Color(0, 149, 255));
        btnSalvar.addActionListener(e -> System.out.println("Ação de Salvar..."));
        btnSalvar.setPreferredSize(new java.awt.Dimension(100, 35));
        
        footerPanel.add(btnSair);
        footerPanel.add(btnSalvar);
        
        return footerPanel;
    }
    
    private void styleTextField(JTextField field) {
        field.setOpaque(false);
        field.setBorder(null);
        field.setHorizontalAlignment(JTextField.RIGHT);
        field.setFont(FONT_INPUT);
        field.setForeground(new Color(80, 80, 80));
    }
}

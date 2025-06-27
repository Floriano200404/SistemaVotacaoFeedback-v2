/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;

/**
 *
 * @author esten
 */

import br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil; 
import br.edu.ifro.calama.votacaofeedback.util.RoundedPanelUtil; 
import java.awt.BorderLayout;
import java.awt.Color;
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

    // Passamos os dados do usuário para o construtor para preencher a tela
    public PerfilView(JFrame parent, String nome, String email, String cpf, String matricula, String cursoAtual) {
        super(parent, true);

        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        
        RoundedPanelUtil mainPanel = new RoundedPanelUtil(25, new Color(245, 245, 245));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        
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
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(new JSeparator(), gbc);
        
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Nome do Usuário:"), gbc);
        gbc.gridx = 1;
        JTextField txtNome = new JTextField(nome, 20);
        formPanel.add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Email Institucional:"), gbc);
        gbc.gridx = 1;
        JTextField txtEmail = new JTextField(email, 20);
        formPanel.add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("CPF:"), gbc);
        gbc.gridx = 1;
        JTextField txtCpf = new JTextField(cpf, 20);
        formPanel.add(txtCpf, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Matrícula:"), gbc);
        gbc.gridx = 1;
        JTextField txtMatricula = new JTextField(matricula, 20);
        txtMatricula.setEditable(false);
        txtMatricula.setForeground(Color.GRAY);
        formPanel.add(txtMatricula, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Curso:"), gbc);
        gbc.gridx = 1;
        String[] cursos = {"Análise e Desenv. de Sistemas", "Engenharia Civil", "Licenciatura em Química", "Medicina"};
        JComboBox<String> comboCurso = new JComboBox<>(cursos);
        comboCurso.setSelectedItem(cursoAtual);
        formPanel.add(comboCurso, gbc);
        
        return formPanel;
    }
    
    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setOpaque(false);
        
        RoundedButtonUtil btnSair = new RoundedButtonUtil();
        btnSair.setText("Sair");
        btnSair.setForeground(Color.WHITE);
        btnSair.setRadius(15);
        btnSair.setColor(new Color(200, 0, 0));
        btnSair.setColorOver(new Color(231, 76, 60));
        btnSair.setColorClick(new Color(192, 57, 43));
        btnSair.setBorderColor(new Color(200, 0, 0));
        btnSair.addActionListener(e -> System.out.println("Ação de Logout..."));

        RoundedButtonUtil btnSalvar = new RoundedButtonUtil();
        btnSalvar.setText("Salvar");
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setRadius(15);
        btnSalvar.setColor(new Color(0, 149, 255));
        btnSalvar.setColorOver(new Color(52, 152, 219));
        btnSalvar.setColorClick(new Color(41, 128, 185));
        btnSalvar.setBorderColor(new Color(0, 149, 255));
        btnSalvar.addActionListener(e -> System.out.println("Ação de Salvar..."));
        
        footerPanel.add(btnSair);
        footerPanel.add(btnSalvar);
        
        return footerPanel;
    }
}

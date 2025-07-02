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
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
    
    
    public PerfilView(JFrame parent, String nome, String email, String cpf, String matricula, String cursoAtual) {
        super(parent, true);

        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        
        RoundedPanelUtil mainPanel = new RoundedPanelUtil(25, new Color(245, 245, 245));
        mainPanel.setFocusable(true);
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

        JLabel lblTitle = new JLabel("Perfil do Usuário");
        lblTitle.setFont(FONT_LABEL);
        lblTitle.setForeground(new Color(50, 50, 50));

        
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

        
        headerPanel.add(lblTitle, BorderLayout.WEST);
        headerPanel.add(btnClose, BorderLayout.EAST);
        
        return headerPanel;
    }
    
    private JPanel createDisabledFieldPanel(String text) {
       JTextField textField = new JTextField(text, 20);
       textField.setEditable(false);
       textField.setOpaque(false);
       textField.setBorder(null);
       textField.setHorizontalAlignment(JTextField.RIGHT);
       textField.setFont(FONT_INPUT);
       textField.setForeground(new Color(110, 110, 110));

       RoundedPanelUtil panel = new RoundedPanelUtil(15, new Color(220, 220, 220));
       panel.setLayout(new BorderLayout());
       panel.setBorder(new EmptyBorder(5, 10, 5, 10));
       panel.add(textField, BorderLayout.CENTER);

       return panel;
    }
    
    private JPanel createFormPanel(String nome, String email, String cpf, String matricula, String cursoAtual) {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(new EmptyBorder(15, 0, 15, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; 

        
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        formPanel.add(new JSeparator(), gbc); // --- Linha Separadora ---
        gbc.gridwidth = 1;

        
        gbc.gridx = 0; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;

        gbc.gridy = 1; formPanel.add(new JLabel("Nome do Usuário:"), gbc);
        gbc.gridy = 2; formPanel.add(new JLabel("Email Institucional:"), gbc);
        gbc.gridy = 3; formPanel.add(new JLabel("CPF:"), gbc);
        gbc.gridy = 4; formPanel.add(new JLabel("Matrícula:"), gbc);
        gbc.gridy = 5; formPanel.add(new JLabel("Curso:"), gbc);

        
        for(Component comp : formPanel.getComponents()){
            if(comp instanceof JLabel){
                comp.setFont(FONT_LABEL);
            }
        }
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        gbc.gridy = 1; formPanel.add(createDisabledFieldPanel(nome), gbc);
        gbc.gridy = 2; formPanel.add(createDisabledFieldPanel(email), gbc);
        gbc.gridy = 3; formPanel.add(createDisabledFieldPanel(cpf), gbc);
        gbc.gridy = 4; formPanel.add(createDisabledFieldPanel(matricula), gbc);
        gbc.gridy = 5; formPanel.add(createDisabledFieldPanel("Análise e Desenv. de Sistemas"), gbc);

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

    footerPanel.add(btnSair);

    return footerPanel;
    }
    
}

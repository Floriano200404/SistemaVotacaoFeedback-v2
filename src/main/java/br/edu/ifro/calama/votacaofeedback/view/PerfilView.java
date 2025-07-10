/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;

import br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil;
import br.edu.ifro.calama.votacaofeedback.util.RoundedPanelUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author SEJUS- 15206
 */
public class PerfilView extends JDialog {

    private static final Font FONT_INPUT = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font FONT_LABEL = new Font("Segoe UI", Font.BOLD, 16);
    private static final Color COLOR_BACKGROUND = new Color(245, 245, 245);
    private static final Color COLOR_TEXT_FIELD_BG = new Color(220, 220, 220);
    private static final Color COLOR_TEXT_FIELD_FG = new Color(110, 110, 110);
    private static final Color COLOR_LABEL_FG = new Color(50, 50, 50);

    /**
     * Construtor da tela de perfil.
     *
     * @param parent       O frame pai sobre o qual o diálogo será modal.
     * @param nome         Nome do usuário.
     * @param email        Email do usuário.
     * @param cpf          CPF do usuário.
     * @param matricula    Matrícula do usuário.
     * @param cursoAtual   Curso atual do usuário.
     * @param logoutAction Ação a ser executada quando o botão "Sair" for clicado.
     */
    public PerfilView(JFrame parent, String nome, String email, String cpf, String matricula, String cursoAtual, ActionListener logoutAction) {
        super(parent, true);
        initComponents(nome, email, cpf, matricula, cursoAtual, logoutAction);
    }

    private void initComponents(String nome, String email, String cpf, String matricula, String cursoAtual, ActionListener logoutAction) {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0)); // Fundo transparente para ver os cantos arredondados

        // Painel principal com cantos arredondados
        RoundedPanelUtil mainPanel = new RoundedPanelUtil(25, COLOR_BACKGROUND);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(25, 35, 25, 35));
        setContentPane(mainPanel);

        // Criação e adição dos painéis de conteúdo
        JPanel headerPanel = createHeaderPanel();
        JPanel formPanel = createFormPanel(nome, email, cpf, matricula, cursoAtual);
        JPanel footerPanel = createFooterPanel(logoutAction);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        pack(); // Ajusta o tamanho do diálogo ao conteúdo
        setLocationRelativeTo(getParent()); // Centraliza em relação ao pai
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        JLabel lblTitle = new JLabel("Perfil do Usuário");
        lblTitle.setFont(FONT_LABEL);
        lblTitle.setForeground(COLOR_LABEL_FG);

        RoundedButtonUtil btnClose = new RoundedButtonUtil();
        btnClose.setText("X");
        btnClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnClose.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnClose.setForeground(Color.GRAY);
        btnClose.setColor(COLOR_BACKGROUND);
        btnClose.setColorOver(new Color(220, 220, 220));
        btnClose.setColorClick(new Color(210, 210, 210));
        btnClose.setRadius(999);
        btnClose.setBorderColor(COLOR_BACKGROUND);
        btnClose.addActionListener(e -> dispose()); // dispose() é a forma correta de fechar um JDialog

        headerPanel.add(lblTitle, BorderLayout.WEST);
        headerPanel.add(btnClose, BorderLayout.EAST);

        return headerPanel;
    }

    private JPanel createFormPanel(String nome, String email, String cpf, String matricula, String cursoAtual) {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(new EmptyBorder(15, 0, 15, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        // Adiciona as linhas de informação de forma programática
        addInfoRow(formPanel, gbc, "Nome do Usuário:", nome);
        addInfoRow(formPanel, gbc, "Email Institucional:", email);
        addInfoRow(formPanel, gbc, "CPF:", cpf);
        addInfoRow(formPanel, gbc, "Matrícula:", matricula);
        addInfoRow(formPanel, gbc, "Curso:", cursoAtual); // Corrigido para usar o parâmetro

        return formPanel;
    }

    /**
     * Helper para adicionar uma linha de informação (Rótulo, Campo e Separador) ao painel.
     */
    private void addInfoRow(JPanel panel, GridBagConstraints gbc, String labelText, String valueText) {
        // --- Rótulo e Campo ---
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0; // Peso para o rótulo
        gbc.gridy++;
        
        JLabel label = new JLabel(labelText);
        label.setFont(FONT_LABEL);
        label.setForeground(COLOR_LABEL_FG);
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Peso para o campo, fazendo-o esticar
        panel.add(createDisabledFieldPanel(valueText), gbc);
        gbc.gridx = 0;
        
        // --- Separador ---
        gbc.gridy++;
        gbc.gridwidth = 2; // Ocupa as duas colunas
        gbc.insets = new Insets(5, 0, 5, 0); // Espaçamento vertical para o separador

        JSeparator separator = new JSeparator();
        separator.setForeground(COLOR_TEXT_FIELD_BG);
        panel.add(separator, gbc);
        
        gbc.insets = new Insets(10, 5, 10, 5); // Restaura o espaçamento padrão
    }

    private JPanel createDisabledFieldPanel(String text) {
        JTextField textField = new JTextField(text, 20);
        textField.setEditable(false);
        textField.setOpaque(false);
        textField.setBorder(null);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setFont(FONT_INPUT);
        textField.setForeground(COLOR_TEXT_FIELD_FG);

        RoundedPanelUtil panel = new RoundedPanelUtil(15, COLOR_TEXT_FIELD_BG);
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 10, 5, 10));
        panel.add(textField, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createFooterPanel(ActionListener logoutAction) {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setOpaque(false);

        RoundedButtonUtil btnSair = new RoundedButtonUtil();
        btnSair.setText("Sair");
        btnSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSair.setForeground(Color.WHITE);
        btnSair.setRadius(15);
        btnSair.setColor(new Color(200, 0, 0));
        btnSair.setColorOver(new Color(231, 76, 60));
        btnSair.setColorClick(new Color(192, 57, 43));
        btnSair.setBorderColor(new Color(200, 0, 0));
        btnSair.setPreferredSize(new Dimension(100, 35));
        
        // Adiciona a ação de logout que foi passada pelo construtor
        if (logoutAction != null) {
            btnSair.addActionListener(logoutAction);
        }

        footerPanel.add(btnSair);

        return footerPanel;
    }

    /**
     * Método main para testar o JDialog de forma independente.
     */
    public static void main(String[] args) {
        // Simula as dependências (substitua pelas suas classes reais se necessário para compilar)
        // class RoundedPanelUtil extends JPanel { public RoundedPanelUtil(int r, Color c) { setBackground(c); } }
        // class RoundedButtonUtil extends JButton { public void setRadius(int r){} public void setColor(Color c){} public void setColorOver(Color c){} public void setColorClick(Color c){} public void setBorderColor(Color c){} }

        // Executa a criação da GUI na Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            JFrame testFrame = new JFrame("Parent Frame");
            testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            testFrame.setSize(800, 600);
            testFrame.setLocationRelativeTo(null);
            //testFrame.setVisible(true); // Não precisa ser visível para ser pai

            // Define a ação de logout aqui, fora da View
            ActionListener logoutAction = e -> {
                System.out.println("Ação de Logout foi disparada!");
                // Aqui você colocaria a lógica real, como fechar a aplicação ou voltar para a tela de login
                JOptionPane.showMessageDialog(null, "Você foi deslogado!");
                System.exit(0);
            };

            // Instancia e exibe o PerfilView
            PerfilView perfilDialog = new PerfilView(
                testFrame,
                "Ada Lovelace",
                "ada.lovelace@institucional.edu.br",
                "123.456.789-00",
                "18421852",
                "Análise e Desenv. de Sistemas",
                logoutAction
            );
            perfilDialog.setVisible(true);
        });
    }
}

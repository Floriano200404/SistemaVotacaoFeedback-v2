/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;

import br.edu.ifro.calama.votacaofeedback.controller.VotacaoController;
import br.edu.ifro.calama.votacaofeedback.model.OpcaoVoto;
import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.model.Votacao;
import br.edu.ifro.calama.votacaofeedback.repository.OpcaoVotoRepository;
import br.edu.ifro.calama.votacaofeedback.service.VotacaoService;
import br.edu.ifro.calama.votacaofeedback.util.PlaceHolderUtil;
import br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil;
import br.edu.ifro.calama.votacaofeedback.util.ToastUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author floriano, ATHOS
 */
public class CriarVotacaoOpcoesView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CriarVotacaoOpcoesView.class.getName());
    private MenuPrincipalView menuPrincipal;
    private final Usuario usuarioLogado;
    private final Votacao votacao;
    private boolean isEditMode = false;
    

    private final List<JPanel> paineisDeOpcao = new ArrayList<>();
    private final int MAX_OPCOES = 5;
    private javax.swing.JPanel painelOpcoesContainer;

    public CriarVotacaoOpcoesView(MenuPrincipalView menu, Usuario usuario, Votacao votacao) throws Exception {
        initComponents();
        aplicarEstilosPersonalizados();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.painelOpcoesContainer = this.painelOpcoesContainer1;
        painelOpcoesContainer1.removeAll();
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.usuarioLogado = usuario;
        this.menuPrincipal = menu;
        this.votacao = votacao;
        painelOpcoesContainer1.setLayout(new javax.swing.BoxLayout(painelOpcoesContainer1, javax.swing.BoxLayout.Y_AXIS));
        if (this.usuarioLogado != null) {
            labelNomeUsuario.setText(this.usuarioLogado.getNome());
        }
        java.awt.event.MouseAdapter focusListener = new java.awt.event.MouseAdapter() {
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
                formCardPanel.requestFocusInWindow();
            }
        };
        this.PainelConteudo.addMouseListener(focusListener);
        this.formCardPanel.addMouseListener(focusListener);
        this.painelOpcoesContainer.addMouseListener(focusListener);
        this.painelHeader.addMouseListener(focusListener);
        configurarTela();
        
    }
    
    private void aplicarEstilosPersonalizados() {
        final int ALTURA_PADRAO = 35;
        
        javax.swing.border.Border bordaArredondadaPreta = new br.edu.ifro.calama.votacaofeedback.util.RoundedVotacoesUtil(15, Color.BLACK);

        txtPergunta.setBackground(Color.WHITE);
        txtPergunta.setForeground(Color.BLACK);
        txtPergunta.setPreferredSize(new Dimension(txtPergunta.getPreferredSize().width, ALTURA_PADRAO));
        txtPergunta.setBorder(bordaArredondadaPreta);
        txtPergunta.setMargin(new java.awt.Insets(2, 10, 2, 10));
        PlaceHolderUtil.setPlaceholder(txtPergunta, "Escreva a pergunta da votação.");

        javax.swing.border.Border bordaBotaoVoltar = new br.edu.ifro.calama.votacaofeedback.util.RoundedVotacoesUtil(15, new Color(0x6A6A6A));
        btnVoltar.setText("VOLTAR");
        ((br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil) btnVoltar).setColor(new Color(0x6A6A6A));
        ((br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil) btnVoltar).setColorOver(new Color(0x8A8A8A));
        ((br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil) btnVoltar).setColorClick(new Color(0x5A5A5A));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setPreferredSize(new Dimension(120, ALTURA_PADRAO));
        btnVoltar.setBorder(bordaBotaoVoltar);
        btnVoltar.setFocusPainted(false);

        javax.swing.border.Border bordaBotaoFinalizar = new br.edu.ifro.calama.votacaofeedback.util.RoundedVotacoesUtil(15, new Color(0x0095FF));
        btnFinalizar.setText("FINALIZAR");
        ((br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil) btnFinalizar).setColor(new Color(0x0095FF));
        ((br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil) btnFinalizar).setColorOver(new Color(0x33ADFF));
        ((br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil) btnFinalizar).setColorClick(new Color(0x0078CC));
        btnFinalizar.setForeground(Color.WHITE);
        btnFinalizar.setBackground(new Color(0x0095FF));
        btnFinalizar.setPreferredSize(new Dimension(120, ALTURA_PADRAO));
        btnFinalizar.setBorder(bordaBotaoFinalizar);
        btnFinalizar.setFocusPainted(false);

        btnAdicionarOpcao.setText("Adicionar mais uma opção");
        btnAdicionarOpcao.setForeground(Color.GRAY);
        btnAdicionarOpcao.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnAdicionarOpcao.setOpaque(false);
        btnAdicionarOpcao.setContentAreaFilled(false);
        btnAdicionarOpcao.setBorderPainted(false);
        btnAdicionarOpcao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnAdicionarOpcao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdicionarOpcao.setForeground(new Color(0, 102, 204));
                btnAdicionarOpcao.setText("<html><u>Adicionar mais uma opção</u></html>");
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAdicionarOpcao.setForeground(Color.GRAY);
                btnAdicionarOpcao.setText("Adicionar mais uma opção");
            }
        });
    }
    private void configurarTela() throws Exception {
        if (votacao != null && votacao.getIdVotacao() > 0) {
            this.isEditMode = true;
        }

        if (isEditMode) {
            TituloPrincipal.setText("EDITAR VOTAÇÃO - OPÇÕES");
            btnFinalizar.setText("SALVAR");
            preencherDadosExistentes();
        } else {
            TituloPrincipal.setText("CRIAR VOTAÇÃO - OPÇÕES");
            btnFinalizar.setText("FINALIZAR");
            adicionarNovaOpcao(false, "");
            adicionarNovaOpcao(false, "");
        }
        atualizarVisibilidadeBotaoAdicionar();
    }

    private void preencherDadosExistentes() throws Exception {
        txtPergunta.setText(votacao.getPergunta());
        txtPergunta.setForeground(Color.BLACK);
        OpcaoVotoRepository opcaoRepo = new OpcaoVotoRepository();
        List<OpcaoVoto> opcoesExistentes = opcaoRepo.buscarPorIdVotacao(votacao.getIdVotacao());
        for (int i = 0; i < opcoesExistentes.size(); i++) {
            boolean isRemovivel = i >= 2;
            adicionarNovaOpcao(isRemovivel, opcoesExistentes.get(i).getDescricao());
        }
    }

    private void adicionarNovaOpcao(boolean removivel, String textoInicial) {
        if (paineisDeOpcao.size() >= MAX_OPCOES) return;

        JPanel painelOpcao = new JPanel();
        painelOpcao.setLayout(new javax.swing.BoxLayout(painelOpcao, javax.swing.BoxLayout.Y_AXIS));
        painelOpcao.setOpaque(false);
        painelOpcao.setBorder(new EmptyBorder(5, 0, 5, 0));

        int novoIndice = paineisDeOpcao.size() + 1;

        JLabel label = new JLabel("Opção " + novoIndice + ":");
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel painelCampoEBotao = new JPanel(new BorderLayout(10, 0));
        painelCampoEBotao.setOpaque(false);

        JTextField textField = new JTextField(); 
        textField.setName("txtOpcao" + novoIndice);

        final int ALTURA_PADRAO = 35;
        textField.setMinimumSize(new Dimension(10, ALTURA_PADRAO));
        textField.setPreferredSize(new Dimension(10, ALTURA_PADRAO));
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, ALTURA_PADRAO));

        javax.swing.border.Border bordaArredondadaPreta = new br.edu.ifro.calama.votacaofeedback.util.RoundedVotacoesUtil(15, Color.BLACK);
        textField.setBackground(Color.WHITE);
        textField.setBorder(bordaArredondadaPreta);
        textField.setMargin(new java.awt.Insets(2, 10, 2, 10));

        PlaceHolderUtil.setPlaceholder(textField, "Digite o texto da opção");

        if (!textoInicial.trim().isEmpty()) {
            textField.setText(textoInicial);
            textField.setForeground(Color.BLACK);
        }

        painelCampoEBotao.add(textField, BorderLayout.CENTER);

        if (removivel) {
            // ... (seu código do botão "X" não muda)
            RoundedButtonUtil btnClose = new RoundedButtonUtil();
            btnClose.setText("X");
            btnClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnClose.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnClose.setForeground(Color.GRAY);
            btnClose.setColor(Color.WHITE);
            btnClose.setColorOver(new Color(242, 242, 242));
            btnClose.setColorClick(new Color(220, 220, 220));
            btnClose.setRadius(999);
            btnClose.setBorderColor(Color.WHITE);
            btnClose.setPreferredSize(new Dimension(30, 30));
            btnClose.addActionListener(e -> removerOpcao(painelOpcao));
            painelCampoEBotao.add(btnClose, BorderLayout.EAST);
        }

        painelCampoEBotao.setAlignmentX(Component.LEFT_ALIGNMENT);

        painelOpcao.add(label);
        painelOpcao.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 4)));
        painelOpcao.add(painelCampoEBotao);

        paineisDeOpcao.add(painelOpcao);
        painelOpcoesContainer.add(painelOpcao);

        atualizarLayoutConteudo();
        atualizarVisibilidadeBotaoAdicionar();
    }

    private void removerOpcao(JPanel painelOpcao) {
        painelOpcoesContainer.remove(painelOpcao);
        paineisDeOpcao.remove(painelOpcao);

        for (int i = 0; i < paineisDeOpcao.size(); i++) {
            JPanel panel = paineisDeOpcao.get(i);
            for (Component comp : panel.getComponents()) {
                if (comp instanceof JLabel) {
                    ((JLabel) comp).setText("Opção " + (i + 1) + ":");
                    break;
                }
            }
        }

        atualizarLayoutConteudo();
        atualizarVisibilidadeBotaoAdicionar();
    }

    private void atualizarLayoutConteudo() {
        painelOpcoesContainer.revalidate();
        painelOpcoesContainer.repaint();
    }

    private void atualizarVisibilidadeBotaoAdicionar() {
        btnAdicionarOpcao.setVisible(paineisDeOpcao.size() < MAX_OPCOES);
    }
    
    
    public void exibirMensagem(String mensagem) {
        ToastUtil toast = new ToastUtil(this, mensagem, ToastUtil.ToastType.ERROR, ToastUtil.ToastPosition.TOP_RIGHT);
        toast.display();
    }

    public void exibirMensagemDeSucesso(String mensagem) {
        ToastUtil toast = new ToastUtil(this, mensagem, ToastUtil.ToastType.SUCCESS, ToastUtil.ToastPosition.TOP_RIGHT);
        toast.display();
    }
    
    private void navegarParaMenuPrincipal() {
        new Timer(1500, e -> {
            try {
            MenuPrincipalView menuPrincipal = new MenuPrincipalView(this.usuarioLogado);
            menuPrincipal.setLocationRelativeTo(null);
            menuPrincipal.setVisible(true);

            this.dispose();

        } catch (Exception ex) {
            exibirMensagem("Erro ao carregar o menu principal: " + ex.getMessage());
            ex.printStackTrace();
        }
    }){{setRepeats(false);}}.start();
    }
    
    private JTextField findTextFieldInPanel(JPanel container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JTextField) {
                return (JTextField) c;
            }
            if (c instanceof JPanel) {
                JTextField found = findTextFieldInPanel((JPanel) c);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
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

        painelHeader = new javax.swing.JPanel();
        painelHeaderEsquerda = new javax.swing.JPanel();
        labelLogo = new javax.swing.JLabel();
        painelHeaderDireita = new javax.swing.JPanel();
        labelNomeUsuario = new javax.swing.JLabel();
        labelIconePerfil = new javax.swing.JLabel();
        PainelConteudo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        formCardPanel = new javax.swing.JPanel();
        TituloPrincipal = new javax.swing.JLabel();
        TituloP = new javax.swing.JLabel();
        txtPergunta = new javax.swing.JTextField();
        painelOpcoesContainer1 = new javax.swing.JPanel();
        TituloO1 = new javax.swing.JLabel();
        txtOpcao1 = new javax.swing.JTextField();
        TituloO2 = new javax.swing.JLabel();
        txtOpcao2 = new javax.swing.JTextField();
        TituloO3 = new javax.swing.JLabel();
        txtOpcao3 = new javax.swing.JTextField();
        TituloO4 = new javax.swing.JLabel();
        txtOpcao4 = new javax.swing.JTextField();
        TituloO5 = new javax.swing.JLabel();
        txtOpcao5 = new javax.swing.JTextField();
        btnAdicionarOpcao = new javax.swing.JButton();
        botoesInferiores = new javax.swing.JPanel();
        btnVoltar = new br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil()  ;
        btnFinalizar = new br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil()  ;

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        painelHeader.setBackground(new java.awt.Color(0, 0, 51));
        painelHeader.setPreferredSize(new java.awt.Dimension(100, 50));
        painelHeader.setLayout(new java.awt.BorderLayout());

        painelHeaderEsquerda.setOpaque(false);
        painelHeaderEsquerda.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 30, 5));

        labelLogo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelLogo.setForeground(new java.awt.Color(255, 255, 255));
        labelLogo.setText("OCTACORE");
        labelLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelLogoMouseClicked(evt);
            }
        });
        painelHeaderEsquerda.add(labelLogo);

        painelHeader.add(painelHeaderEsquerda, java.awt.BorderLayout.LINE_START);

        painelHeaderDireita.setOpaque(false);
        painelHeaderDireita.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 20, 5));

        labelNomeUsuario.setFont(new java.awt.Font("Silom", 0, 14)); // NOI18N
        labelNomeUsuario.setForeground(new java.awt.Color(255, 255, 255));
        labelNomeUsuario.setText("Nome Usuario");
        painelHeaderDireita.add(labelNomeUsuario);

        labelIconePerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user.png"))); // NOI18N
        labelIconePerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelIconePerfilMouseClicked(evt);
            }
        });
        painelHeaderDireita.add(labelIconePerfil);

        painelHeader.add(painelHeaderDireita, java.awt.BorderLayout.LINE_END);

        getContentPane().add(painelHeader, java.awt.BorderLayout.PAGE_START);

        PainelConteudo.setLayout(new java.awt.GridBagLayout());

        formCardPanel.setBackground(new java.awt.Color(255, 255, 255));
        formCardPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 30, 30));
        formCardPanel.setMinimumSize(new java.awt.Dimension(0, 0));
        formCardPanel.setLayout(new java.awt.GridBagLayout());

        TituloPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TituloPrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TituloPrincipal.setText("CRIAR VOTAÇÃO - OPÇÕES");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        formCardPanel.add(TituloPrincipal, gridBagConstraints);

        TituloP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        TituloP.setText("Pergunta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        formCardPanel.add(TituloP, gridBagConstraints);

        txtPergunta.setMinimumSize(new java.awt.Dimension(100, 22));
        txtPergunta.setPreferredSize(new java.awt.Dimension(100, 22));
        txtPergunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPerguntaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        formCardPanel.add(txtPergunta, gridBagConstraints);

        painelOpcoesContainer1.setBackground(new java.awt.Color(255, 255, 255));
        painelOpcoesContainer1.setLayout(new javax.swing.BoxLayout(painelOpcoesContainer1, javax.swing.BoxLayout.Y_AXIS));

        TituloO1.setText("Opção 1");
        painelOpcoesContainer1.add(TituloO1);
        painelOpcoesContainer1.add(txtOpcao1);

        TituloO2.setText("Opção 2");
        painelOpcoesContainer1.add(TituloO2);
        painelOpcoesContainer1.add(txtOpcao2);

        TituloO3.setText("Opção 3");
        painelOpcoesContainer1.add(TituloO3);

        txtOpcao3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOpcao3ActionPerformed(evt);
            }
        });
        painelOpcoesContainer1.add(txtOpcao3);

        TituloO4.setText("Opção 4");
        painelOpcoesContainer1.add(TituloO4);
        painelOpcoesContainer1.add(txtOpcao4);

        TituloO5.setText("Opção 5");
        painelOpcoesContainer1.add(TituloO5);
        painelOpcoesContainer1.add(txtOpcao5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 15, 0);
        formCardPanel.add(painelOpcoesContainer1, gridBagConstraints);

        btnAdicionarOpcao.setLabel("Adicionar Opção");
        btnAdicionarOpcao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarOpcaoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 20, 0);
        formCardPanel.add(btnAdicionarOpcao, gridBagConstraints);

        botoesInferiores.setOpaque(false);
        botoesInferiores.setLayout(new java.awt.GridLayout(1, 2, 30, 0));

        btnVoltar.setText("VOLTAR");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });
        botoesInferiores.add(btnVoltar);

        btnFinalizar.setText("FINALIZAR");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });
        botoesInferiores.add(btnFinalizar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 50);
        formCardPanel.add(botoesInferiores, gridBagConstraints);

        jScrollPane1.setViewportView(formCardPanel);

        PainelConteudo.add(jScrollPane1, new java.awt.GridBagConstraints());

        getContentPane().add(PainelConteudo, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void labelLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelLogoMouseClicked
        try {
        MenuPrincipalView telaPrincipal = new MenuPrincipalView(this.usuarioLogado);
        telaPrincipal.setLocationRelativeTo(null);
        telaPrincipal.setVisible(true);
        
        this.dispose();

        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao carregar o menu principal: " + e.getMessage(), "Erro de Conexão", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_labelLogoMouseClicked

    private void labelIconePerfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconePerfilMouseClicked
        ActionListener acaoDeLogout = e -> {
        new LoginView().setVisible(true);
        this.dispose();
        System.out.println("Logout realizado. Janela principal fechada.");
    };

    PerfilView perfil = new PerfilView(
        this,
        this.usuarioLogado.getNome(),
        this.usuarioLogado.getEmail(),
        this.usuarioLogado.getCpf(),
        this.usuarioLogado.getMatricula(),
        this.usuarioLogado.getCurso(),
        acaoDeLogout
    );
    perfil.setVisible(true);
    }//GEN-LAST:event_labelIconePerfilMouseClicked

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        
        String pergunta = txtPergunta.getText().trim();
        String placeholderPergunta = "Escreva a pergunta da votação.";
        if (pergunta.isEmpty() || pergunta.equals(placeholderPergunta)) {
            exibirMensagem("O campo 'Pergunta' é obrigatório.");
            return;
        }
        this.votacao.setPergunta(pergunta);

        List<String> opcoesValidas = new ArrayList<>();
        String placeholderOpcao = "Digite o texto da opção";

        for (JPanel painelOpcao : paineisDeOpcao) {
            JTextField textFieldDaOpcao = findTextFieldInPanel(painelOpcao);

            if (textFieldDaOpcao != null) {
                String textoOpcao = textFieldDaOpcao.getText().trim();

                if (!textoOpcao.isEmpty() && !textoOpcao.equals(placeholderOpcao)) {
                    opcoesValidas.add(textoOpcao);
                }
            }
        }

        if (opcoesValidas.size() < 2) {
            exibirMensagem("Preencha pelo menos as 2 primeiras opções.");
            return;
        }

        VotacaoController controller = new VotacaoController();
        OpcaoVotoRepository opcaoRepo = new OpcaoVotoRepository();
        VotacaoService service = new VotacaoService();

        if (isEditMode) {
            try {
                controller.atualizarVotacao(this.votacao);
                service.sincronizarOpcoes(this.votacao.getIdVotacao(), opcoesValidas);
                exibirMensagemDeSucesso("Votação atualizada com sucesso!");
                navegarParaMenuPrincipal();

            } catch (Exception e) {
                exibirMensagem("Erro ao atualizar a votação: " + e.getMessage());
                logger.log(java.util.logging.Level.SEVERE, "Erro ao atualizar votação", e);
            }
        } else {
            try {
                int novoId = controller.criarVotacao(this.votacao);
                if (novoId > 0) {
                    for (String descricaoOpcao : opcoesValidas) {
                        OpcaoVoto op = new OpcaoVoto();
                        op.setDescricao(descricaoOpcao);
                        op.setIdVotacao(novoId);
                        opcaoRepo.criar(op);
                    }
                    exibirMensagemDeSucesso("Votação criada com sucesso!");
                    navegarParaMenuPrincipal();
                } else {
                    exibirMensagem("Falha ao salvar os dados principais da votação.");
                }
            } catch (Exception e) {
                exibirMensagem("Erro ao criar a votação: " + e.getMessage());
                logger.log(java.util.logging.Level.SEVERE, "Erro ao criar votação", e);
            }
        }
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        CriarVotacaoView telaCriarVotacao = new CriarVotacaoView(this.menuPrincipal, this.usuarioLogado, this.votacao, this.isEditMode);
        telaCriarVotacao.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnAdicionarOpcaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarOpcaoActionPerformed
        if (paineisDeOpcao.size() < MAX_OPCOES) {
            adicionarNovaOpcao(true, "");
        } else {
            exibirMensagem("Limite máximo de 5 opções atingido.");
        }
    }//GEN-LAST:event_btnAdicionarOpcaoActionPerformed

    private void txtOpcao3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOpcao3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOpcao3ActionPerformed

    private void txtPerguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPerguntaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPerguntaActionPerformed
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PainelConteudo;
    private javax.swing.JLabel TituloO1;
    private javax.swing.JLabel TituloO2;
    private javax.swing.JLabel TituloO3;
    private javax.swing.JLabel TituloO4;
    private javax.swing.JLabel TituloO5;
    private javax.swing.JLabel TituloP;
    private javax.swing.JLabel TituloPrincipal;
    private javax.swing.JPanel botoesInferiores;
    private javax.swing.JButton btnAdicionarOpcao;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JPanel formCardPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelIconePerfil;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JLabel labelNomeUsuario;
    private javax.swing.JPanel painelHeader;
    private javax.swing.JPanel painelHeaderDireita;
    private javax.swing.JPanel painelHeaderEsquerda;
    private javax.swing.JPanel painelOpcoesContainer1;
    private javax.swing.JTextField txtOpcao1;
    private javax.swing.JTextField txtOpcao2;
    private javax.swing.JTextField txtOpcao3;
    private javax.swing.JTextField txtOpcao4;
    private javax.swing.JTextField txtOpcao5;
    private javax.swing.JTextField txtPergunta;
    // End of variables declaration//GEN-END:variables

}
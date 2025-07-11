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
import br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil;
import br.edu.ifro.calama.votacaofeedback.util.ToastUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author floriano, ATHOS
 */
public class CriarVotacaoOpcoesView extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CriarVotacaoOpcoesView.class.getName());
    private final Usuario usuarioLogado;
    private final Votacao votacao;
    private boolean isEditMode = false;

    private JPanel painelOpcoesContainer;
    private final List<JPanel> paineisDeOpcao = new ArrayList<>();
    private final int MAX_OPCOES = 5;
    private final int ANIMATION_DURATION = 150;
    private final int OPTION_PANEL_HEIGHT = 45;

    public CriarVotacaoOpcoesView(Usuario usuario, Votacao votacao) throws Exception {
        initComponents();
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.usuarioLogado = usuario;
        this.votacao = votacao;

        if (this.usuarioLogado != null) {
            labelNomeUsuario.setText(this.usuarioLogado.getNome());
        }

        configurarFormulario();
        inicializarMenuLateral();
        configurarTela();
        
        this.setMinimumSize(new Dimension(800, 600));
        this.setLocationRelativeTo(null);
    }

    private void configurarFormulario() {
        // O painel dentro do JScrollPane deve se chamar 'painelFormulario' no modo Design.
        painelFormulario.setLayout(new GridBagLayout());
        painelFormulario.setBackground(new Color(245, 245, 245)); // Um cinza claro suave

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Título Principal
        gbc.insets = new Insets(20, 10, 10, 10);
        TituloPrincipal.setFont(new Font("Segoe UI", Font.BOLD, 20));
        TituloPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
        painelFormulario.add(TituloPrincipal, gbc);

        // Label Pergunta
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(15, 10, 0, 10);
        TituloP.setFont(new Font("Segoe UI", Font.BOLD, 14));
        painelFormulario.add(TituloP, gbc);

        // Campo de Texto Pergunta
        gbc.insets = new Insets(5, 10, 10, 10);
        painelFormulario.add(txtPergunta, gbc);

        // Container das Opções
        painelOpcoesContainer = new JPanel();
        painelOpcoesContainer.setLayout(new BoxLayout(painelOpcoesContainer, BoxLayout.Y_AXIS));
        painelOpcoesContainer.setOpaque(false);
        painelFormulario.add(painelOpcoesContainer, gbc);

        // Botão Adicionar Opção
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 10, 15, 10);
        btnAdicionarOpcao.setPreferredSize(new Dimension(200, 35));
        painelFormulario.add(btnAdicionarOpcao, gbc);

        // Painel para os botões Voltar e Finalizar
        JPanel painelBotoesAcao = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        painelBotoesAcao.setOpaque(false);
        btnVoltar.setPreferredSize(new Dimension(150, 35));
        btnFinalizar.setPreferredSize(new Dimension(150, 35));
        painelBotoesAcao.add(btnVoltar);
        painelBotoesAcao.add(btnFinalizar);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 20, 10);
        painelFormulario.add(painelBotoesAcao, gbc);

        // Esconde os componentes antigos que estavam soltos no Frame
        java.util.List<Component> componentesParaEsconder = java.util.Arrays.asList(
            TituloO1, TituloO2, TituloO3, TituloO4, TituloO5,
            txtOpcao1, txtOpcao2, txtOpcao3, txtOpcao4, txtOpcao5
        );
        componentesParaEsconder.forEach(c -> c.setVisible(false));
    }

    // O MÉTODO inicializarLayoutDinamico() FOI REMOVIDO DAQUI.

    private void configurarTela() throws Exception {
        if (votacao != null && votacao.getIdVotacao() > 0) {
            this.isEditMode = true;
        }

        if (isEditMode) {
            TituloPrincipal.setText("EDITAR OPÇÕES DA VOTAÇÃO");
            btnFinalizar.setText("Salvar Alterações");
            preencherDadosExistentes();
        } else {
            TituloPrincipal.setText("CRIAR VOTAÇÃO - OPÇÕES");
            btnFinalizar.setText("Finalizar");
            adicionarNovaOpcao(false, "");
            adicionarNovaOpcao(false, "");
        }
        atualizarVisibilidadeBotaoAdicionar();
    }

    private void preencherDadosExistentes() throws Exception {
        txtPergunta.setText(votacao.getPergunta());
        OpcaoVotoRepository opcaoRepo = new OpcaoVotoRepository();
        List<OpcaoVoto> opcoesExistentes = opcaoRepo.buscarPorIdVotacao(votacao.getIdVotacao());
        for (int i = 0; i < opcoesExistentes.size(); i++) {
            boolean isRemovivel = i >= 2;
            adicionarNovaOpcao(isRemovivel, opcoesExistentes.get(i).getDescricao());
        }
    }

    private void adicionarNovaOpcao(boolean removivel, String textoInicial) {
        if (paineisDeOpcao.size() >= MAX_OPCOES) return;

        int novoIndice = paineisDeOpcao.size() + 1;
        JPanel painelLinha = new JPanel(new BorderLayout(10, 0));
        painelLinha.setOpaque(false);
        painelLinha.setBorder(new EmptyBorder(5, 0, 5, 0));
        
        JLabel label = new JLabel("Opção " + novoIndice + ":");
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        JTextField textField = new JTextField(textoInicial);
        textField.setName("txtOpcao" + novoIndice);
        
        painelLinha.add(label, BorderLayout.WEST);
        painelLinha.add(textField, BorderLayout.CENTER);

        if (removivel) {
            RoundedButtonUtil btnClose = new RoundedButtonUtil();
            btnClose.setText("X");
            btnClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnClose.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnClose.setForeground(Color.GRAY);
            btnClose.setColor(painelFormulario.getBackground());
            btnClose.setColorOver(new Color(220, 220, 220));
            btnClose.setColorClick(new Color(210, 210, 210));
            btnClose.setRadius(999);
            btnClose.setBorderColor(painelFormulario.getBackground());
            btnClose.setPreferredSize(new Dimension(30, 30));
            btnClose.addActionListener(e -> removerOpcaoComAnimacao(painelLinha));
            painelLinha.add(btnClose, BorderLayout.EAST);
        }

        paineisDeOpcao.add(painelLinha);
        animarAdicao(painelLinha);
        atualizarVisibilidadeBotaoAdicionar();
    }

    private void animarAdicao(JPanel painelOpcional) {
        Dimension startSize = new Dimension(painelOpcional.getPreferredSize().width, 0);
        painelOpcional.setPreferredSize(startSize);
        painelOpcional.setMaximumSize(startSize);
        painelOpcoesContainer.add(painelOpcional);

        Timer timer = new Timer(ANIMATION_DURATION / OPTION_PANEL_HEIGHT, e -> {
            Dimension currentSize = painelOpcional.getPreferredSize();
            int newHeight = currentSize.height + 2; // Aumentar o passo da animação
            
            if (newHeight >= OPTION_PANEL_HEIGHT) {
                ((Timer) e.getSource()).stop();
                painelOpcional.setPreferredSize(null);
                painelOpcional.setMaximumSize(new Dimension(Short.MAX_VALUE, OPTION_PANEL_HEIGHT));
                atualizarLayoutConteudo();
            } else {
                painelOpcional.setPreferredSize(new Dimension(currentSize.width, newHeight));
                painelOpcional.setMaximumSize(new Dimension(currentSize.width, newHeight));
                atualizarLayoutConteudo();
            }
        });
        timer.start();
    }

    private void removerOpcaoComAnimacao(JPanel painelOpcao) {
        Timer timer = new Timer(ANIMATION_DURATION / OPTION_PANEL_HEIGHT, e -> {
            Dimension currentSize = painelOpcao.getPreferredSize();
            int newHeight = currentSize.height - 2; // Aumentar o passo da animação

            if (newHeight <= 0) {
                ((Timer) e.getSource()).stop();
                painelOpcoesContainer.remove(painelOpcao);
                paineisDeOpcao.remove(painelOpcao);
                
                for (int i = 0; i < paineisDeOpcao.size(); i++) {
                    JPanel panel = paineisDeOpcao.get(i);
                    JLabel label = (JLabel) panel.getComponent(0);
                    label.setText("Opção " + (i + 1) + ":");
                }
                
                atualizarVisibilidadeBotaoAdicionar();
                atualizarLayoutConteudo();
            } else {
                painelOpcao.setPreferredSize(new Dimension(currentSize.width, newHeight));
                painelOpcao.setMaximumSize(new Dimension(currentSize.width, newHeight));
                atualizarLayoutConteudo();
            }
        });
        timer.start();
    }

    private void atualizarLayoutConteudo() {
        painelOpcoesContainer.revalidate();
        painelOpcoesContainer.repaint();
        painelFormulario.revalidate();
        painelFormulario.repaint();
    }

    private void atualizarVisibilidadeBotaoAdicionar() {
        btnAdicionarOpcao.setVisible(paineisDeOpcao.size() < MAX_OPCOES);
    }

    // O MÉTODO setVisible() FOI REMOVIDO DAQUI.

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
            new MenuPrincipalView(this.usuarioLogado).setVisible(true);
            this.dispose();
        }){{setRepeats(false);}}.start();
    }
    
    // ... COLE O RESTANTE DOS SEUS MÉTODOS A PARTIR DAQUI ...
    // (initComponents, labelIconeMenuMouseClicked, btnFinalizarActionPerformed, etc.)

    //<editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        painelHeader = new javax.swing.JPanel();
        painelHeaderEsquerda = new javax.swing.JPanel();
        labelIconeMenu = new javax.swing.JLabel();
        labelLogo = new javax.swing.JLabel();
        painelHeaderDireita = new javax.swing.JPanel();
        labelNomeUsuario = new javax.swing.JLabel();
        labelIconePerfil = new javax.swing.JLabel();
        painelSidebar = new javax.swing.JPanel();
        menutxt = new javax.swing.JLabel();
        criarVotacao = new javax.swing.JButton();
        participarVotacao = new javax.swing.JButton();
        gerenciaVotacao = new javax.swing.JButton();
        aprovarVotacao = new javax.swing.JButton();
        votoArquivado = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        PainelConteudo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        painelFormulario = new javax.swing.JPanel();
        TituloPrincipal = new javax.swing.JLabel();
        TituloP = new javax.swing.JLabel();
        txtPergunta = new javax.swing.JTextField();
        btnAdicionarOpcao = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        painelHeader.setBackground(new java.awt.Color(0, 0, 51));
        painelHeader.setPreferredSize(new java.awt.Dimension(100, 50));
        painelHeader.setLayout(new java.awt.BorderLayout());

        painelHeaderEsquerda.setOpaque(false);
        painelHeaderEsquerda.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 30, 5));

        labelIconeMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/menu.png"))); // NOI18N
        labelIconeMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelIconeMenuMouseClicked(evt);
            }
        });
        painelHeaderEsquerda.add(labelIconeMenu);

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

        painelSidebar.setBackground(new java.awt.Color(255, 255, 255));
        painelSidebar.setPreferredSize(new java.awt.Dimension(230, 0));
        painelSidebar.setLayout(new javax.swing.BoxLayout(painelSidebar, javax.swing.BoxLayout.Y_AXIS));
        painelSidebar.add(menutxt);

        criarVotacao.setText("Criar Votação");
        criarVotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarVotacaoActionPerformed(evt);
            }
        });
        painelSidebar.add(criarVotacao);

        participarVotacao.setText("Participar de Votação ");
        participarVotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                participarVotacaoActionPerformed(evt);
            }
        });
        painelSidebar.add(participarVotacao);

        gerenciaVotacao.setText("Gerenciar Votação");
        gerenciaVotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gerenciaVotacaoActionPerformed(evt);
            }
        });
        painelSidebar.add(gerenciaVotacao);

        aprovarVotacao.setText("Aprovar Votações\n");
        aprovarVotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aprovarVotacaoActionPerformed(evt);
            }
        });
        painelSidebar.add(aprovarVotacao);

        votoArquivado.setText("Votações Arquivadas");
        painelSidebar.add(votoArquivado);
        painelSidebar.add(filler1);

        getContentPane().add(painelSidebar, java.awt.BorderLayout.LINE_START);

        PainelConteudo.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout painelFormularioLayout = new javax.swing.GroupLayout(painelFormulario);
        painelFormulario.setLayout(painelFormularioLayout);
        painelFormularioLayout.setHorizontalGroup(
            painelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1277, Short.MAX_VALUE)
        );
        painelFormularioLayout.setVerticalGroup(
            painelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 755, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(painelFormulario);

        PainelConteudo.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(PainelConteudo, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>                        

    private void labelIconeMenuMouseClicked(java.awt.event.MouseEvent evt) {                                            
        new Thread(() -> {
            if (painelSidebar.getWidth() > 0) {
                try {
                    for (int i = painelSidebar.getWidth(); i >= 0; i--) {
                        painelSidebar.setSize(i, painelSidebar.getHeight());
                        Thread.sleep(1);
                    }
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            } else {
                try {
                    for (int i = 0; i <= 210; i++) {
                        painelSidebar.setSize(i, painelSidebar.getHeight());
                        Thread.sleep(1);
                    }
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            }
        }).start();
    }                                           

    private void labelLogoMouseClicked(java.awt.event.MouseEvent evt) {                                       
        new MenuPrincipalView(this.usuarioLogado).setVisible(true);
        this.dispose();
    }                                      

    private void criarVotacaoActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void participarVotacaoActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

    private void gerenciaVotacaoActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void aprovarVotacaoActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void txtOpcao3ActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        String pergunta = txtPergunta.getText().trim();
        if (pergunta.isEmpty()) {
            exibirMensagem("O campo 'Pergunta' é obrigatório.");
            return;
        }
        this.votacao.setPergunta(pergunta);

        List<String> novasOpcoes = new ArrayList<>();
        for (JPanel painelOpcao : paineisDeOpcao) {
            for (Component comp : painelOpcao.getComponents()) {
                if (comp instanceof JTextField) {
                    String textoOpcao = ((JTextField) comp).getText().trim();
                    if (textoOpcao.isEmpty()) {
                        exibirMensagem("Preencha todos os campos de opção em branco.");
                        return;
                    }
                    novasOpcoes.add(textoOpcao);
                }
            }
        }

        if (novasOpcoes.size() < 2) {
            exibirMensagem("A votação deve ter pelo menos 2 opções.");
            return;
        }

        VotacaoController controller = new VotacaoController();
        OpcaoVotoRepository opcaoRepo = new OpcaoVotoRepository();
        VotacaoService service = new VotacaoService();

        if (isEditMode) {
            try {
                controller.atualizarVotacao(this.votacao);
                service.sincronizarOpcoes(this.votacao.getIdVotacao(), novasOpcoes);
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
                    for (String descricaoOpcao : novasOpcoes) {
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
    }                                            

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        new CriarVotacaoView(this.usuarioLogado, this.votacao, this.isEditMode).setVisible(true);
        this.dispose();
    }                                         

    private void btnAdicionarOpcaoActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        if (paineisDeOpcao.size() < MAX_OPCOES) {
            adicionarNovaOpcao(true, "");
        } else {
            exibirMensagem("Limite máximo de 5 opções atingido.");
        }
    }                                                 

    private void txtPerguntaActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void labelIconePerfilMouseClicked(java.awt.event.MouseEvent evt) {                                              
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
    }                                             
    
    private void inicializarMenuLateral() {
        java.util.List<JButton> botoes = java.util.Arrays.asList(
            criarVotacao, participarVotacao, gerenciaVotacao, aprovarVotacao, votoArquivado
        );

        configurarBotao(criarVotacao, "criarVoto.png");
        configurarBotao(participarVotacao, "peoplemais.png");
        configurarBotao(gerenciaVotacao, "configpast.png");
        configurarBotao(aprovarVotacao, "list_check.png");
        configurarBotao(votoArquivado, "arquivada.png");

        for (JButton botao : botoes) {
            adicionarListeners(botao);
        }
    }

    private void configurarBotao(JButton botao, String nomeIcone) {
        botao.putClientProperty("JButton.buttonType", "toolBarButton");
        botao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        botao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botao.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 15, 8, 15));
        botao.setIconTextGap(15);
        botao.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));

        try {
            botao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/" + nomeIcone)));
        } catch (Exception e) {
            System.out.println("ERRO ao carregar ícone: " + nomeIcone);
        }
    }

    private void adicionarListeners(JButton botao) {
        final java.awt.Color COR_FUNDO_SIDEBAR = painelSidebar.getBackground();
        final java.awt.Color COR_HOVER_AZUL = new java.awt.Color(235, 240, 255);

        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(COR_HOVER_AZUL);
                botao.setOpaque(true);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setOpaque(false);
                botao.setBackground(COR_FUNDO_SIDEBAR);
            }
        });

        botao.addActionListener(e -> {
            System.out.println("Botão '" + botao.getText() + "' clicado!");
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel PainelConteudo;
    private javax.swing.JLabel TituloO1;
    private javax.swing.JLabel TituloO2;
    private javax.swing.JLabel TituloO3;
    private javax.swing.JLabel TituloO4;
    private javax.swing.JLabel TituloO5;
    private javax.swing.JLabel TituloP;
    private javax.swing.JLabel TituloPrincipal;
    private javax.swing.JButton aprovarVotacao;
    private javax.swing.JButton btnAdicionarOpcao;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JButton criarVotacao;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton gerenciaVotacao;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelIconeMenu;
    private javax.swing.JLabel labelIconePerfil;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JLabel labelNomeUsuario;
    private javax.swing.JLabel menutxt;
    private javax.swing.JPanel painelFormulario;
    private javax.swing.JPanel painelHeader;
    private javax.swing.JPanel painelHeaderDireita;
    private javax.swing.JPanel painelHeaderEsquerda;
    private javax.swing.JPanel painelSidebar;
    private javax.swing.JButton participarVotacao;
    private javax.swing.JTextField txtOpcao1;
    private javax.swing.JTextField txtOpcao2;
    private javax.swing.JTextField txtOpcao3;
    private javax.swing.JTextField txtOpcao4;
    private javax.swing.JTextField txtOpcao5;
    private javax.swing.JTextField txtPergunta;
    private javax.swing.JButton votoArquivado;
    // End of variables declaration                   
}

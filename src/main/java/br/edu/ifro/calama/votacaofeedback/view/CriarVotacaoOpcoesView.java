package br.edu.ifro.calama.votacaofeedback.view;

import br.edu.ifro.calama.votacaofeedback.controller.VotacaoController;
import br.edu.ifro.calama.votacaofeedback.model.OpcaoVoto;
import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.model.Votacao;
import br.edu.ifro.calama.votacaofeedback.repository.OpcaoVotoRepository;
import br.edu.ifro.calama.votacaofeedback.util.ToastUtil;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author floriano, Athos
 */
public class CriarVotacaoOpcoesView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CriarVotacaoOpcoesView.class.getName());
    private final Usuario usuarioLogado;
    private final Votacao votacao;
    private final List<JLabel> todosOsLabelsDeOpcao;
    private final List<JTextField> todosOsCamposDeOpcao;
    private int opcoesVisiveis = 0;
    private boolean isEditMode = false;

    public CriarVotacaoOpcoesView(Usuario usuario, Votacao votacao) {
        initComponents();
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.usuarioLogado = usuario;
        this.votacao = votacao;

        todosOsLabelsDeOpcao = Arrays.asList(TituloO1, TituloO2, TituloO3, TituloO4, TituloO5);
        todosOsCamposDeOpcao = Arrays.asList(txtOpcao1, txtOpcao2, txtOpcao3, txtOpcao4, txtOpcao5);

        for (int i = 0; i < todosOsLabelsDeOpcao.size(); i++) {
            todosOsLabelsDeOpcao.get(i).setVisible(false);
            todosOsCamposDeOpcao.get(i).setVisible(false);
        }

        inicializarMenuLateral();
        configurarTela();
    }

    private void configurarTela() {
        if (votacao != null && votacao.getIdVotacao() > 0) {
            this.isEditMode = true;
        }

        if (isEditMode) {
            TituloPrincipal.setText("EDITAR OPÇÕES DA VOTAÇÃO");
            btnFinalizar.setText("Salvar alteções");
            preencherDadosExistentes();
        } else {
            TituloPrincipal.setText("CRIAR VOTAÇÃO - OPÇÕES");
            btnFinalizar.setText("Finalizar");
            btnAdicionarOpcao.doClick();
            btnAdicionarOpcao.doClick();
        }
    }

    private void preencherDadosExistentes() {
        txtPergunta.setText(votacao.getPergunta());

        OpcaoVotoRepository opcaoRepo = new OpcaoVotoRepository();
        List<OpcaoVoto> opcoesExistentes = opcaoRepo.buscarPorIdVotacao(votacao.getIdVotacao());

        opcoesVisiveis = 0;
        for (int i = 0; i < opcoesExistentes.size(); i++) {
            if (i < todosOsCamposDeOpcao.size()) {
                todosOsLabelsDeOpcao.get(i).setVisible(true);
                JTextField campo = todosOsCamposDeOpcao.get(i);
                campo.setVisible(true);
                campo.setText(opcoesExistentes.get(i).getDescricao());
                opcoesVisiveis++;
            }
        }
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
        new MenuPrincipalView(this.usuarioLogado).setVisible(true);
        this.dispose();
    }){{setRepeats(false);}}.start();
}

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        String pergunta = txtPergunta.getText().trim();
    if (pergunta.isEmpty()) {
        exibirMensagem("O campo 'Pergunta' é obrigatório.");
        return;
    }

    List<String> novasOpcoes = new ArrayList<>();
    for (JTextField campo : todosOsCamposDeOpcao) {
        if (campo.isVisible() && !campo.getText().trim().isEmpty()) {
            novasOpcoes.add(campo.getText().trim());
        }
    }
    if (novasOpcoes.size() < 2) {
        exibirMensagem("A votação deve ter pelo menos 2 opções.");
        return;
    }

    // 2. Prepara os objetos para interagir com o backend
    VotacaoController controller = new VotacaoController();
    OpcaoVotoRepository opcaoRepo = new OpcaoVotoRepository();
    
    // Atualiza o objeto votacao em memória com a pergunta da tela
    this.votacao.setPergunta(pergunta);
    
    // 3. Executa a lógica de salvamento com tratamento de erro
    if (isEditMode) {
        // --- LÓGICA DE EDIÇÃO ---
        try {
            // PASSO 1: Atualiza os dados principais da votação (título, datas, etc.)
            controller.atualizarVotacao(this.votacao);
            
            // PASSO 2: Limpa as opções antigas
            opcaoRepo.deletarPorIdVotacao(this.votacao.getIdVotacao());
            
            // PASSO 3: Insere as novas opções
            for (String descricaoOpcao : novasOpcoes) {
                OpcaoVoto op = new OpcaoVoto();
                op.setDescricao(descricaoOpcao);
                op.setIdVotacao(this.votacao.getIdVotacao());
                opcaoRepo.criar(op);
            }
            
            // Se chegou até aqui, tudo deu certo!
            exibirMensagemDeSucesso("Votação atualizada com sucesso!");
            navegarParaMenuPrincipal();

        } catch (Exception e) {
            // Se qualquer um dos passos acima falhar, o código pula para cá
            exibirMensagem("Erro ao atualizar a votação: " + e.getMessage());
            e.printStackTrace();
        }
        
    } else {
        // --- LÓGICA DE CRIAÇÃO ---
        try {
            // PASSO 1: Cria a votação principal e pega o ID gerado
            int novoId = controller.criarVotacao(this.votacao);
            
            if (novoId > 0) {
                // PASSO 2: Cria as opções associadas ao novo ID
                for (String descricaoOpcao : novasOpcoes) {
                    OpcaoVoto op = new OpcaoVoto();
                    op.setDescricao(descricaoOpcao);
                    op.setIdVotacao(novoId);
                    opcaoRepo.criar(op);
                }
                
                // Se chegou até aqui, tudo deu certo!
                exibirMensagemDeSucesso("Votação criada com sucesso!");
                navegarParaMenuPrincipal();

            } else {
                exibirMensagem("Falha ao salvar os dados principais da votação.");
            }

        } catch (Exception e) {
            // Se qualquer um dos passos acima falhar, o código pula para cá
            exibirMensagem("Erro ao criar a votação: " + e.getMessage());
            e.printStackTrace();
        }
    }
}                                            

    private void btnAdicionarOpcaoActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        if (opcoesVisiveis < todosOsLabelsDeOpcao.size()) {
            todosOsLabelsDeOpcao.get(opcoesVisiveis).setVisible(true);
            todosOsCamposDeOpcao.get(opcoesVisiveis).setVisible(true);
            opcoesVisiveis++;
        } else {
            exibirMensagem("Limite máximo de opções atingido.");
        }
    }                                                 

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        CriarVotacaoView telaCriarVotacao = new CriarVotacaoView(this.usuarioLogado, this.votacao);
        telaCriarVotacao.setLocationRelativeTo(null);
        telaCriarVotacao.setVisible(true);
        this.dispose();
    }                                         

    // O resto do seu código (métodos de menu, listeners, etc.) continua aqui...
    //<editor-fold defaultstate="collapsed" desc="Código Gerado e Métodos do Menu (não precisa mexer)">
    @SuppressWarnings("unchecked")
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
        TituloPrincipal = new javax.swing.JLabel();
        TituloP = new javax.swing.JLabel();
        txtPergunta = new javax.swing.JTextField();
        btnAdicionarOpcao = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        TituloO1 = new javax.swing.JLabel();
        TituloO2 = new javax.swing.JLabel();
        TituloO3 = new javax.swing.JLabel();
        TituloO4 = new javax.swing.JLabel();
        TituloO5 = new javax.swing.JLabel();
        txtOpcao1 = new javax.swing.JTextField();
        txtOpcao2 = new javax.swing.JTextField();
        txtOpcao3 = new javax.swing.JTextField();
        txtOpcao4 = new javax.swing.JTextField();
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

        TituloPrincipal.setText("CRIAR VOTAÇÃO - OPÇÕES");

        TituloP.setText("Pergunta");

        txtPergunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPerguntaActionPerformed(evt);
            }
        });

        btnAdicionarOpcao.setText("Adicionar Opção.");
        btnAdicionarOpcao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarOpcaoActionPerformed(evt);
            }
        });

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        btnFinalizar.setText("Finalizar");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        TituloO1.setText("Opção 1");

        TituloO2.setText("Opção 2");

        TituloO3.setText("Opção 3");

        TituloO4.setText("Opção 4");

        TituloO5.setText("Opção 5");

        txtOpcao3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOpcao3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelConteudoLayout = new javax.swing.GroupLayout(PainelConteudo);
        PainelConteudo.setLayout(PainelConteudoLayout);
        PainelConteudoLayout.setHorizontalGroup(
            PainelConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelConteudoLayout.createSequentialGroup()
                .addGroup(PainelConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelConteudoLayout.createSequentialGroup()
                        .addGap(1106, 1106, 1106)
                        .addComponent(TituloPrincipal))
                    .addGroup(PainelConteudoLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(PainelConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdicionarOpcao)
                            .addGroup(PainelConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(TituloO1)
                                .addComponent(TituloP)
                                .addComponent(txtPergunta, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                .addComponent(TituloO5)
                                .addComponent(TituloO4)
                                .addComponent(TituloO3)
                                .addComponent(TituloO2)
                                .addComponent(txtOpcao1)
                                .addComponent(txtOpcao2)
                                .addComponent(txtOpcao3)
                                .addComponent(txtOpcao5)
                                .addComponent(txtOpcao4))))
                    .addGroup(PainelConteudoLayout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(btnVoltar)
                        .addGap(29, 29, 29)
                        .addComponent(btnFinalizar)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        PainelConteudoLayout.setVerticalGroup(
            PainelConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelConteudoLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(TituloPrincipal)
                .addGap(18, 18, 18)
                .addComponent(TituloP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPergunta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TituloO1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOpcao1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TituloO2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOpcao2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TituloO3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOpcao3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TituloO4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOpcao4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TituloO5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOpcao5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAdicionarOpcao)
                .addGap(18, 18, 18)
                .addGroup(PainelConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVoltar)
                    .addComponent(btnFinalizar))
                .addContainerGap(292, Short.MAX_VALUE))
        );

        getContentPane().add(PainelConteudo, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>                        

    private void labelIconeMenuMouseClicked(java.awt.event.MouseEvent evt) {                                            
        new Thread(new Runnable() {
            @Override
            public void run() {
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
            }
        }).start();
    }                                           

    private void labelLogoMouseClicked(java.awt.event.MouseEvent evt) {                                       
        MenuPrincipalView telaDeCriacao = new MenuPrincipalView(this.usuarioLogado);
        telaDeCriacao.setLocationRelativeTo(null);
        telaDeCriacao.setVisible(true);
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
        java.util.List<javax.swing.JButton> botoes = java.util.Arrays.asList(
            criarVotacao, participarVotacao, gerenciaVotacao, aprovarVotacao, votoArquivado
        );
        configurarBotao(criarVotacao, "criarVoto.png");
        configurarBotao(participarVotacao, "peoplemais.png");
        configurarBotao(gerenciaVotacao, "configpast.png");
        configurarBotao(aprovarVotacao, "list_check.png");
        configurarBotao(votoArquivado, "arquivada.png");

        for (javax.swing.JButton botao : botoes) {
            adicionarListeners(botao);
        }
    }

    private void configurarBotao(javax.swing.JButton botao, String nomeIcone) {
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

    private void adicionarListeners(javax.swing.JButton botao) {
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
    private javax.swing.JLabel labelIconeMenu;
    private javax.swing.JLabel labelIconePerfil;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JLabel labelNomeUsuario;
    private javax.swing.JLabel menutxt;
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
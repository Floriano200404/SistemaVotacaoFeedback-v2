/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;

import br.edu.ifro.calama.votacaofeedback.controller.VotacaoController;
import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.model.Votacao;
import br.edu.ifro.calama.votacaofeedback.repository.UsuarioRepository;
import br.edu.ifro.calama.votacaofeedback.service.VotacaoService;
import br.edu.ifro.calama.votacaofeedback.util.ToastUtil;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Aluno
 */
public class MenuPrincipalView extends javax.swing.JFrame {

    private Usuario usuariologado;
    private final VotacaoService votacaoService;
    private JLabel labelContagemAtivas, labelContagemAprovacao, labelContagemArquivadas, labelContagemGerenciar;
    private AprovarVotacaoView telaAprovarVotacao;
    private GerenciarVotacaoView telaGerenciarVotacao;
    private VotacoesAtivasView telaVotacoesAtivas;
    private VotacoesArquivadasView telaVotacoesArquivadas;
    private TelaDeVotoView telaDeVoto;
    

    public MenuPrincipalView(Usuario usuario) throws Exception {
        initComponents();
        this.usuariologado = usuario;
        this.votacaoService = new VotacaoService();

        if (this.usuariologado != null) {
                labelNomeUsuario.setText(this.usuariologado.getNome());
                txtbemVindo.setText("Bem-Vindo(a), " + this.usuariologado.getNome() + "!");
         }

        painelConteudo.add(cardDashboard, "cardDashboard");
        CardLayout cl = (CardLayout) painelConteudo.getLayout();
        cl.show(painelConteudo, "cardDashboard");

        inicializarMenuLateral();
        inicializarPaineis();
        construirDashboard();
        carregarDadosDashboard();
        atualizarDashboard();
   
   }   
    
    private void inicializarPaineis() {
        // Instancia cada painel UMA VEZ, passando a referência do MenuPrincipal quando necessário
        telaAprovarVotacao = new AprovarVotacaoView(this, usuariologado);
        telaGerenciarVotacao = new GerenciarVotacaoView(this, usuariologado);
        telaVotacoesAtivas = new VotacoesAtivasView(this, usuariologado);
        telaVotacoesArquivadas = new VotacoesArquivadasView(this, usuariologado);
        telaDeVoto = new TelaDeVotoView(this, usuariologado);

        // Adiciona cada painel ao painelConteudo com uma CHAVE (String) única
        // O painel cardDashboard já é adicionado pelo NetBeans, vamos apenas dar um nome
        painelConteudo.add(cardDashboard, "DASHBOARD");
        painelConteudo.add(telaAprovarVotacao, "APROVAR");
        painelConteudo.add(telaGerenciarVotacao, "GERENCIAR");
        painelConteudo.add(telaVotacoesAtivas, "ATIVAS");
        painelConteudo.add(telaVotacoesArquivadas, "ARQUIVADAS");
        painelConteudo.add(telaDeVoto, "TELADEVOTO");
        
        // Mostra o dashboard como tela inicial
        CardLayout cl = (CardLayout) painelConteudo.getLayout();
        cl.show(painelConteudo, "DASHBOARD");
    }
   
    private void construirDashboard() {
        labelContagemAtivas = new JLabel("0 Votações");
        labelContagemAprovacao = new JLabel("0 Votações");
        labelContagemArquivadas = new JLabel("0 Votações");
        labelContagemGerenciar = new JLabel("0 Votações");

        JPanel linhaDeCima = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 25, 0));
        linhaDeCima.setOpaque(false);
        JPanel linhaDeBaixo = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 25, 0));
        linhaDeBaixo.setOpaque(false);
        
        linhaDeCima.add(criarCard("VOTAÇÕES ATIVAS", labelContagemAtivas, () -> navegarPara("ATIVAS")));
        linhaDeCima.add(criarCard("AGUARDANDO APROVAÇÃO", labelContagemAprovacao, () -> navegarPara("APROVAR")));
        linhaDeCima.add(criarCard("VOTAÇÕES ARQUIVADAS", labelContagemArquivadas, () -> navegarPara("ARQUIVADAS")));
        
        linhaDeBaixo.add(criarCard("CRIAR VOTAÇÃO", new JLabel("Começar do zero"), () -> criarVotacaoActionPerformed(null)));
        linhaDeBaixo.add(criarCard("GERENCIAR MINHAS VOTAÇÕES", labelContagemGerenciar, () -> navegarPara("GERENCIAR")));

        painelDosCards.removeAll();
        painelDosCards.setLayout(new javax.swing.BoxLayout(painelDosCards, javax.swing.BoxLayout.Y_AXIS));
        painelDosCards.add(javax.swing.Box.createVerticalStrut(20));
        painelDosCards.add(linhaDeCima);
        painelDosCards.add(javax.swing.Box.createVerticalStrut(25));
        painelDosCards.add(linhaDeBaixo);
        painelDosCards.add(javax.swing.Box.createVerticalGlue());
        painelDosCards.revalidate();
        painelDosCards.repaint();
    }
    
    private void carregarDadosDashboard() {
        if (usuariologado == null) return;

        new Thread(() -> {
            try {
                long ativas = votacaoService.contarVotacoesAtivas();
                long aprovacao = 0;
                long arquivadas = votacaoService.contarVotacoesArquivadas();
                long gerenciar = 0;

                if (isUsuarioAdmin()) {
                    aprovacao = votacaoService.contarVotacoesAguardandoAprovacao();
                    gerenciar = aprovacao;
                } else {
                    aprovacao = 0;
                    gerenciar = votacaoService.contarMinhasVotacoesGerenciaveis(usuariologado.getId());
                }

                final long fAtivas = ativas, fAprovacao = aprovacao, fArquivadas = arquivadas, fGerenciar = gerenciar;
                javax.swing.SwingUtilities.invokeLater(() -> {
                    labelContagemAtivas.setText(fAtivas + (fAtivas == 1 ? " Votação" : " Votações"));
                    labelContagemAprovacao.setText(fAprovacao + (fAprovacao == 1 ? " Votação" : " Votações"));
                    labelContagemArquivadas.setText(fArquivadas + (fArquivadas == 1 ? " Votação" : " Votações"));
                    labelContagemGerenciar.setText(fGerenciar + (fGerenciar == 1 ? " Votação" : " Votações"));
                });

            } catch (Exception e) {
                e.printStackTrace();
                 javax.swing.SwingUtilities.invokeLater(() -> {
                    labelContagemAtivas.setText("Erro ao carregar");
                    labelContagemAprovacao.setText("Erro ao carregar");
                    labelContagemArquivadas.setText("Erro ao carregar");
                    labelContagemGerenciar.setText("Erro ao carregar");
                 });
            }
        }).start();
    }
    
    public void atualizarDashboard() {
        if (usuariologado == null) return;
        new Thread(() -> {
            try {
                long ativas = votacaoService.contarVotacoesAtivas();
                long arquivadas = votacaoService.contarVotacoesArquivadas();
                long aprovacao, gerenciar;

                if (isUsuarioAdmin()) {
                    aprovacao = votacaoService.contarVotacoesAguardandoAprovacao();
                    gerenciar = aprovacao; // Para admin, gerenciar mostra todas as pendentes
                } else {
                    aprovacao = 0; // Usuário comum não aprova
                    gerenciar = votacaoService.contarMinhasVotacoesGerenciaveis(usuariologado.getId());
                }

                final long fAtivas = ativas, fAprovacao = aprovacao, fArquivadas = arquivadas, fGerenciar = gerenciar;
                javax.swing.SwingUtilities.invokeLater(() -> {
                    labelContagemAtivas.setText(fAtivas + (fAtivas == 1 ? " Votação" : " Votações"));
                    labelContagemAprovacao.setText(fAprovacao + (fAprovacao == 1 ? " Votação" : " Votações"));
                    labelContagemArquivadas.setText(fArquivadas + (fArquivadas == 1 ? " Votação" : " Votações"));
                    labelContagemGerenciar.setText(fGerenciar + (fGerenciar == 1 ? " Votação" : " Votações"));
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    public void navegarPara(String nomeDoPainel) {
        // Primeiro, atualiza os dados do painel de destino antes de mostrá-lo
        switch (nomeDoPainel) {
            case "DASHBOARD":
                atualizarDashboard(); // Atualiza os contadores ao voltar para o dashboard
                break;
            case "APROVAR":
                if (!isUsuarioAdmin()) {
                    exibirMensagemErro("Acesso negado. Apenas administradores.");
                    return; // Impede a navegação se o usuário não for admin
                }
                telaAprovarVotacao.carregarVotacoesParaAprovacao();
                break;
            case "GERENCIAR":
                 // A tela de gerenciar pode ser acessada por admin ou usuário comum para ver suas próprias votações
                telaGerenciarVotacao.carregarVotacoesDoUsuario();
                break;
            case "ATIVAS":
                telaVotacoesAtivas.carregarVotacoes(this.usuariologado);
                break;
            case "ARQUIVADAS":
                telaVotacoesArquivadas.carregarVotacoesArquivadas(this.usuariologado);
                break;
        }
        CardLayout cl = (CardLayout) painelConteudo.getLayout();
        cl.show(painelConteudo, nomeDoPainel);
    }
    
    private JPanel criarCard(String titulo, JLabel labelSubtitulo, Runnable acao) {
        JPanel card = new JPanel();
        card.setPreferredSize(new java.awt.Dimension(300, 65));
        card.setBackground(new java.awt.Color(48, 162, 218));
        card.setLayout(new javax.swing.BoxLayout(card, javax.swing.BoxLayout.Y_AXIS));
        card.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 20, 15, 20));
        card.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        labelTitulo.setForeground(java.awt.Color.WHITE);

        labelSubtitulo.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 10));
        labelSubtitulo.setForeground(new java.awt.Color(220, 240, 255));

        card.add(labelTitulo);
        card.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 8)));
        card.add(labelSubtitulo);

        card.addMouseListener(new MouseAdapter() {
            final java.awt.Color corOriginal = new java.awt.Color(48, 162, 218);
            final java.awt.Color corHover = new java.awt.Color(68, 182, 238);
            
            @Override
            public void mouseClicked(MouseEvent e) {
                if (acao != null) {
                    acao.run();
                }
            }
            @Override
            public void mouseEntered(MouseEvent evt) {
                card.setBackground(corHover);
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                card.setBackground(corOriginal);
            }
        });
        return card;
    }
    
    private boolean isUsuarioAdmin() {
        if (this.usuariologado == null) return false;
        try {
            UsuarioRepository userRepo = new UsuarioRepository();
            return userRepo.isUsuarioEmGrupo(this.usuariologado.getId(), "ADMINISTRADORES");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void navegarParaTelaDeVoto(Votacao votacao, TelaDeVotoView.ModoTela modo) {
        if (telaDeVoto != null) {
            telaDeVoto.carregarDados(votacao, this.usuariologado, modo);
            CardLayout cl = (CardLayout) painelConteudo.getLayout();
            cl.show(painelConteudo, "TELADEVOTO");
        }
    }
    
    public void exibirMensagemErro(String mensagem) {
        ToastUtil toast = new ToastUtil(this, mensagem, ToastUtil.ToastType.ERROR, ToastUtil.ToastPosition.TOP_RIGHT);
        toast.display();
    }
    
    public void exibirMensagem(String mensagem) {
        ToastUtil toast = new ToastUtil(this, mensagem, ToastUtil.ToastType.SUCCESS, ToastUtil.ToastPosition.TOP_RIGHT);
        toast.display();
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelHeader = new javax.swing.JPanel();
        painelHeaderEsquerda = new javax.swing.JPanel();
        labelIconeMenu = new javax.swing.JLabel();
        labelLogo = new javax.swing.JLabel();
        painelHeaderDireita = new javax.swing.JPanel();
        labelNomeUsuario = new javax.swing.JLabel();
        labelIconePerfil = new javax.swing.JLabel();
        painelConteudo = new javax.swing.JPanel();
        cardDashboard = new javax.swing.JPanel();
        painelTopoConteudo = new javax.swing.JPanel();
        txtbemVindo = new javax.swing.JLabel();
        textGuia = new javax.swing.JTextArea();
        painelDosCards = new javax.swing.JPanel();
        painelSidebar = new javax.swing.JPanel();
        menutxt = new javax.swing.JLabel();
        criarVotacao = new javax.swing.JButton();
        participarVotacao = new javax.swing.JButton();
        gerenciaVotacao = new javax.swing.JButton();
        aprovarVotacao = new javax.swing.JButton();
        votoArquivado = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1480, 800));

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

        painelConteudo.setLayout(new java.awt.CardLayout());

        cardDashboard.setLayout(new java.awt.BorderLayout());

        painelTopoConteudo.setLayout(new javax.swing.BoxLayout(painelTopoConteudo, javax.swing.BoxLayout.Y_AXIS));

        txtbemVindo.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        txtbemVindo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtbemVindo.setText("Bem-Vindo, Nome do Usuário!");
        txtbemVindo.setAlignmentX(0.5F);
        painelTopoConteudo.add(txtbemVindo);

        textGuia.setEditable(false);
        textGuia.setColumns(20);
        textGuia.setLineWrap(true);
        textGuia.setRows(5);
        textGuia.setText("Bem-vindo(a) de volta!\n\nEste é o seu painel de controle para o Sistema de Votação. Acompanhe as votações ativas, gerencie propostas pendentes de aprovação e acesse os resultados arquivados.\n\nPara começar, selecione uma das opções no menu de navegação à sua esquerda ou utilize um dos atalhos nos cartões abaixo para acesso rápido às principais funções.");
        textGuia.setWrapStyleWord(true);
        textGuia.setBorder(null);
        textGuia.setOpaque(false);
        painelTopoConteudo.add(textGuia);

        cardDashboard.add(painelTopoConteudo, java.awt.BorderLayout.PAGE_START);

        painelDosCards.setOpaque(false);
        painelDosCards.setLayout(new javax.swing.BoxLayout(painelDosCards, javax.swing.BoxLayout.Y_AXIS));
        cardDashboard.add(painelDosCards, java.awt.BorderLayout.CENTER);

        painelConteudo.add(cardDashboard, "card4");

        getContentPane().add(painelConteudo, java.awt.BorderLayout.CENTER);

        painelSidebar.setBackground(new java.awt.Color(255, 255, 255));
        painelSidebar.setPreferredSize(new java.awt.Dimension(230, 0));
        painelSidebar.setLayout(new javax.swing.BoxLayout(painelSidebar, javax.swing.BoxLayout.Y_AXIS));
        painelSidebar.add(menutxt);

        criarVotacao.setText("Criar Votação");
        criarVotacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                criarVotacaoMouseClicked(evt);
            }
        });
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
        votoArquivado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                votoArquivadoActionPerformed(evt);
            }
        });
        painelSidebar.add(votoArquivado);
        painelSidebar.add(filler1);

        getContentPane().add(painelSidebar, java.awt.BorderLayout.LINE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void labelIconeMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconeMenuMouseClicked

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
    }//GEN-LAST:event_labelIconeMenuMouseClicked
    public void navegarParaVotacoesAtivas() {

        if (telaVotacoesAtivas != null && this.usuariologado != null) {

            telaVotacoesAtivas.carregarVotacoes(this.usuariologado);
        }


        java.awt.CardLayout cl = (java.awt.CardLayout)(painelConteudo.getLayout());
        cl.show(painelConteudo, "cardVotacoesAtivas");
    }
    
    private void criarVotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarVotacaoActionPerformed
        CriarVotacaoView telaDeCriacao = new CriarVotacaoView(this, this.usuariologado, null, false);
        telaDeCriacao.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_criarVotacaoActionPerformed

    private void participarVotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_participarVotacaoActionPerformed
        navegarPara("ATIVAS");
    }//GEN-LAST:event_participarVotacaoActionPerformed

    private void gerenciaVotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gerenciaVotacaoActionPerformed
        navegarPara("GERENCIAR");
    }//GEN-LAST:event_gerenciaVotacaoActionPerformed

    private void aprovarVotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aprovarVotacaoActionPerformed
        navegarPara("APROVAR");
    }//GEN-LAST:event_aprovarVotacaoActionPerformed

    private void labelLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelLogoMouseClicked
        navegarPara("DASHBOARD");
    }//GEN-LAST:event_labelLogoMouseClicked

    private void criarVotacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_criarVotacaoMouseClicked
               // TODO add your handling code here:
    }//GEN-LAST:event_criarVotacaoMouseClicked

    private void labelIconePerfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconePerfilMouseClicked
        ActionListener acaoDeLogout = e -> {
        new LoginView().setVisible(true);
        this.dispose();
        System.out.println("Logout realizado. Janela principal fechada.");
    };

    PerfilView perfil = new PerfilView(
        this,
        this.usuariologado.getNome(),
        this.usuariologado.getEmail(),
        this.usuariologado.getCpf(),
        this.usuariologado.getMatricula(),
        this.usuariologado.getCurso(),
        acaoDeLogout
    );
    perfil.setVisible(true);
    }//GEN-LAST:event_labelIconePerfilMouseClicked
    
    private void votoArquivadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_votoArquivadoActionPerformed
        navegarPara("ARQUIVADAS");
    }//GEN-LAST:event_votoArquivadoActionPerformed
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aprovarVotacao;
    private javax.swing.JPanel cardDashboard;
    private javax.swing.JButton criarVotacao;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton gerenciaVotacao;
    private javax.swing.JLabel labelIconeMenu;
    private javax.swing.JLabel labelIconePerfil;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JLabel labelNomeUsuario;
    private javax.swing.JLabel menutxt;
    private javax.swing.JPanel painelConteudo;
    private javax.swing.JPanel painelDosCards;
    private javax.swing.JPanel painelHeader;
    private javax.swing.JPanel painelHeaderDireita;
    private javax.swing.JPanel painelHeaderEsquerda;
    private javax.swing.JPanel painelSidebar;
    private javax.swing.JPanel painelTopoConteudo;
    private javax.swing.JButton participarVotacao;
    private javax.swing.JTextArea textGuia;
    private javax.swing.JLabel txtbemVindo;
    private javax.swing.JButton votoArquivado;
    // End of variables declaration//GEN-END:variables


}

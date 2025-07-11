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
import br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil; // Assumindo que você tem essa classe no projeto
import br.edu.ifro.calama.votacaofeedback.util.ToastUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    private int opcoesVisiveis = 0;
    private boolean isEditMode = false;
    
    private JPanel painelOpcoesContainer;
    private final List<JPanel> paineisDeOpcao = new ArrayList<>();
    private final int MAX_OPCOES = 5;
    private final int ANIMATION_DURATION = 150;
    private final int OPTION_PANEL_HEIGHT = 40;

    public CriarVotacaoOpcoesView(Usuario usuario, Votacao votacao) throws Exception {
        initComponents();
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.usuarioLogado = usuario;
        this.votacao = votacao;

        if (this.usuarioLogado != null) {
            labelNomeUsuario.setText(this.usuarioLogado.getNome());
        }
        
        inicializarLayoutDinamico();
        inicializarMenuLateral();
        configurarTela();
        
        
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    private void inicializarLayoutDinamico() {
        // 1. Cria o container que vai empilhar as opções verticalmente
        painelOpcoesContainer = new JPanel();
        painelOpcoesContainer.setLayout(new BoxLayout(painelOpcoesContainer, BoxLayout.Y_AXIS));
        painelOpcoesContainer.setBackground(PainelConteudo.getBackground()); // Cor de fundo igual ao pai

        // 2. Esconde os componentes originais (JLabels e JTextFields) pois vamos criá-los dinamicamente
        java.util.List<Component> componentesParaEsconder = java.util.Arrays.asList(
            TituloO1, TituloO2, TituloO3, TituloO4, TituloO5,
            txtOpcao1, txtOpcao2, txtOpcao3, txtOpcao4, txtOpcao5
        );
        componentesParaEsconder.forEach(c -> c.setVisible(false));
        
        // 3. Configura o GroupLayout para o PainelConteudo principal
        GroupLayout layout = new GroupLayout(PainelConteudo);
        PainelConteudo.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Alinhamento Horizontal: Centraliza tudo
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(TituloPrincipal, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
            .addComponent(TituloP)
            .addComponent(txtPergunta, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
            .addComponent(painelOpcoesContainer, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
            .addComponent(btnAdicionarOpcao, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addGap(20)
                .addComponent(btnFinalizar, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
        );

        // Alinhamento Vertical: Empilha os componentes
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(TituloPrincipal)
            .addGap(25)
            .addComponent(TituloP)
            .addComponent(txtPergunta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addGap(15)
            .addComponent(painelOpcoesContainer)
            .addGap(15)
            .addComponent(btnAdicionarOpcao)
            .addGap(25)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btnVoltar)
                .addComponent(btnFinalizar))
            .addGap(20)
        );
        
        // Alinha os títulos das opções à esquerda
        TituloP.setHorizontalAlignment(SwingConstants.LEFT);
    }
    
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
            // Adiciona as duas opções iniciais obrigatórias
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
            boolean isRemovivel = i >= 2; // Somente a partir da terceira opção (índice 2)
            adicionarNovaOpcao(isRemovivel, opcoesExistentes.get(i).getDescricao());
        }
    }
    
    private void adicionarNovaOpcao(boolean removivel, String textoInicial) {
        if (paineisDeOpcao.size() >= MAX_OPCOES) {
            return;
        }

        int novoIndice = paineisDeOpcao.size() + 1;

        // Painel da linha, usa BorderLayout para alinhar o botão 'X'
        JPanel painelLinha = new JPanel(new BorderLayout(10, 0));
        painelLinha.setBackground(painelOpcoesContainer.getBackground());
        painelLinha.setBorder(new EmptyBorder(5, 0, 5, 0)); // Espaçamento vertical

        JLabel label = new JLabel("Opção " + novoIndice + ":");
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));

        JTextField textField = new JTextField(textoInicial);
        textField.setName("txtOpcao" + novoIndice); // Dando um nome para fácil identificação

        painelLinha.add(label, BorderLayout.WEST);
        painelLinha.add(textField, BorderLayout.CENTER);

        if (removivel) {
            RoundedButtonUtil btnClose = new RoundedButtonUtil();
            btnClose.setText("X");
            btnClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnClose.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnClose.setForeground(Color.GRAY);
            btnClose.setColor(PainelConteudo.getBackground());
            btnClose.setColorOver(new Color(220, 220, 220));
            btnClose.setColorClick(new Color(210, 210, 210));
            btnClose.setRadius(999);
            btnClose.setBorderColor(PainelConteudo.getBackground());
            btnClose.setPreferredSize(new Dimension(30, 30));

            btnClose.addActionListener(e -> removerOpcaoComAnimacao(painelLinha));
            painelLinha.add(btnClose, BorderLayout.EAST);
        }

        paineisDeOpcao.add(painelLinha);
        animarAdicao(painelLinha); // Anima a adição em vez de adicionar diretamente
        
        atualizarVisibilidadeBotaoAdicionar();
    }
    
    private void animarAdicao(JPanel painelOpcional) {
        // Define o tamanho inicial como altura 0 para a animação
        Dimension startSize = new Dimension(painelOpcional.getPreferredSize().width, 0);
        painelOpcional.setPreferredSize(startSize);
        painelOpcional.setMaximumSize(startSize);
        painelOpcoesContainer.add(painelOpcional);

        Timer timer = new Timer(ANIMATION_DURATION / OPTION_PANEL_HEIGHT, new ActionListener() {
            private int currentHeight = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                currentHeight++;
                if (currentHeight >= OPTION_PANEL_HEIGHT) {
                    // Fim da animação
                    ((Timer) e.getSource()).stop();
                    painelOpcional.setPreferredSize(null); // Volta ao tamanho preferido
                    painelOpcional.setMaximumSize(new Dimension(Short.MAX_VALUE, OPTION_PANEL_HEIGHT));
                    revalidarJanela();
                } else {
                    Dimension newSize = new Dimension(painelOpcional.getPreferredSize().width, currentHeight);
                    painelOpcional.setPreferredSize(newSize);
                    painelOpcional.setMaximumSize(newSize);
                    revalidarJanela();
                }
            }
        });
        timer.start();
    }
    
    private void removerOpcaoComAnimacao(JPanel painelOpcao) {
        Timer timer = new Timer(ANIMATION_DURATION / OPTION_PANEL_HEIGHT, new ActionListener() {
            private int currentHeight = OPTION_PANEL_HEIGHT;

            @Override
            public void actionPerformed(ActionEvent e) {
                currentHeight--;
                if (currentHeight <= 0) {
                    // Fim da animação
                    ((Timer) e.getSource()).stop();
                    painelOpcoesContainer.remove(painelOpcao);
                    paineisDeOpcao.remove(painelOpcao);
                    
                    // Renomeia os labels das opções restantes
                    for(int i = 0; i < paineisDeOpcao.size(); i++){
                        JPanel panel = paineisDeOpcao.get(i);
                        JLabel label = (JLabel) panel.getComponent(0);
                        label.setText("Opção " + (i + 1) + ":");
                    }
                    
                    atualizarVisibilidadeBotaoAdicionar();
                    revalidarJanela();
                } else {
                    Dimension newSize = new Dimension(painelOpcao.getPreferredSize().width, currentHeight);
                    painelOpcao.setPreferredSize(newSize);
                    painelOpcao.setMaximumSize(newSize);
                    revalidarJanela();
                }
            }
        });
        timer.start();
    }
    
    private void revalidarJanela() {
        PainelConteudo.revalidate();
        PainelConteudo.repaint();
        pack(); // Redimensiona a janela para caber os componentes
        setLocationRelativeTo(null); // Centraliza a janela novamente
    }
    
    private void atualizarVisibilidadeBotaoAdicionar() {
        btnAdicionarOpcao.setVisible(paineisDeOpcao.size() < MAX_OPCOES);
    }
    
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (b) {
            pack();
            setLocationRelativeTo(null);
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
        jPanel1 = new javax.swing.JPanel();
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

        txtOpcao3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOpcao3ActionPerformed(evt);
            }
        });

        TituloO4.setText("Opção 4");

        TituloO5.setText("Opção 5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TituloO2)
                    .addComponent(TituloO1))
                .addContainerGap(307, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtOpcao1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtOpcao3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtOpcao5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtOpcao4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TituloO5)
                                        .addComponent(TituloO4)
                                        .addComponent(TituloO3))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, Short.MAX_VALUE)))
                            .addContainerGap(101, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(txtOpcao2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(101, Short.MAX_VALUE)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(TituloO1)
                .addGap(42, 42, 42)
                .addComponent(TituloO2)
                .addContainerGap(321, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(46, 46, 46)
                    .addComponent(txtOpcao1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(34, 34, 34)
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
                    .addContainerGap(112, Short.MAX_VALUE)))
        );

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
                            .addComponent(TituloP)
                            .addComponent(txtPergunta, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PainelConteudoLayout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(btnVoltar)
                        .addGap(29, 29, 29)
                        .addComponent(btnFinalizar)))
                .addContainerGap(21, Short.MAX_VALUE))
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
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(213, 213, 213)
                .addComponent(btnAdicionarOpcao)
                .addGap(18, 18, 18)
                .addGroup(PainelConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVoltar)
                    .addComponent(btnFinalizar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(PainelConteudo, java.awt.BorderLayout.CENTER);

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

    private void labelLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelLogoMouseClicked
        MenuPrincipalView telaDeCriacao = new MenuPrincipalView(this.usuarioLogado);

        telaDeCriacao.setLocationRelativeTo(null);
        telaDeCriacao.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_labelLogoMouseClicked

    private void criarVotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarVotacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_criarVotacaoActionPerformed

    private void participarVotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_participarVotacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_participarVotacaoActionPerformed

    private void gerenciaVotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gerenciaVotacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gerenciaVotacaoActionPerformed

    private void aprovarVotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aprovarVotacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aprovarVotacaoActionPerformed

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

    private void txtOpcao3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOpcao3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOpcao3ActionPerformed

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        String pergunta = txtPergunta.getText().trim();
        if (pergunta.isEmpty()) {
            exibirMensagem("O campo 'Pergunta' é obrigatório.");
            return;
        }
        this.votacao.setPergunta(pergunta);

        List<String> novasOpcoes = new ArrayList<>();
        // MODIFICADO: Itera sobre os painéis para pegar os textos
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

        // A lógica de salvar permanece a mesma
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
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        CriarVotacaoView telaCriarVotacao = new CriarVotacaoView(this.usuarioLogado, this.votacao, this.isEditMode);
        telaCriarVotacao.setLocationRelativeTo(null);
        telaCriarVotacao.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnAdicionarOpcaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarOpcaoActionPerformed
        if (paineisDeOpcao.size() < MAX_OPCOES) {
            adicionarNovaOpcao(true, ""); // Todas as opções adicionadas pelo botão são removíveis
        } else {
            exibirMensagem("Limite máximo de 5 opções atingido.");
        }
    }//GEN-LAST:event_btnAdicionarOpcaoActionPerformed

    private void txtPerguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPerguntaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPerguntaActionPerformed
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
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JPanel jPanel1;
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
    // End of variables declaration//GEN-END:variables

}
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
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;

/**
 *
 * @author floriano, ATHOS
 */
public class CriarVotacaoOpcoesView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CriarVotacaoOpcoesView.class.getName());
    private final Usuario usuarioLogado;
    private final Votacao votacao;
    private boolean isEditMode = false;

    private final List<JPanel> paineisDeOpcao = new ArrayList<>();
    private final int MAX_OPCOES = 5;
    private javax.swing.JPanel painelOpcoesContainer;

    public CriarVotacaoOpcoesView(Usuario usuario, Votacao votacao) throws Exception {
        initComponents();
        this.painelOpcoesContainer = this.painelOpcoesContainer1;
        painelOpcoesContainer1.removeAll();
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.usuarioLogado = usuario;
        this.votacao = votacao;

        if (this.usuarioLogado != null) {
            labelNomeUsuario.setText(this.usuarioLogado.getNome());
        }
        aplicarEstilosPersonalizados(); 
        
        inicializarMenuLateral();
        configurarTela();
        
        
        
        this.setLocationRelativeTo(null);
    }
    
    private void aplicarEstilosPersonalizados() {

        // Estilo especial para o botão "Adicionar Opção"
        btnAdicionarOpcao.setForeground(Color.GRAY);
        btnAdicionarOpcao.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnAdicionarOpcao.setOpaque(false);
        btnAdicionarOpcao.setContentAreaFilled(false);
        btnAdicionarOpcao.setBorderPainted(false);
        btnAdicionarOpcao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAdicionarOpcao.setText("Adicionar mais uma opção");

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

        botoesInferiores.setOpaque(false);
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
            btnClose.setColor(Color.WHITE);
            btnClose.setColorOver(new Color(220, 220, 220));
            btnClose.setColorClick(new Color(210, 210, 210));
            btnClose.setRadius(999);
            btnClose.setBorderColor(Color.WHITE);
            btnClose.setPreferredSize(new Dimension(30, 30));
            btnClose.addActionListener(e -> removerOpcao(painelLinha));
            painelLinha.add(btnClose, BorderLayout.EAST);
        }

        paineisDeOpcao.add(painelLinha);
        // Assumindo que você nomeou o painel no modo Design como 'painelOpcoesContainer'
        painelOpcoesContainer.add(painelLinha);

        atualizarLayoutConteudo();
        atualizarVisibilidadeBotaoAdicionar();
    }

    private void removerOpcao(JPanel painelOpcao) {
        painelOpcoesContainer.remove(painelOpcao);
        paineisDeOpcao.remove(painelOpcao);

        for (int i = 0; i < paineisDeOpcao.size(); i++) {
            JPanel panel = paineisDeOpcao.get(i);
            JLabel label = (JLabel) panel.getComponent(0);
            label.setText("Opção " + (i + 1) + ":");
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
        java.awt.GridBagConstraints gridBagConstraints;

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
        btnVoltar = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();

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

        PainelConteudo.setLayout(new java.awt.GridBagLayout());

        formCardPanel.setBackground(new java.awt.Color(255, 255, 255));
        formCardPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 30, 30));
        formCardPanel.setLayout(new java.awt.GridBagLayout());

        TituloPrincipal.setText("CRIAR VOTAÇÃO - OPÇÕES");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        formCardPanel.add(TituloPrincipal, gridBagConstraints);

        TituloP.setText("Pergunta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        formCardPanel.add(TituloP, gridBagConstraints);

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
        gridBagConstraints.insets = new java.awt.Insets(12, 21, 0, 0);
        formCardPanel.add(painelOpcoesContainer1, gridBagConstraints);

        btnAdicionarOpcao.setText("Adicionar Opção.");
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
        botoesInferiores.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });
        botoesInferiores.add(btnVoltar);

        btnFinalizar.setText("Finalizar");
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
        formCardPanel.add(botoesInferiores, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        PainelConteudo.add(formCardPanel, gridBagConstraints);

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
    private javax.swing.JPanel botoesInferiores;
    private javax.swing.JButton btnAdicionarOpcao;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JButton criarVotacao;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JPanel formCardPanel;
    private javax.swing.JButton gerenciaVotacao;
    private javax.swing.JLabel labelIconeMenu;
    private javax.swing.JLabel labelIconePerfil;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JLabel labelNomeUsuario;
    private javax.swing.JLabel menutxt;
    private javax.swing.JPanel painelHeader;
    private javax.swing.JPanel painelHeaderDireita;
    private javax.swing.JPanel painelHeaderEsquerda;
    private javax.swing.JPanel painelOpcoesContainer1;
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
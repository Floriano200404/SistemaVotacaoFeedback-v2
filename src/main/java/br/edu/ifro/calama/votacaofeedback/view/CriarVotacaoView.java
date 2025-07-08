/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;

import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.util.PlaceHolderUtil;
import br.edu.ifro.calama.votacaofeedback.util.RoundedVotacoesUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author floriano
 */
public class CriarVotacaoView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CriarVotacaoView.class.getName());
    private Usuario usuarioLogado;

    
    public CriarVotacaoView(Usuario usuario) {
        initComponents();
        this.usuarioLogado = usuario;
        
        if (this.usuarioLogado != null) {
            labelNomeUsuario.setText(this.usuarioLogado.getNome());
        }
        aplicarEstilos();
        inicializarMenuLateral();
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);
        
        PlaceHolderUtil.setPlaceholder(txtTitulo, "Digite o título da votação");
        PlaceHolderUtil.setPlaceholder(txtDescricao, "Descreva aqui os detalhes da sua votação...");
        txtDataInicial.setDateFormatString("dd/MM/yyyy");
        txtDataFinal.setDateFormatString("dd/MM/yyyy");
        txtDataDivulgacao.setDateFormatString("dd/MM/yyyy");
        txtDataInicial.setDate(new java.util.Date()); // data inicial da classe ja vem com o dia atual
        txtDataFinal.setDate(new java.util.Date()); // data inicial da classe ja vem com o dia atual
        txtDataDivulgacao.setDate(new java.util.Date()); // data inicial da classe ja vem com o dia atual
        
        java.awt.event.MouseAdapter focusRemoverListener = new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                java.awt.Component component = (java.awt.Component) e.getSource();
                component.requestFocusInWindow();
            }
        };

        jScrollPane.setPreferredSize(new java.awt.Dimension(212, 84)); 
        jScrollPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        jScrollPane.setOpaque(false);
        jScrollPane.getViewport().setOpaque(false);
        jScrollPane.setBorder(null);
        
        painelHeader.addMouseListener(focusRemoverListener);
        painelSidebar.addMouseListener(focusRemoverListener);
        painelConteudo.addMouseListener(focusRemoverListener);
        
        this.revalidate();
        this.repaint();
        this.getContentPane().addMouseListener(focusRemoverListener);

    }

    private void aplicarEstilos() {
        final int ALTURA_PADRAO = 35;
        JComponent[] campos = {
            txtTitulo, 
            txtDataInicial, 
            txtDataFinal, 
            txtDataDivulgacao, 
            comboParticipantes,
        };
        Border bordaArredondadaPreta = (Border) new RoundedVotacoesUtil(15, Color.BLACK);
        
        for (JComponent componente : campos) {
            componente.setBackground(Color.WHITE); 
            componente.setForeground(Color.BLACK);
            componente.setPreferredSize(new Dimension(componente.getPreferredSize().width, ALTURA_PADRAO));
            componente.setBorder(bordaArredondadaPreta);
            if (componente instanceof JTextField) {
                JTextField textField = (JTextField) componente;
                textField.setMargin(new Insets(2, 10, 2, 10)); 
            }
        }
        
        txtDescricao.setBackground(Color.WHITE);
        txtDescricao.setBorder(bordaArredondadaPreta);  
        Border bordaBotao = (Border) new RoundedVotacoesUtil(15, new Color(0x6A6A6A)); 

        btnCancelar.setText("CANCELAR");
        
        ((br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil) btnCancelar).setColor(new Color(0x6A6A6A)); 
        ((br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil) btnCancelar).setColorOver(new Color(0x8A8A8A));
        ((br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil) btnCancelar).setColorClick(new Color(0x5A5A5A));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setPreferredSize(new Dimension(120, ALTURA_PADRAO));
        btnCancelar.setBorder(bordaBotao);
        btnCancelar.setFocusPainted(false); 
        
        Border bordaBotaoAvancar = (Border) new RoundedVotacoesUtil(15, new Color(0x0095FF));
        btnAvancar.setText("AVANÇAR");
        ((br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil) btnAvancar).setColor(new Color(0x0095FF));
        ((br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil) btnAvancar).setColorOver(new Color(0x33ADFF));
        ((br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil) btnAvancar).setColorClick(new Color(0x0078CC));
        btnAvancar.setForeground(Color.WHITE);
        btnAvancar.setPreferredSize(new Dimension(120, ALTURA_PADRAO));
        btnAvancar.setBorder(bordaBotaoAvancar);
        btnAvancar.setFocusPainted(false);
        
    }
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
        painelConteudo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        TituloPrincipal = new javax.swing.JLabel();
        TituloVotacao = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        TituloDesc = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        TituloDateI = new javax.swing.JLabel();
        txtDataInicial = new com.toedter.calendar.JDateChooser();
        TituloDateF = new javax.swing.JLabel();
        txtDataFinal = new com.toedter.calendar.JDateChooser();
        TituloParticipante = new javax.swing.JLabel();
        comboParticipantes = new javax.swing.JComboBox<>();
        tituloDivulga = new javax.swing.JLabel();
        txtDataDivulgacao = new com.toedter.calendar.JDateChooser();
        jPanelBotoesInferiores = new javax.swing.JPanel();
        btnCancelar = new br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil()
        ;
        btnAvancar = new br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil()
        ;

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

        painelConteudo.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        TituloPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TituloPrincipal.setText("CRIAR VOTAÇÃO");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        jPanel1.add(TituloPrincipal, gridBagConstraints);

        TituloVotacao.setText("Título da Votação");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 2, 10);
        jPanel1.add(TituloVotacao, gridBagConstraints);

        txtTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTituloActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 15, 10);
        jPanel1.add(txtTitulo, gridBagConstraints);

        TituloDesc.setText("Descrição da Votação");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 2, 10);
        jPanel1.add(TituloDesc, gridBagConstraints);

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        jScrollPane.setViewportView(txtDescricao);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 279;
        gridBagConstraints.ipady = 64;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 20, 10);
        jPanel1.add(jScrollPane, gridBagConstraints);

        TituloDateI.setText("Data Inicial da Votação");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 2, 5);
        jPanel1.add(TituloDateI, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 62;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 15, 5);
        jPanel1.add(txtDataInicial, gridBagConstraints);

        TituloDateF.setText("Data Final da Votação");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 2, 10);
        jPanel1.add(TituloDateF, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 15, 10);
        jPanel1.add(txtDataFinal, gridBagConstraints);

        TituloParticipante.setText("Participantes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 2, 5);
        jPanel1.add(TituloParticipante, gridBagConstraints);

        comboParticipantes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 15, 5);
        jPanel1.add(comboParticipantes, gridBagConstraints);

        tituloDivulga.setText("Data De Divulgação dos Resultados");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 2, 10);
        jPanel1.add(tituloDivulga, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 15, 10);
        jPanel1.add(txtDataDivulgacao, gridBagConstraints);

        jPanelBotoesInferiores.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        btnCancelar.setText("X Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanelBotoesInferiores.add(btnCancelar);

        btnAvancar.setText("Avançar");
        btnAvancar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvancarActionPerformed(evt);
            }
        });
        jPanelBotoesInferiores.add(btnAvancar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        jPanel1.add(jPanelBotoesInferiores, gridBagConstraints);

        painelConteudo.add(jPanel1, new java.awt.GridBagConstraints());

        getContentPane().add(painelConteudo, java.awt.BorderLayout.CENTER);

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

    private void txtTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTituloActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAvancarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvancarActionPerformed
        java.util.Date dataInicial = txtDataInicial.getDate();
        java.util.Date dataFinal = txtDataFinal.getDate();
        java.util.Date dataDivulgacao = txtDataDivulgacao.getDate();
        if (dataInicial == null || dataFinal == null || dataDivulgacao == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, preencha todas as datas.", "Campos Obrigatórios", javax.swing.JOptionPane.ERROR_MESSAGE);
            return; 
        }
        if (dataFinal.before(dataInicial)) {
            javax.swing.JOptionPane.showMessageDialog(this, "A data final não pode ser anterior à data inicial.", "Erro de Validação", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        System.out.println("--- Dados da Votação ---");
        System.out.println("Data Inicial: " + dataInicial);
        System.out.println("Data Final: " + dataFinal);
        System.out.println("Data Divulgação: " + dataDivulgacao);
    
        CriarVotacaoOpcoesView telaDeCriacao = new CriarVotacaoOpcoesView(this.usuarioLogado);
        
        telaDeCriacao.setLocationRelativeTo(null);
        telaDeCriacao.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAvancarActionPerformed
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
    private javax.swing.JLabel TituloDateF;
    private javax.swing.JLabel TituloDateI;
    private javax.swing.JLabel TituloDesc;
    private javax.swing.JLabel TituloParticipante;
    private javax.swing.JLabel TituloPrincipal;
    private javax.swing.JLabel TituloVotacao;
    private javax.swing.JButton aprovarVotacao;
    private javax.swing.JButton btnAvancar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> comboParticipantes;
    private javax.swing.JButton criarVotacao;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton gerenciaVotacao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBotoesInferiores;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel labelIconeMenu;
    private javax.swing.JLabel labelIconePerfil;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JLabel labelNomeUsuario;
    private javax.swing.JLabel menutxt;
    private javax.swing.JPanel painelConteudo;
    private javax.swing.JPanel painelHeader;
    private javax.swing.JPanel painelHeaderDireita;
    private javax.swing.JPanel painelHeaderEsquerda;
    private javax.swing.JPanel painelSidebar;
    private javax.swing.JButton participarVotacao;
    private javax.swing.JLabel tituloDivulga;
    private com.toedter.calendar.JDateChooser txtDataDivulgacao;
    private com.toedter.calendar.JDateChooser txtDataFinal;
    private com.toedter.calendar.JDateChooser txtDataInicial;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtTitulo;
    private javax.swing.JButton votoArquivado;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;

import br.edu.ifro.calama.votacaofeedback.model.Grupo;
import br.edu.ifro.calama.votacaofeedback.model.OpcaoVoto;
import br.edu.ifro.calama.votacaofeedback.model.ResultadoVotacao;
import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.model.Votacao;
import br.edu.ifro.calama.votacaofeedback.repository.GrupoRepository;
import br.edu.ifro.calama.votacaofeedback.repository.OpcaoVotoRepository;
import br.edu.ifro.calama.votacaofeedback.repository.VotoRepository;
import br.edu.ifro.calama.votacaofeedback.service.VotoService;
import br.edu.ifro.calama.votacaofeedback.util.PainelArredondadoUtil;
import java.awt.Color;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author floriano
 */
public class TelaDeVotoView extends javax.swing.JPanel {

    
    private ModoTela modoAtual;


    private Votacao votacaoAtual;
    private Usuario usuarioLogado;
    private javax.swing.ButtonGroup grupoDeOpcoes;
    private final MenuPrincipalView menuPrincipal;
    private final java.awt.Color corVoltar = new java.awt.Color(127, 140, 141);
    private final java.awt.Color corVoltarHover = new java.awt.Color(149, 165, 166);
    private final java.awt.Color corVoltarClick = new java.awt.Color(93, 109, 126);


    /**
     * Creates new form TelaDeVotoView
     */
    public TelaDeVotoView(MenuPrincipalView menuPrincipal, Usuario usuario) {
        initComponents();
        this.grupoDeOpcoes = new javax.swing.ButtonGroup();

        int cornerRadius = 15;

        Color corVoltar = new Color(0x6A6A6A);
        Color corVoltarHover = new Color(0x8A8A8A);
        Color corVoltarClick = new Color(0x5A5A5A);

        btnVoltarVotacao.setText("VOLTAR");
        btnVoltarVotacao.setForeground(Color.WHITE);
        btnVoltarVotacao.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11));
        btnVoltarVotacao.setFocusPainted(false);
        btnVoltarVotacao.setBorder(null);
        
        btnVoltarVotacao.setPreferredSize(new java.awt.Dimension(100, 25));

        this.menuPrincipal = menuPrincipal;
        this.usuarioLogado = usuario;
    }
    
    public enum ModoTela {
        VOTAR,
        RESULTADO
    }

public void carregarDados(Votacao votacao, Usuario usuario, ModoTela modo) {
    
    this.votacaoAtual = votacao;
    this.usuarioLogado = usuario;
    this.modoAtual = modo;

    
    lblTituloEspecifico.setText(votacao.getTitulo());
    txtAreaDescricaoVotacao.setText(votacao.getDescricao());
    
    painelDeOpcoes.removeAll(); 

    
    switch (modo) {
        case VOTAR:
            
            btnSalvarVoto.setVisible(true); // Garante que o botão de salvar aparece

            OpcaoVotoRepository opcaoRepo = new OpcaoVotoRepository();
            this.grupoDeOpcoes = new javax.swing.ButtonGroup();
            try {
                List<OpcaoVoto> opcoes = opcaoRepo.buscarPorIdVotacao(votacao.getIdVotacao());
                for (OpcaoVoto opcao : opcoes) {
                    javax.swing.JRadioButton radioBotao = new javax.swing.JRadioButton(opcao.getDescricao());
                    radioBotao.putClientProperty("opcao_objeto", opcao);
                    this.grupoDeOpcoes.add(radioBotao);
                    painelDeOpcoes.add(radioBotao);
                }
            } catch (Exception e) { e.printStackTrace(); }
            break;

        case RESULTADO:
           
            btnSalvarVoto.setVisible(false); 

            VotoRepository votoRepo = new VotoRepository();
            try {
                List<ResultadoVotacao> resultados = votoRepo.contarVotosPorVotacao(votacao.getIdVotacao());
                for (ResultadoVotacao resultado : resultados) {
                    JPanel painelLinha = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
                    JLabel lblDescricao = new JLabel(resultado.getDescricaoOpcao() + ": ");
                    JLabel lblQuantidade = new JLabel(String.valueOf(resultado.getQuantidadeVotos()) + " votos");
                    painelLinha.add(lblDescricao);
                    painelLinha.add(lblQuantidade);
                    painelDeOpcoes.add(painelLinha);
                }
            } catch (Exception e) { e.printStackTrace(); }
            break;
    }



        br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil btnVoltarCustom = (br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil) btnVoltarVotacao;
        btnVoltarCustom.setColor(corVoltar);
        btnVoltarCustom.setColorOver(corVoltarHover);
        btnVoltarCustom.setColorClick(corVoltarClick);

        Color corSalvar = new Color(0x0095FF);
        Color corSalvarHover = new Color(0x33ADFF);
        Color corSalvarClick = new Color(0x0078CC);

        btnSalvarVoto.setText("SALVAR");
        btnSalvarVoto.setForeground(Color.WHITE);
        btnSalvarVoto.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11));
        btnSalvarVoto.setFocusPainted(false);
        btnSalvarVoto.setBorder(null);
        
        btnSalvarVoto.setPreferredSize(new java.awt.Dimension(100, 25));


        br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil btnSalvarCustom = (br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil) btnSalvarVoto;
        btnSalvarCustom.setColor(corSalvar);
        btnSalvarCustom.setColorOver(corSalvarHover);
        btnSalvarCustom.setColorClick(corSalvarClick);

    }
    public void carregarDadosVotacao(Votacao votacao, Usuario usuario) {

        this.votacaoAtual = votacao;
        this.usuarioLogado = usuario;


        lblTituloEspecifico.setText(votacao.getTitulo());
        txtAreaDescricaoVotacao.setText(votacao.getDescricao());
        lblPerguntaPrincipal.setText(votacao.getPergunta());


        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        if (votacao.getDataInicial() != null) {
            lblDataInicial.setText(sdf.format(votacao.getDataInicial()));
        }
        if (votacao.getDataFinal() != null) {
            lblDataFinal.setText(sdf.format(votacao.getDataFinal()));
        }
        if (votacao.getDataResultado() != null) {
            lblDataResultado.setText(sdf.format(votacao.getDataResultado()));
        }

        try {
            GrupoRepository grupoRepo = new GrupoRepository();
            Grupo grupo = grupoRepo.buscarPorId(votacao.getIdGrupoDestino());
            if (grupo != null) {
                lblParticipantes.setText(grupo.getNome());
            } else {
                lblParticipantes.setText("Grupo não encontrado");
            }
        } catch (Exception e) {
            lblParticipantes.setText("Erro ao buscar grupo");
            e.printStackTrace();
        }


        OpcaoVotoRepository opcaoRepo = new OpcaoVotoRepository();
        painelDeOpcoes.removeAll(); 
        try {
            java.util.List<OpcaoVoto> opcoes = opcaoRepo.buscarPorIdVotacao(votacao.getIdVotacao());
                System.out.println("DEBUG: Encontradas " + opcoes.size() + " opções para a votação ID " + votacao.getIdVotacao());


            for (OpcaoVoto opcao : opcoes) {

                javax.swing.JRadioButton radioBotao = new javax.swing.JRadioButton(opcao.getDescricao());
                radioBotao.setFont(new java.awt.Font("Segoe UI", 0, 12));


                radioBotao.putClientProperty("opcao_objeto", opcao);


                this.grupoDeOpcoes.add(radioBotao);
                painelDeOpcoes.add(radioBotao);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }


        this.revalidate();
        this.repaint();
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        painelConteudoCentral = new javax.swing.JPanel();
        painelBrancoInterno = new PainelArredondadoUtil();
        painelTitulo = new javax.swing.JPanel();
        lblTituloDaPagina = new javax.swing.JLabel();
        painelBotoesAcao = new javax.swing.JPanel();
        btnVoltarVotacao = new br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil();
        btnSalvarVoto = new br.edu.ifro.calama.votacaofeedback.util.RoundedButtonUtil();
        painelCorpoConteudo = new javax.swing.JPanel();
        painelTextosVotacao = new javax.swing.JPanel();
        lblTituloEspecifico = new javax.swing.JLabel();
        txtAreaDescricaoVotacao = new javax.swing.JTextArea();
        painelInfoCard = new javax.swing.JPanel();
        DataInicio = new javax.swing.JLabel();
        lblDataInicial = new javax.swing.JLabel();
        DataFim = new javax.swing.JLabel();
        lblDataFinal = new javax.swing.JLabel();
        DataREsultado = new javax.swing.JLabel();
        lblDataResultado = new javax.swing.JLabel();
        TipoVotacao = new javax.swing.JLabel();
        lblTipoVotacao = new javax.swing.JLabel();
        Participantes = new javax.swing.JLabel();
        lblParticipantes = new javax.swing.JLabel();
        painelPerguntaEOpcoes = new javax.swing.JPanel();
        lblPerguntaPrincipal = new javax.swing.JLabel();
        painelDeOpcoes = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();

        setLayout(new java.awt.BorderLayout());

        painelConteudoCentral.setBackground(new java.awt.Color(240, 240, 240));
        painelConteudoCentral.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 50, 50, 50));
        painelConteudoCentral.setLayout(new java.awt.BorderLayout());

        painelBrancoInterno.setBackground(new java.awt.Color(236, 236, 236));
        painelBrancoInterno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        painelBrancoInterno.setLayout(new java.awt.BorderLayout());

        painelTitulo.setBackground(new java.awt.Color(0, 0, 51));
        painelTitulo.setPreferredSize(new java.awt.Dimension(100, 60));
        painelTitulo.setLayout(new java.awt.BorderLayout());

        lblTituloDaPagina.setFont(new java.awt.Font("Silom", 1, 18)); // NOI18N
        lblTituloDaPagina.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloDaPagina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloDaPagina.setText("VOTAÇÃO");
        painelTitulo.add(lblTituloDaPagina, java.awt.BorderLayout.CENTER);

        painelBrancoInterno.add(painelTitulo, java.awt.BorderLayout.NORTH);

        painelBotoesAcao.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnVoltarVotacao.setText("X Voltar");
        btnVoltarVotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarVotacaoActionPerformed(evt);
            }
        });
        painelBotoesAcao.add(btnVoltarVotacao);

        btnSalvarVoto.setText("✓ Salvar");
        btnSalvarVoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarVotoActionPerformed(evt);
            }
        });
        painelBotoesAcao.add(btnSalvarVoto);

        painelBrancoInterno.add(painelBotoesAcao, java.awt.BorderLayout.SOUTH);

        painelCorpoConteudo.setLayout(new java.awt.BorderLayout());

        painelTextosVotacao.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 20, 15, 20));
        painelTextosVotacao.setLayout(new java.awt.BorderLayout());

        lblTituloEspecifico.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTituloEspecifico.setText("VOTAÇÃO PARA DIREÇÃO-GERAL DO CAMPUS CALAMA");
        painelTextosVotacao.add(lblTituloEspecifico, java.awt.BorderLayout.PAGE_START);

        txtAreaDescricaoVotacao.setEditable(false);
        txtAreaDescricaoVotacao.setColumns(20);
        txtAreaDescricaoVotacao.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtAreaDescricaoVotacao.setLineWrap(true);
        txtAreaDescricaoVotacao.setRows(3);
        txtAreaDescricaoVotacao.setText("Lorem ipsum.");
        txtAreaDescricaoVotacao.setWrapStyleWord(true);
        txtAreaDescricaoVotacao.setOpaque(false);
        painelTextosVotacao.add(txtAreaDescricaoVotacao, java.awt.BorderLayout.CENTER);

        painelInfoCard.setLayout(new java.awt.GridLayout(5, 2, 4, 5));

        DataInicio.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        DataInicio.setText("Data Início:");
        painelInfoCard.add(DataInicio);

        lblDataInicial.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        lblDataInicial.setText("jLabel3");
        painelInfoCard.add(lblDataInicial);

        DataFim.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        DataFim.setText("Data Fim:");
        painelInfoCard.add(DataFim);

        lblDataFinal.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        lblDataFinal.setText("jLabel4");
        painelInfoCard.add(lblDataFinal);

        DataREsultado.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        DataREsultado.setText("Data Resultado:");
        painelInfoCard.add(DataREsultado);

        lblDataResultado.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        lblDataResultado.setText("jLabel6");
        painelInfoCard.add(lblDataResultado);

        TipoVotacao.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        TipoVotacao.setText("Tipo Votação:");
        painelInfoCard.add(TipoVotacao);

        lblTipoVotacao.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        lblTipoVotacao.setText("Escolha Única");
        painelInfoCard.add(lblTipoVotacao);

        Participantes.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        Participantes.setText("Participantes:");
        painelInfoCard.add(Participantes);

        lblParticipantes.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        lblParticipantes.setText("jLabel7");
        painelInfoCard.add(lblParticipantes);

        painelTextosVotacao.add(painelInfoCard, java.awt.BorderLayout.EAST);

        painelCorpoConteudo.add(painelTextosVotacao, java.awt.BorderLayout.NORTH);

        painelPerguntaEOpcoes.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelPerguntaEOpcoes.setLayout(new javax.swing.BoxLayout(painelPerguntaEOpcoes, javax.swing.BoxLayout.Y_AXIS));

        lblPerguntaPrincipal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPerguntaPrincipal.setText("Pergunta");
        painelPerguntaEOpcoes.add(lblPerguntaPrincipal);

        painelDeOpcoes.setLayout(new javax.swing.BoxLayout(painelDeOpcoes, javax.swing.BoxLayout.Y_AXIS));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("teste1");
        painelDeOpcoes.add(jRadioButton1);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("teste2");
        painelDeOpcoes.add(jRadioButton2);

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("teste3");
        painelDeOpcoes.add(jRadioButton3);

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setText("teste4");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        painelDeOpcoes.add(jRadioButton4);

        buttonGroup1.add(jRadioButton5);
        jRadioButton5.setText("teste5");
        painelDeOpcoes.add(jRadioButton5);

        painelPerguntaEOpcoes.add(painelDeOpcoes);

        painelCorpoConteudo.add(painelPerguntaEOpcoes, java.awt.BorderLayout.CENTER);

        painelBrancoInterno.add(painelCorpoConteudo, java.awt.BorderLayout.CENTER);

        painelConteudoCentral.add(painelBrancoInterno, java.awt.BorderLayout.PAGE_START);

        add(painelConteudoCentral, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void btnVoltarVotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarVotacaoActionPerformed

        if (this.modoAtual == ModoTela.VOTAR) {
            this.menuPrincipal.navegarPara("ATIVAS");
        } else {
            this.menuPrincipal.navegarPara("ARQUIVADAS");
        }
    }//GEN-LAST:event_btnVoltarVotacaoActionPerformed

    private void btnSalvarVotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarVotoActionPerformed
    OpcaoVoto opcaoSelecionada = null;

    for (java.util.Enumeration<javax.swing.AbstractButton> buttons = this.grupoDeOpcoes.getElements(); buttons.hasMoreElements();) {
        javax.swing.AbstractButton button = buttons.nextElement();
        if (button.isSelected()) {
            opcaoSelecionada = (OpcaoVoto) button.getClientProperty("opcao_objeto");
            break;
        }
    }

    if (opcaoSelecionada == null) {
        if (menuPrincipal != null) {
            menuPrincipal.exibirMensagemDeErro("Selecione uma opção para votar.");
        }
        return;
    }

    try {
        VotoService votoService = new VotoService();
        votoService.registrarVoto(this.votacaoAtual, opcaoSelecionada, this.usuarioLogado);

        if (menuPrincipal != null) {
            menuPrincipal.exibirMensagemDeSucesso("Voto registrado com sucesso!");
            
            menuPrincipal.navegarPara("ATIVAS");
        }

    } catch (Exception e) {
        if (menuPrincipal != null) {
            menuPrincipal.exibirMensagemDeErro("Seu voto já foi registrado!");
        }
        e.printStackTrace();
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarVotoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DataFim;
    private javax.swing.JLabel DataInicio;
    private javax.swing.JLabel DataREsultado;
    private javax.swing.JLabel Participantes;
    private javax.swing.JLabel TipoVotacao;
    private javax.swing.JButton btnSalvarVoto;
    private javax.swing.JButton btnVoltarVotacao;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JLabel lblDataFinal;
    private javax.swing.JLabel lblDataInicial;
    private javax.swing.JLabel lblDataResultado;
    private javax.swing.JLabel lblParticipantes;
    private javax.swing.JLabel lblPerguntaPrincipal;
    private javax.swing.JLabel lblTipoVotacao;
    private javax.swing.JLabel lblTituloDaPagina;
    private javax.swing.JLabel lblTituloEspecifico;
    private javax.swing.JPanel painelBotoesAcao;
    private javax.swing.JPanel painelBrancoInterno;
    private javax.swing.JPanel painelConteudoCentral;
    private javax.swing.JPanel painelCorpoConteudo;
    private javax.swing.JPanel painelDeOpcoes;
    private javax.swing.JPanel painelInfoCard;
    private javax.swing.JPanel painelPerguntaEOpcoes;
    private javax.swing.JPanel painelTextosVotacao;
    private javax.swing.JPanel painelTitulo;
    private javax.swing.JTextArea txtAreaDescricaoVotacao;
    // End of variables declaration//GEN-END:variables

}

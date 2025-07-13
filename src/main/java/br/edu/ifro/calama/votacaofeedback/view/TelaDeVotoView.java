/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;

import br.edu.ifro.calama.votacaofeedback.model.Grupo;
import br.edu.ifro.calama.votacaofeedback.model.OpcaoVoto;
import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.model.Votacao;
import br.edu.ifro.calama.votacaofeedback.repository.GrupoRepository;
import br.edu.ifro.calama.votacaofeedback.repository.OpcaoVotoRepository;
import br.edu.ifro.calama.votacaofeedback.service.VotoService;
import java.util.List;

/**
 *
 * @author floriano
 */
public class TelaDeVotoView extends javax.swing.JPanel {
  private Votacao votacaoAtual;
private Usuario usuarioLogado;
private javax.swing.ButtonGroup grupoDeOpcoes;
public enum ModoTela {
    VOTAR,
    RESULTADO
}
    /**
     * Creates new form TelaDeVotoView
     */
    public TelaDeVotoView() {
        initComponents();
        this.grupoDeOpcoes = new javax.swing.ButtonGroup();
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
            radioBotao.setFont(new java.awt.Font("Segoe UI", 0, 14));

           
            radioBotao.putClientProperty("opcao_objeto", opcao);

          
            this.grupoDeOpcoes.add(radioBotao);
            painelDeOpcoes.add(radioBotao);
        }
    } catch (Exception e) {
        e.printStackTrace();
        
    }

    
    painelDeOpcoes.revalidate();
    painelDeOpcoes.repaint();
}
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        painelTitulo = new javax.swing.JPanel();
        lblTituloDaPagina = new javax.swing.JLabel();
        painelBotoesAcao = new javax.swing.JPanel();
        btnVoltarVotacao = new javax.swing.JButton();
        btnSalvarVoto = new javax.swing.JButton();
        painelConteudoCentral = new javax.swing.JPanel();
        painelTextosVotacao = new javax.swing.JPanel();
        lblTituloEspecifico = new javax.swing.JLabel();
        txtAreaDescricaoVotacao = new javax.swing.JTextArea();
        lblPerguntaPrincipal = new javax.swing.JLabel();
        painelDeOpcoes = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        painelInfoCard = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblDataInicial = new javax.swing.JLabel();
        lblDataFinal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblDataResultado = new javax.swing.JLabel();
        lblParticipantes = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        painelTitulo.setBackground(new java.awt.Color(0, 0, 51));
        painelTitulo.setPreferredSize(new java.awt.Dimension(100, 60));
        painelTitulo.setLayout(new java.awt.BorderLayout());

        lblTituloDaPagina.setFont(new java.awt.Font("Silom", 1, 18)); // NOI18N
        lblTituloDaPagina.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloDaPagina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloDaPagina.setText("VOTAÇÃO");
        painelTitulo.add(lblTituloDaPagina, java.awt.BorderLayout.CENTER);

        add(painelTitulo, java.awt.BorderLayout.PAGE_START);

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

        add(painelBotoesAcao, java.awt.BorderLayout.PAGE_END);

        painelConteudoCentral.setLayout(new java.awt.BorderLayout());

        painelTextosVotacao.setLayout(new javax.swing.BoxLayout(painelTextosVotacao, javax.swing.BoxLayout.Y_AXIS));

        lblTituloEspecifico.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblTituloEspecifico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloEspecifico.setText("VOTAÇÃO PARA DIREÇÃO-GERAL DO CAMPUS CALAMA");
        painelTextosVotacao.add(lblTituloEspecifico);

        txtAreaDescricaoVotacao.setEditable(false);
        txtAreaDescricaoVotacao.setColumns(20);
        txtAreaDescricaoVotacao.setLineWrap(true);
        txtAreaDescricaoVotacao.setRows(5);
        txtAreaDescricaoVotacao.setText("Lorem ipsum.");
        txtAreaDescricaoVotacao.setWrapStyleWord(true);
        txtAreaDescricaoVotacao.setOpaque(false);
        painelTextosVotacao.add(txtAreaDescricaoVotacao);

        lblPerguntaPrincipal.setText("Pergunta");
        painelTextosVotacao.add(lblPerguntaPrincipal);

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

        painelTextosVotacao.add(painelDeOpcoes);

        painelConteudoCentral.add(painelTextosVotacao, java.awt.BorderLayout.CENTER);

        jLabel1.setText("lblDataInicioInfo");

        jLabel2.setText("lblDataFimInfo");

        lblDataInicial.setText("jLabel3");

        lblDataFinal.setText("jLabel4");

        jLabel5.setText("lblDataResultadoInfo");

        lblDataResultado.setText("jLabel6");

        lblParticipantes.setText("jLabel7");

        jLabel8.setText("lblTipoVotacaoInfo");

        jLabel3.setText("jLabel3");

        jLabel4.setText("lblParticipantesInfo");

        javax.swing.GroupLayout painelInfoCardLayout = new javax.swing.GroupLayout(painelInfoCard);
        painelInfoCard.setLayout(painelInfoCardLayout);
        painelInfoCardLayout.setHorizontalGroup(
            painelInfoCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelInfoCardLayout.createSequentialGroup()
                .addGroup(painelInfoCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelInfoCardLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDataInicial))
                    .addGroup(painelInfoCardLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDataFinal))
                    .addGroup(painelInfoCardLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(lblDataResultado))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelInfoCardLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelInfoCardLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblParticipantes)))
                .addContainerGap())
        );
        painelInfoCardLayout.setVerticalGroup(
            painelInfoCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelInfoCardLayout.createSequentialGroup()
                .addGroup(painelInfoCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblDataInicial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelInfoCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(painelInfoCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblDataResultado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelInfoCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3))
                .addGap(13, 13, 13)
                .addGroup(painelInfoCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblParticipantes)
                    .addComponent(jLabel4))
                .addGap(0, 237, Short.MAX_VALUE))
        );

        painelConteudoCentral.add(painelInfoCard, java.awt.BorderLayout.LINE_END);

        add(painelConteudoCentral, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void btnVoltarVotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarVotacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVoltarVotacaoActionPerformed

    private void btnSalvarVotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarVotoActionPerformed
    System.out.println("--- 1. Botão Salvar Clicado ---");
    OpcaoVoto opcaoSelecionada = null;

    for (java.util.Enumeration<javax.swing.AbstractButton> buttons = this.grupoDeOpcoes.getElements(); buttons.hasMoreElements();) {
        javax.swing.AbstractButton button = buttons.nextElement();
        if (button.isSelected()) {
            opcaoSelecionada = (OpcaoVoto) button.getClientProperty("opcao_objeto");
            break;
        }
    }

    if (opcaoSelecionada == null) {
        javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecione uma opção para votar.", "Atenção", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    System.out.println("Opção selecionada: " + opcaoSelecionada.getDescricao() + " (ID: " + opcaoSelecionada.getIdOpcaoVoto() + ")");

    try {
        VotoService votoService = new VotoService();
        votoService.registrarVoto(this.votacaoAtual, opcaoSelecionada, this.usuarioLogado);

        javax.swing.JOptionPane.showMessageDialog(this, "Voto registrado com sucesso!");


        MenuPrincipalView menu = (MenuPrincipalView) javax.swing.SwingUtilities.getWindowAncestor(this);
        if (menu != null) {
            menu.navegarParaVotacoesAtivas();
        }

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro ao registrar voto:\n" + e.getMessage(), "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarVotoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvarVoto;
    private javax.swing.JButton btnVoltarVotacao;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
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
    private javax.swing.JLabel lblTituloDaPagina;
    private javax.swing.JLabel lblTituloEspecifico;
    private javax.swing.JPanel painelBotoesAcao;
    private javax.swing.JPanel painelConteudoCentral;
    private javax.swing.JPanel painelDeOpcoes;
    private javax.swing.JPanel painelInfoCard;
    private javax.swing.JPanel painelTextosVotacao;
    private javax.swing.JPanel painelTitulo;
    private javax.swing.JTextArea txtAreaDescricaoVotacao;
    // End of variables declaration//GEN-END:variables
}

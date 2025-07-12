/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;

import br.edu.ifro.calama.votacaofeedback.model.Grupo;
import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.model.Votacao;
import br.edu.ifro.calama.votacaofeedback.repository.GrupoRepository;
import br.edu.ifro.calama.votacaofeedback.repository.VotacaoRepository;
import java.awt.Component;
/**
 *
 * @author floriano
 */
public class DetalhesVotacaoDialog extends javax.swing.JDialog {
    private Votacao votacaoAtual;
    private final Component telaDeOrigem;
    private AprovarVotacaoView origem;
    private Usuario usuarioLogado;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DetalhesVotacaoDialog.class.getName());
    
    public enum ModoDialogo {
        APROVACAO,
        GERENCIAMENTO
    }
    
    public DetalhesVotacaoDialog(java.awt.Frame parent, boolean modal, Usuario usuario, Component telaDeOrigem) {
        super(parent, modal);
        this.usuarioLogado = usuario;
        this.telaDeOrigem = telaDeOrigem;
        initComponents();
        
        // Lógica para esconder os botões inicialmente
        btnAprovarDialog.setVisible(false);
        btnReprovarDialog.setVisible(false);
        btnEditar.setVisible(false);
    }
    
    public void setModo(ModoDialogo modo) {
        if (modo == ModoDialogo.APROVACAO) {
            btnAprovarDialog.setVisible(true);
            btnReprovarDialog.setVisible(true);
            btnEditar.setVisible(false);
        } else if (modo == ModoDialogo.GERENCIAMENTO) {
            btnAprovarDialog.setVisible(false);
            btnReprovarDialog.setVisible(false);
            btnEditar.setVisible(true);
        }

    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtAreaDescricao = new javax.swing.JTextArea();
        btnVoltarDialog = new javax.swing.JButton();
        btnReprovarDialog = new javax.swing.JButton();
        btnAprovarDialog = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblTituloPrincipal = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblDataInicial = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblParticipantes = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblPerguntaPrincipal = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblDataFinal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblTipoVotacao = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblDataResultado = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Screenshot_2-Photoroom.png"))); // NOI18N

        txtAreaDescricao.setEditable(false);
        txtAreaDescricao.setColumns(20);
        txtAreaDescricao.setLineWrap(true);
        txtAreaDescricao.setRows(5);
        txtAreaDescricao.setWrapStyleWord(true);
        txtAreaDescricao.setBorder(null);
        txtAreaDescricao.setOpaque(false);

        btnVoltarDialog.setBackground(new java.awt.Color(102, 102, 102));
        btnVoltarDialog.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVoltarDialog.setForeground(new java.awt.Color(255, 255, 255));
        btnVoltarDialog.setText("X Voltar");
        btnVoltarDialog.setBorder(null);
        btnVoltarDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarDialogActionPerformed(evt);
            }
        });

        btnReprovarDialog.setBackground(new java.awt.Color(204, 0, 0));
        btnReprovarDialog.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReprovarDialog.setForeground(new java.awt.Color(255, 255, 255));
        btnReprovarDialog.setText("X Reprovar");
        btnReprovarDialog.setBorder(null);
        btnReprovarDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReprovarDialogActionPerformed(evt);
            }
        });

        btnAprovarDialog.setBackground(new java.awt.Color(0, 204, 0));
        btnAprovarDialog.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAprovarDialog.setForeground(new java.awt.Color(255, 255, 255));
        btnAprovarDialog.setText("✓ Aprovar");
        btnAprovarDialog.setBorder(null);
        btnAprovarDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAprovarDialogActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(11, 41, 81));

        lblTituloPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTituloPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPrincipal.setText("VOTAÇÃO PARA DIREÇÃO-GERAL DO CAMPUS CALAMA");

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/WhatsApp Image 2025-07-10 at 00.50.52-Photoroom.png"))); // NOI18N

        jSeparator2.setForeground(new java.awt.Color(51, 153, 0));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel8.setBackground(new java.awt.Color(102, 102, 102));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Campus");

        jLabel10.setBackground(new java.awt.Color(102, 102, 102));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Porto Velho Calama");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel8))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTituloPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTituloPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel2.setText("Data Inicial da Votação");

        lblDataInicial.setText("lblDataInicial");

        jLabel4.setText("Participantes");

        lblParticipantes.setText("lblParticipantes");

        jLabel7.setText("Pergunta Principal");

        lblPerguntaPrincipal.setText("escreva aqui");

        jLabel3.setText("Data Final da Votação:");

        lblDataFinal.setText("lblDataFinal");

        jLabel5.setText("Tipo da Votação");

        lblTipoVotacao.setText("Escolha Unica");

        jLabel6.setText("Data do Resultado da Votação");

        lblDataResultado.setText("lblDataResultado");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel5))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblDataInicial)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDataFinal)))
                        .addGap(51, 51, 51))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblParticipantes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTipoVotacao)
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblPerguntaPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                                        .addComponent(jLabel6))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)))
                        .addGap(22, 22, 22))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDataResultado)
                .addGap(40, 40, 40))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDataInicial)
                            .addComponent(lblDataFinal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblParticipantes))
                    .addComponent(lblTipoVotacao, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDataResultado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPerguntaPrincipal)
                .addContainerGap())
        );

        btnEditar.setBackground(new java.awt.Color(0, 153, 204));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("✓ Editar");
        btnEditar.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(300, 300, 300)
                .addComponent(txtAreaDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(106, 106, 106))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnAprovarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReprovarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(310, 310, 310)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnVoltarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(txtAreaDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(43, 43, 43)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAprovarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReprovarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoltarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReprovarDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReprovarDialogActionPerformed
   try {
        VotacaoRepository votacaoRepo = new VotacaoRepository();
        votacaoRepo.atualizarStatus(this.votacaoAtual.getIdVotacao(), "REPROVADA");

        javax.swing.JOptionPane.showMessageDialog(this, "Votação Reprovada.");
        if (telaDeOrigem != null) {
            ((AprovarVotacaoView) telaDeOrigem).carregarVotacoesParaAprovacao();
        }
        this.dispose();
    } catch (Exception e) {
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this, "Erro ao reprovar votação.", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnReprovarDialogActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (this.votacaoAtual != null) {
            System.out.println("[DEBUG DetalhesVotacaoDialog] Enviando votação com título: '" + this.votacaoAtual.getTitulo() + "'");
        } else {
            System.out.println("[DEBUG DetalhesVotacaoDialog] ERRO: Tentando enviar uma votação NULA!");
        }
        
        if (this.votacaoAtual != null && this.usuarioLogado != null) {
            CriarVotacaoView telaDeEdicao = new CriarVotacaoView(this.usuarioLogado, this.votacaoAtual, true);
            telaDeEdicao.setLocationRelativeTo(null);
            telaDeEdicao.setVisible(true);

            ((java.awt.Frame) getParent()).dispose();
            this.dispose();
        }
    }//GEN-LAST:event_btnEditarActionPerformed
    private void btnAprovarDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAprovarDialogActionPerformed
        try {
        VotacaoRepository votacaoRepo = new VotacaoRepository();
        // Chama o método para APROVAR, usando o ID da votação que está sendo exibida
        votacaoRepo.atualizarStatus(this.votacaoAtual.getIdVotacao(), "APROVADA");

        // Mostra uma mensagem de sucesso e fecha o pop-up
        javax.swing.JOptionPane.showMessageDialog(this, "Votação Aprovada com Sucesso!");
        if (telaDeOrigem != null) {
            ((GerenciarVotacaoView) telaDeOrigem).carregarVotacoesDoUsuario();
        }
        this.dispose(); 
    } catch (Exception e) {
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this, "Erro ao aprovar votação.", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
    }    
    }//GEN-LAST:event_btnAprovarDialogActionPerformed

    private void btnVoltarDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarDialogActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnVoltarDialogActionPerformed

    /**
     * @param args the command line arguments
     */
   
public void setDados(Votacao votacao) {
    this.votacaoAtual = votacao; 
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
    lblTituloPrincipal.setText(votacao.getTitulo());
    txtAreaDescricao.setText(votacao.getDescricao());
    if (votacao.getDataInicial() != null) {
        lblDataInicial.setText(sdf.format(votacao.getDataInicial()));
    }
    if (votacao.getDataFinal() != null) {
        lblDataFinal.setText(sdf.format(votacao.getDataFinal()));
    }
    if (votacao.getDataResultado() != null) {
        lblDataResultado.setText(sdf.format(votacao.getDataResultado()));
    }
    lblPerguntaPrincipal.setText(votacao.getPergunta());
    
    try {
        GrupoRepository grupoRepo = new GrupoRepository();
        int idDoGrupo = votacao.getIdGrupoDestino();
        Grupo grupo = grupoRepo.buscarPorId(idDoGrupo);
        if (grupo != null) {
            lblParticipantes.setText(grupo.getNome()); 
        } else {
            lblParticipantes.setText("Grupo não encontrado");
        }
    } catch (Exception e) {
        lblParticipantes.setText("Erro ao buscar dados");
        e.printStackTrace();
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAprovarDialog;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnReprovarDialog;
    private javax.swing.JButton btnVoltarDialog;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblDataFinal;
    private javax.swing.JLabel lblDataInicial;
    private javax.swing.JLabel lblDataResultado;
    private javax.swing.JLabel lblParticipantes;
    private javax.swing.JLabel lblPerguntaPrincipal;
    private javax.swing.JLabel lblTipoVotacao;
    private javax.swing.JLabel lblTituloPrincipal;
    private javax.swing.JTextArea txtAreaDescricao;
    // End of variables declaration//GEN-END:variables
}
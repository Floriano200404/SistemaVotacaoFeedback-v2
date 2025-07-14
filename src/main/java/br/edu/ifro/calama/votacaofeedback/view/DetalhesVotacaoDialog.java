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
import br.edu.ifro.calama.votacaofeedback.view.AprovarVotacaoView;
import br.edu.ifro.calama.votacaofeedback.view.CriarVotacaoView;
import br.edu.ifro.calama.votacaofeedback.view.GerenciarVotacaoView;
import br.edu.ifro.calama.votacaofeedback.view.MenuPrincipalView;
import java.awt.Component;
/**
 *
 * @author floriano
 */
public class DetalhesVotacaoDialog extends javax.swing.JDialog {
      public enum ModoDialogo {
        APROVACAO,
        GERENCIAMENTO,
        PARTICIPACAO,
        RESULTADO
    }
    private Votacao votacaoAtual;
    private final Component telaDeOrigem;
    private AprovarVotacaoView origem;
    private Usuario usuarioLogado;
    private ModoDialogo modo;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DetalhesVotacaoDialog.class.getName());

    public DetalhesVotacaoDialog(java.awt.Frame parent, boolean modal, Usuario usuario, Component telaDeOrigem) {
        super(parent, modal);
        this.usuarioLogado = usuario;
        this.telaDeOrigem = telaDeOrigem;
        initComponents();
        
       
        btnAprovarDialog.setVisible(false);
        btnReprovarDialog.setVisible(false);
        btnEditar.setVisible(false);
    }
    
    public void setModo(ModoDialogo modo) {
        this.modo = modo;

        // Esconde todos por padrão
        btnAprovarDialog.setVisible(false);
        btnReprovarDialog.setVisible(false);
        btnParticipar.setVisible(false);
        btnEditar.setVisible(false);
        btnVerResultado.setVisible(false);

        // Mostra apenas os necessários para o modo atual
        if (modo == null) return;

        switch (modo) {
            case APROVACAO:
                btnAprovarDialog.setVisible(true);
                btnReprovarDialog.setVisible(true);
                break;
            case PARTICIPACAO:
                btnParticipar.setVisible(true);
                break;
            case GERENCIAMENTO:
                btnEditar.setVisible(true);
                break;
            case RESULTADO:
                btnVerResultado.setVisible(true);
                break;
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblTituloPrincipal = new javax.swing.JLabel();
        txtAreaDescricao = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        lblDataInicial = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblDataFinal = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblParticipantes = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblDataResultado = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblTipoVotacao = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblPerguntaPrincipal = new javax.swing.JLabel();
        btnVoltarDialog = new javax.swing.JButton();
        btnReprovarDialog = new javax.swing.JButton();
        btnAprovarDialog = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnParticipar = new javax.swing.JButton();
        btnVerResultado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/IF.png"))); // NOI18N

        lblTituloPrincipal.setText("VOTAÇÃO PARA DIREÇÃO-GERAL..");

        txtAreaDescricao.setEditable(false);
        txtAreaDescricao.setColumns(20);
        txtAreaDescricao.setLineWrap(true);
        txtAreaDescricao.setRows(5);
        txtAreaDescricao.setWrapStyleWord(true);
        txtAreaDescricao.setBorder(null);
        txtAreaDescricao.setOpaque(false);

        jLabel2.setText("Data Inicial da Votação");

        lblDataInicial.setText("lblDataInicial");

        jLabel3.setText("Data Final da Votação:");

        lblDataFinal.setText("lblDataFinal");

        jLabel4.setText("Participantes");

        lblParticipantes.setText("lblParticipantes");

        jLabel6.setText("Data do Resultado da Votação");

        lblDataResultado.setText("lblDataResultado");

        jLabel5.setText("Tipo da Votação");

        lblTipoVotacao.setText("Escolha Unica");

        jLabel7.setText("Pergunta Principal");

        lblPerguntaPrincipal.setText("escreva aqui");

        btnVoltarDialog.setText("X Voltar");
        btnVoltarDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarDialogActionPerformed(evt);
            }
        });

        btnReprovarDialog.setText("X Reprovar");
        btnReprovarDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReprovarDialogActionPerformed(evt);
            }
        });

        btnAprovarDialog.setText("✓ Aprovar");
        btnAprovarDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAprovarDialogActionPerformed(evt);
            }
        });

        btnEditar.setText("✓ Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnParticipar.setText("Participar");
        btnParticipar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParticiparActionPerformed(evt);
            }
        });

        btnVerResultado.setText("Resultado");
        btnVerResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerResultadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblDataInicial)
                        .addGap(114, 114, 114)
                        .addComponent(lblDataFinal)
                        .addGap(72, 72, 72)
                        .addComponent(lblDataResultado))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblParticipantes))
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(lblPerguntaPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnVoltarDialog)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReprovarDialog))
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblTipoVotacao))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAprovarDialog)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnParticipar)
                                .addGap(18, 18, 18)
                                .addComponent(btnVerResultado)))))
                .addGap(132, 132, 132))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtAreaDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTituloPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTituloPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(txtAreaDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataFinal)
                    .addComponent(lblDataResultado)
                    .addComponent(lblDataInicial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoVotacao)
                    .addComponent(lblParticipantes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(btnVoltarDialog)
                    .addComponent(btnReprovarDialog))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPerguntaPrincipal)
                        .addComponent(btnAprovarDialog)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnParticipar)
                    .addComponent(btnVerResultado))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 390));

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
        
        votacaoRepo.atualizarStatus(this.votacaoAtual.getIdVotacao(), "APROVADA");

       
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

    private void btnParticiparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParticiparActionPerformed
       MenuPrincipalView menuPrincipal = (MenuPrincipalView) javax.swing.SwingUtilities.getWindowAncestor(this);
        if (menuPrincipal != null && this.votacaoAtual != null) {
        menuPrincipal.navegarParaTelaDeVoto(this.votacaoAtual, TelaDeVotoView.ModoTela.VOTAR);
        }
        this.dispose();
    }//GEN-LAST:event_btnParticiparActionPerformed

    private void btnVerResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerResultadoActionPerformed
        MenuPrincipalView menuPrincipal = (MenuPrincipalView) javax.swing.SwingUtilities.getWindowAncestor(this);
            if (menuPrincipal != null && this.votacaoAtual != null) {
            menuPrincipal.navegarParaTelaDeVoto(this.votacaoAtual, TelaDeVotoView.ModoTela.RESULTADO);
        }
        this.dispose();
    }//GEN-LAST:event_btnVerResultadoActionPerformed

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
    private javax.swing.JButton btnParticipar;
    private javax.swing.JButton btnReprovarDialog;
    private javax.swing.JButton btnVerResultado;
    private javax.swing.JButton btnVoltarDialog;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
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
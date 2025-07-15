/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;

import br.edu.ifro.calama.votacaofeedback.model.ResultadoVotacao;
import br.edu.ifro.calama.votacaofeedback.model.Votacao;
import br.edu.ifro.calama.votacaofeedback.service.VotacaoService;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author floriano
 */
public class ResultadoView extends javax.swing.JPanel {

    private MenuPrincipalView menuPrincipal;
    
    public ResultadoView(MenuPrincipalView menu) {
        initComponents();
        this.menuPrincipal = menu;
    }
    public void carregarResultados(Votacao votacao) {

    lblTituloDaPagina.setText("RESULTADO: " + votacao.getTitulo());

    VotacaoService votacaoService = new VotacaoService();

    painelListaResultados.removeAll();

    List<ResultadoVotacao> resultados = votacaoService.apurarResultados(votacao.getIdVotacao());

    for (ResultadoVotacao resultado : resultados) {
        JPanel painelLinha = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 5));

        JLabel lblDescricao = new JLabel(resultado.getDescricaoOpcao() + ":");
        JLabel lblQuantidade = new JLabel(String.valueOf(resultado.getQuantidadeVotos()) + " votos");

        lblDescricao.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lblQuantidade.setFont(new java.awt.Font("Segoe UI", 1, 14)); // Deixa a quantidade em negrito

        painelLinha.add(lblDescricao);
        painelLinha.add(lblQuantidade);

        painelListaResultados.add(painelLinha);
    }

    this.revalidate();
    this.repaint();
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelBotoesAcao = new javax.swing.JPanel();
        btnVoltar = new javax.swing.JButton();
        painelTitulo = new javax.swing.JPanel();
        lblTituloDaPagina = new javax.swing.JLabel();
        painelConteudoResultados = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        painelListaResultados = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        painelBotoesAcao.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnVoltar.setText("X Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });
        painelBotoesAcao.add(btnVoltar);

        add(painelBotoesAcao, java.awt.BorderLayout.PAGE_END);

        painelTitulo.setBackground(new java.awt.Color(0, 0, 51));
        painelTitulo.setPreferredSize(new java.awt.Dimension(100, 60));

        lblTituloDaPagina.setBackground(new java.awt.Color(0, 0, 51));
        lblTituloDaPagina.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblTituloDaPagina.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloDaPagina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloDaPagina.setText("RESULTADO DA VOTAÇÃO");

        javax.swing.GroupLayout painelTituloLayout = new javax.swing.GroupLayout(painelTitulo);
        painelTitulo.setLayout(painelTituloLayout);
        painelTituloLayout.setHorizontalGroup(
            painelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelTituloLayout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addComponent(lblTituloDaPagina)
                .addGap(89, 89, 89))
        );
        painelTituloLayout.setVerticalGroup(
            painelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTituloLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblTituloDaPagina)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        add(painelTitulo, java.awt.BorderLayout.PAGE_START);

        painelListaResultados.setLayout(new javax.swing.BoxLayout(painelListaResultados, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(painelListaResultados);

        javax.swing.GroupLayout painelConteudoResultadosLayout = new javax.swing.GroupLayout(painelConteudoResultados);
        painelConteudoResultados.setLayout(painelConteudoResultadosLayout);
        painelConteudoResultadosLayout.setHorizontalGroup(
            painelConteudoResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConteudoResultadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        painelConteudoResultadosLayout.setVerticalGroup(
            painelConteudoResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
        );

        add(painelConteudoResultados, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        if(this.menuPrincipal != null) {
            this.menuPrincipal.navegarPara("ARQUIVADAS");
        }
    }//GEN-LAST:event_btnVoltarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVoltar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTituloDaPagina;
    private javax.swing.JPanel painelBotoesAcao;
    private javax.swing.JPanel painelConteudoResultados;
    private javax.swing.JPanel painelListaResultados;
    private javax.swing.JPanel painelTitulo;
    // End of variables declaration//GEN-END:variables
}

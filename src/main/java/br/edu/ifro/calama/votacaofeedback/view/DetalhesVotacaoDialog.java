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
import java.awt.Color;
import br.edu.ifro.calama.votacaofeedback.view.AprovarVotacaoView;
import br.edu.ifro.calama.votacaofeedback.view.CriarVotacaoView;
import br.edu.ifro.calama.votacaofeedback.view.GerenciarVotacaoView;
import br.edu.ifro.calama.votacaofeedback.view.MenuPrincipalView;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.JPanel;


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

    class RoundedButton extends JButton {
        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
            super.paintComponent(g);
            g2.dispose();
        }
    }
    
    class RoundedPanel extends JPanel {
        public RoundedPanel() {
            super();
            setOpaque(false);
        }

        @Override
        protected void paintComponent (Graphics g){
          Graphics2D g2 = (Graphics2D) g.create();
          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          g2.setColor(getBackground());
          g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
          super.paintComponent(g);
          g2.dispose();
        }
    }
    
    class ShadowPanel extends JPanel {
        private int shadowSize = 5;
        private float shadowOpacity = 0.4f;
        private int arc = 20;

        public ShadowPanel() {
            super();
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = getWidth();
            int height = getHeight();

            for (int i = 0; i < shadowSize; i++) {
                g2.setColor(new Color(180, 180, 180, (int) (shadowOpacity * 255 * (1.0f - (float) i / shadowSize))));
                g2.fillRoundRect(i, i, width - i * 2, height - i * 2, arc, arc);
            }
            
            g2.setColor(getBackground());
            g2.fillRoundRect(shadowSize, shadowSize, width - shadowSize * 2, height - shadowSize * 2, arc, arc);
            g2.dispose();
            
            super.paintComponent(g);
        }

        @Override
        public java.awt.Insets getInsets() {
            return new java.awt.Insets(shadowSize, shadowSize, shadowSize, shadowSize);
        }
    }
    

   public DetalhesVotacaoDialog(java.awt.Frame parent, boolean modal, Usuario usuario, Component telaDeOrigem) {
        super(parent, modal);
        this.usuarioLogado = usuario;
        this.telaDeOrigem = telaDeOrigem;
        initComponents();
        
        pnlAcoesDinamicas.add(btnParticipar, "card_participar");
        pnlAcoesDinamicas.add(btnVerResultado, "card_resultado");
        pnlAcoesDinamicas.add(btnEditar, "card_editar");
        
        btnAprovarDialog.setVisible(false);
        btnReprovarDialog.setVisible(false);
        btnEditar.setVisible(false);
        lblDataInicial.setOpaque(false);
        lblDataFinal.setOpaque(false);
        lblDataResultado.setOpaque(false);
        lblParticipantes.setOpaque(false);
        lblTipoVotacao.setOpaque(false);
        lblPerguntaPrincipal.setOpaque(false);
    }
    
    public void setModo(ModoDialogo modo) {
        this.modo = modo;

        btnAprovarDialog.setVisible(false);
        btnReprovarDialog.setVisible(false);

        pnlAcoesDinamicas.setVisible(true);

        java.awt.CardLayout cl = (java.awt.CardLayout) pnlAcoesDinamicas.getLayout();

        if (modo == null) {
            pnlAcoesDinamicas.setVisible(false);
            return;
        }

        switch (modo) {
            case APROVACAO:
                btnAprovarDialog.setVisible(true);
                btnReprovarDialog.setVisible(true);
                pnlAcoesDinamicas.setVisible(false); 
                break;
            case PARTICIPACAO:
                cl.show(pnlAcoesDinamicas, "card_participar");
                break;
            case GERENCIAMENTO:
                cl.show(pnlAcoesDinamicas, "card_editar");
                break;
            case RESULTADO:
                cl.show(pnlAcoesDinamicas, "card_resultado");
                break;
            default:
                pnlAcoesDinamicas.setVisible(false);
                break;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnVoltarDialog = new RoundedButton("X Voltar");
        btnReprovarDialog = new RoundedButton("X Reprovar");
        btnAprovarDialog = new RoundedButton("✓ Aprovar");
        jPanel2 = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);

                java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;
                g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

                java.awt.Color color1 = new java.awt.Color(9, 32, 63);
                java.awt.Color color2 = new java.awt.Color(21, 52, 98);

                java.awt.GradientPaint gp = new java.awt.GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        lblTituloPrincipal = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtAreaDescricao = new javax.swing.JTextArea();
        jPanel4 = new RoundedPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new ShadowPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new RoundedPanel();
        lblDataInicial = new javax.swing.JLabel();
        jPanel7 = new RoundedPanel();
        lblDataFinal = new javax.swing.JLabel();
        jPanel8 = new RoundedPanel();
        lblDataResultado = new javax.swing.JLabel();
        jPanel9 = new RoundedPanel();
        lblPerguntaPrincipal = new javax.swing.JLabel();
        jPanel10 = new RoundedPanel();
        lblTipoVotacao = new javax.swing.JLabel();
        jPanel11 = new RoundedPanel();
        lblParticipantes = new javax.swing.JLabel();
        pnlAcoesDinamicas = new javax.swing.JPanel();
        btnParticipar = btnParticipar = new RoundedButton("✓ Participar");
        btnEditar = new RoundedButton("✓ Editar");
        btnVerResultado = btnVerResultado = new RoundedButton("✓ Resultado");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Screenshot_2-Photoroom.png"))); // NOI18N

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

        lblTituloPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTituloPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPrincipal.setText("VOTAÇÃO PARA DIREÇÃO-GERAL DO CAMPUS CALAMA");

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        txtAreaDescricao.setEditable(false);
        txtAreaDescricao.setBackground(new java.awt.Color(255, 255, 255));
        txtAreaDescricao.setColumns(20);
        txtAreaDescricao.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtAreaDescricao.setForeground(new java.awt.Color(255, 255, 255));
        txtAreaDescricao.setLineWrap(true);
        txtAreaDescricao.setRows(5);
        txtAreaDescricao.setWrapStyleWord(true);
        txtAreaDescricao.setBorder(null);
        txtAreaDescricao.setOpaque(false);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

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
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel8))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTituloPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAreaDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(318, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 8, Short.MAX_VALUE)
                        .addComponent(lblTituloPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAreaDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Data Inicial da Votação:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Participantes:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Pergunta Principal:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Data Final da Votação:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Tipo da Votação:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Data do Resultado da Votação:");

        jPanel5.setBackground(new java.awt.Color(239, 239, 239));
        jPanel5.setForeground(new java.awt.Color(220, 220, 220));

        lblDataInicial.setBackground(new java.awt.Color(204, 204, 204));
        lblDataInicial.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDataInicial.setForeground(new java.awt.Color(153, 153, 153));
        lblDataInicial.setText("lblDataInicial");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDataInicial)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(239, 239, 239));

        lblDataFinal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDataFinal.setForeground(new java.awt.Color(153, 153, 153));
        lblDataFinal.setText("lblDataFinal");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDataFinal)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(239, 239, 239));
        jPanel8.setForeground(new java.awt.Color(239, 239, 239));

        lblDataResultado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDataResultado.setForeground(new java.awt.Color(153, 153, 153));
        lblDataResultado.setText("lblDataResultado");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDataResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDataResultado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(239, 239, 239));

        lblPerguntaPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPerguntaPrincipal.setForeground(new java.awt.Color(153, 153, 153));
        lblPerguntaPrincipal.setText("escreva aqui");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPerguntaPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPerguntaPrincipal)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(239, 239, 239));

        lblTipoVotacao.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTipoVotacao.setForeground(new java.awt.Color(153, 153, 153));
        lblTipoVotacao.setText("Escolha Unica");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTipoVotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTipoVotacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(239, 239, 239));

        lblParticipantes.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblParticipantes.setForeground(new java.awt.Color(153, 153, 153));
        lblParticipantes.setText("lblParticipantes");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblParticipantes, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblParticipantes)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5))
                                .addGap(14, 14, 14))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 16, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7))
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pnlAcoesDinamicas.setLayout(new java.awt.CardLayout());

        btnParticipar.setBackground(new java.awt.Color(0, 153, 204));
        btnParticipar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnParticipar.setForeground(new java.awt.Color(255, 255, 255));
        btnParticipar.setText("✓ Participar");
        btnParticipar.setBorder(null);
        btnParticipar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParticiparActionPerformed(evt);
            }
        });
        pnlAcoesDinamicas.add(btnParticipar, "card4");

        btnEditar.setBackground(new java.awt.Color(0, 153, 204));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("✓ Editar");
        btnEditar.setBorder(null);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        pnlAcoesDinamicas.add(btnEditar, "card2");

        btnVerResultado.setBackground(new java.awt.Color(0, 153, 204));
        btnVerResultado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVerResultado.setForeground(new java.awt.Color(255, 255, 255));
        btnVerResultado.setText("✓ Resultado");
        btnVerResultado.setBorder(null);
        btnVerResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerResultadoActionPerformed(evt);
            }
        });
        pnlAcoesDinamicas.add(btnVerResultado, "card3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pnlAcoesDinamicas, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnVoltarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnAprovarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReprovarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(106, 106, 106))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAcoesDinamicas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAprovarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnReprovarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnVoltarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(80, 80, 80))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReprovarDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReprovarDialogActionPerformed
        MenuPrincipalView menuPrincipal = (MenuPrincipalView) javax.swing.SwingUtilities.getWindowAncestor(this);

        if (menuPrincipal == null) {
             javax.swing.JOptionPane.showMessageDialog(this, "Erro crítico: Tela principal não encontrada.");
             return;
         }

        try {
             VotacaoRepository votacaoRepo = new VotacaoRepository();
             votacaoRepo.atualizarStatus(this.votacaoAtual.getIdVotacao(), "REPROVADA");

             menuPrincipal.exibirMensagem("Votação reprovada.");

             menuPrincipal.atualizarDashboard();

             if (telaDeOrigem instanceof AprovarVotacaoView) {
                 ((AprovarVotacaoView) telaDeOrigem).carregarVotacoesParaAprovacao();
             }

             this.dispose();
        } catch (Exception e) {
             e.printStackTrace();
             menuPrincipal.exibirMensagemDeErro("Erro ao reprovar votação: " + e.getMessage());
        }
    }//GEN-LAST:event_btnReprovarDialogActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        MenuPrincipalView menuPrincipal = (MenuPrincipalView) javax.swing.SwingUtilities.getWindowAncestor(this);

        if (menuPrincipal == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro: Não foi possível encontrar a tela principal.");
            return;
        }

        if (this.votacaoAtual != null && this.usuarioLogado != null) {
            CriarVotacaoView telaDeEdicao = new CriarVotacaoView(menuPrincipal, this.usuarioLogado, this.votacaoAtual, true);
            telaDeEdicao.setVisible(true);

            menuPrincipal.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_btnEditarActionPerformed
    private void btnAprovarDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAprovarDialogActionPerformed
        MenuPrincipalView menuPrincipal = (MenuPrincipalView) javax.swing.SwingUtilities.getWindowAncestor(this);

        if (menuPrincipal == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro crítico: Tela principal não encontrada.");
            return;
        }

        try {
            VotacaoRepository votacaoRepo = new VotacaoRepository();
            votacaoRepo.atualizarStatus(this.votacaoAtual.getIdVotacao(), "APROVADA");

            menuPrincipal.exibirMensagemDeSucesso("Votação aprovada com sucesso!");

            menuPrincipal.atualizarDashboard();

            if (telaDeOrigem instanceof AprovarVotacaoView) {
                ((AprovarVotacaoView) telaDeOrigem).carregarVotacoesParaAprovacao();
            } else if (telaDeOrigem instanceof GerenciarVotacaoView) {
                ((GerenciarVotacaoView) telaDeOrigem).carregarVotacoesDoUsuario();
            }

            this.dispose(); 
        } catch (Exception e) {
            e.printStackTrace();
            menuPrincipal.exibirMensagemDeErro("Erro ao aprovar votação: ");
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
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblDataFinal;
    private javax.swing.JLabel lblDataInicial;
    private javax.swing.JLabel lblDataResultado;
    private javax.swing.JLabel lblParticipantes;
    private javax.swing.JLabel lblPerguntaPrincipal;
    private javax.swing.JLabel lblTipoVotacao;
    private javax.swing.JLabel lblTituloPrincipal;
    private javax.swing.JPanel pnlAcoesDinamicas;
    private javax.swing.JTextArea txtAreaDescricao;
    // End of variables declaration//GEN-END:variables
}
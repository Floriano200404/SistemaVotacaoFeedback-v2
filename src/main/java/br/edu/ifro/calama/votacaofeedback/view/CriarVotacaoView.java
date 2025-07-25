/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.view;

import br.edu.ifro.calama.votacaofeedback.model.Grupo;
import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.util.PlaceHolderUtil;
import br.edu.ifro.calama.votacaofeedback.util.RoundedVotacoesUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.border.Border;
import br.edu.ifro.calama.votacaofeedback.model.Votacao;
import br.edu.ifro.calama.votacaofeedback.repository.GrupoRepository;
import br.edu.ifro.calama.votacaofeedback.util.GrupoComboBoxRendererUtil;
import br.edu.ifro.calama.votacaofeedback.util.ToastUtil;
import br.edu.ifro.calama.votacaofeedback.view.CriarVotacaoOpcoesView;
import br.edu.ifro.calama.votacaofeedback.view.MenuPrincipalView;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author esten
 */

public class CriarVotacaoView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CriarVotacaoView.class.getName());
    private Usuario usuarioLogado;
    private Votacao votacaoParaEditar;
    private boolean isEditando;
    private MenuPrincipalView menuPrincipal;

    

    public CriarVotacaoView(MenuPrincipalView menu, Usuario usuario, Votacao votacao, boolean isEditando) {
        System.out.println("[DEBUG CriarVotacaoView] Recebeu isEditando = " + isEditando);
        initComponents();
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.menuPrincipal = menu;
        this.usuarioLogado = usuario;
        this.votacaoParaEditar = votacao;
        this.isEditando = isEditando;
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        if (this.votacaoParaEditar != null) {
            System.out.println("[DEBUG CriarVotacaoView] O campo votacaoParaEditar foi definido com o título: " + this.votacaoParaEditar.getTitulo());
        } else {
            System.out.println("[DEBUG CriarVotacaoView] O campo votacaoParaEditar continua nulo. Entrando em modo de criação.");
        }
        
        estilizarDateChooser(txtDataInicial);
        estilizarDateChooser(txtDataFinal);
        estilizarDateChooser(txtDataDivulgacao);
        
        if (this.usuarioLogado != null) {

            labelNomeUsuario.setText(this.usuarioLogado.getNome());
        }
        aplicarEstilos();
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);
        
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
        painelConteudo.addMouseListener(focusRemoverListener);
        
        this.revalidate();
        this.repaint();
        this.getContentPane().addMouseListener(focusRemoverListener);

        javax.swing.SwingUtilities.invokeLater(() -> {
            carregarGrupos();
            configurarModoTela();
        });
    }
    
    private void configurarModoTela() {
        if (this.isEditando) {
            TituloPrincipal.setText("EDITAR VOTAÇÃO");
            btnAvancar.setText("AVANÇAR");
            if (votacaoParaEditar != null){
                preencherFormulario();
            }
        } else {
            TituloPrincipal.setText("CRIAR VOTAÇÃO");
            btnAvancar.setText("AVANÇAR");
            
            PlaceHolderUtil.setPlaceholder(txtTitulo, "Digite o título da votação");
            PlaceHolderUtil.setPlaceholder(txtDescricao, "Descreva aqui os detalhes da sua votação...");
            txtDataInicial.setDate(new java.util.Date());
            txtDataFinal.setDate(new java.util.Date());
            txtDataDivulgacao.setDate(new java.util.Date());
        }
    }
     
    private void preencherFormulario() {
        txtTitulo.setText(votacaoParaEditar.getTitulo());
        txtDescricao.setText(votacaoParaEditar.getDescricao());
        txtDataInicial.setDate(votacaoParaEditar.getDataInicial());
        txtDataFinal.setDate(votacaoParaEditar.getDataFinal());
        txtDataDivulgacao.setDate(votacaoParaEditar.getDataResultado());

        javax.swing.DefaultComboBoxModel model = (javax.swing.DefaultComboBoxModel) comboParticipantes.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            Grupo grupo = (Grupo) model.getElementAt(i);
            if (grupo.getIdGrupo() == votacaoParaEditar.getIdGrupoDestino()) {
                comboParticipantes.setSelectedIndex(i);
                break;
            }
        }
    } 
    
    private void carregarGrupos() {
    try {
        List<Grupo> gruposParaExibir = new ArrayList<>();

        GrupoRepository grupoRepository = new GrupoRepository();
        gruposParaExibir.addAll(grupoRepository.buscarTodos());
        
        comboParticipantes.setModel(new javax.swing.DefaultComboBoxModel(gruposParaExibir.toArray()));
        comboParticipantes.setRenderer(new GrupoComboBoxRendererUtil("Selecionar Participantes"));
        comboParticipantes.setSelectedIndex(-1);
        
    } catch (Exception e) {
        exibirMensagem("Erro ao carregar grupos: " + e.getMessage());
        e.printStackTrace();
    }
}
    
    public javax.swing.JTextField getTxtTitulo() {
        return txtTitulo;
    }
    public javax.swing.JTextArea getTxtDescricao() {
        return txtDescricao;
    }
    public JDateChooser getTxtDataInicial() {
        return txtDataInicial;
    }
    public JDateChooser getTxtDataFinal() {
        return txtDataFinal;
    }
    public JDateChooser getTxtDataDivulgacao() {
        return txtDataDivulgacao;
    }
    public javax.swing.JComboBox getComboParticipantes() {
        return comboParticipantes;
    }

    public void exibirMensagem(String mensagem) {

        ToastUtil toast = new ToastUtil(this, mensagem, ToastUtil.ToastType.ERROR, ToastUtil.ToastPosition.TOP_RIGHT);
        toast.display();
    }
    
    public void exibirMensagemDeSucesso(String mensagem) {
    
        ToastUtil toast = new ToastUtil(
            this, mensagem, ToastUtil.ToastType.SUCCESS, ToastUtil.ToastPosition.TOP_RIGHT
        );
        toast.display();
    }
    
    private void estilizarDateChooser(com.toedter.calendar.JDateChooser dateChooser) {
        JTextField editor = (JTextField) dateChooser.getDateEditor().getUiComponent();
        editor.setBorder(null);
        editor.setBackground(java.awt.Color.WHITE);
        editor.setHorizontalAlignment(JTextField.CENTER);
        
        JButton calendarButton = dateChooser.getCalendarButton();
        calendarButton.setBorder(null);
        calendarButton.setContentAreaFilled(false);
        calendarButton.setFocusPainted(false);
    }
    private java.util.Date removerHoras(java.util.Date data) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(data);
        cal.set(java.util.Calendar.HOUR_OF_DAY, 0);
        cal.set(java.util.Calendar.MINUTE, 0);
        cal.set(java.util.Calendar.SECOND, 0);
        cal.set(java.util.Calendar.MILLISECOND, 0);
        return cal.getTime();
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
        labelLogo = new javax.swing.JLabel();
        painelHeaderDireita = new javax.swing.JPanel();
        labelNomeUsuario = new javax.swing.JLabel();
        labelIconePerfil = new javax.swing.JLabel();
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelIconePerfilMouseEntered(evt);
            }
        });
        painelHeaderDireita.add(labelIconePerfil);

        painelHeader.add(painelHeaderDireita, java.awt.BorderLayout.LINE_END);

        getContentPane().add(painelHeader, java.awt.BorderLayout.PAGE_START);

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

    private void labelLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelLogoMouseClicked
        try {
        MenuPrincipalView telaPrincipal = new MenuPrincipalView(this.usuarioLogado);
        telaPrincipal.setLocationRelativeTo(null);
        telaPrincipal.setVisible(true);
        
        this.dispose();

        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao carregar o menu principal: " + e.getMessage(), "Erro de Conexão", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_labelLogoMouseClicked

    private void txtTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTituloActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (this.menuPrincipal != null) {
            this.menuPrincipal.setVisible(true);
            this.menuPrincipal.atualizarDashboard();
        }
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAvancarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvancarActionPerformed
        try {
            String tituloPlaceholder = "Digite o título da votação";
            String titulo = getTxtTitulo().getText();

            String descricaoPlaceholder = "Descreva aqui os detalhes da sua votação...";
            String descricao = getTxtDescricao().getText();

            if (titulo.trim().isEmpty() || titulo.equals(tituloPlaceholder)) {
                exibirMensagem("O campo Título é obrigatório.");
                return;
            }

            if (descricao.equals(descricaoPlaceholder)) {
                descricao = "";
            }

            java.util.Date dataInicial = getTxtDataInicial().getDate();
            java.util.Date dataFinal = getTxtDataFinal().getDate();
            java.util.Date dataDivulgacao = getTxtDataDivulgacao().getDate();
            Object itemSelecionado = getComboParticipantes().getSelectedItem();

            if (dataInicial == null || dataFinal == null || dataDivulgacao == null) {
                exibirMensagem("Todos os campos de data devem ser preenchidos.");
                return;
            }
            if (itemSelecionado == null) {
                exibirMensagem("Por favor, selecione um grupo de participantes.");
                return;
            }

            java.util.Date hoje = removerHoras(new java.util.Date());
            if (removerHoras(dataInicial).before(hoje)) {
                exibirMensagem("A data inicial não pode ser anterior ao dia de hoje.");
                return;
            }
            if (dataFinal.before(dataInicial)) {
                exibirMensagem("A data final não pode ser anterior à data inicial.");
                return;
            }
            
            Grupo grupoSelecionado = (Grupo) itemSelecionado;
        
            Votacao votacaoParaEnviar;
            if (votacaoParaEditar != null) {
                votacaoParaEditar.setTitulo(titulo);
                votacaoParaEditar.setDescricao(descricao);
                votacaoParaEditar.setDataInicial(dataInicial);
                votacaoParaEditar.setDataFinal(dataFinal);
                votacaoParaEditar.setDataResultado(dataDivulgacao);
                votacaoParaEditar.setIdGrupoDestino(grupoSelecionado.getIdGrupo());
                votacaoParaEnviar = votacaoParaEditar;
            } else {
                Votacao novaVotacao = new Votacao();
                novaVotacao.setTitulo(titulo);
                novaVotacao.setDescricao(descricao);
                novaVotacao.setDataInicial(dataInicial);
                novaVotacao.setDataFinal(dataFinal);
                novaVotacao.setDataResultado(dataDivulgacao);
                novaVotacao.setIdGrupoDestino(grupoSelecionado.getIdGrupo());
                novaVotacao.setIdCriador(this.usuarioLogado.getId());
                novaVotacao.setStatus("PENDENTE");
                votacaoParaEnviar = novaVotacao;
            }

            CriarVotacaoOpcoesView telaDeOpcoes = new CriarVotacaoOpcoesView(this.menuPrincipal, this.usuarioLogado, votacaoParaEnviar);
            telaDeOpcoes.setLocationRelativeTo(null);
            telaDeOpcoes.setVisible(true);
            this.dispose();

        } catch (Exception e) {
            exibirMensagem("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }

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

    }//GEN-LAST:event_btnAvancarActionPerformed

    private void comboParticipantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboParticipantesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboParticipantesActionPerformed
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
    private void labelIconePerfilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIconePerfilMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_labelIconePerfilMouseEntered


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
    private javax.swing.JButton btnAvancar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> comboParticipantes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBotoesInferiores;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel labelIconePerfil;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JLabel labelNomeUsuario;
    private javax.swing.JPanel painelConteudo;
    private javax.swing.JPanel painelHeader;
    private javax.swing.JPanel painelHeaderDireita;
    private javax.swing.JPanel painelHeaderEsquerda;
    private javax.swing.JLabel tituloDivulga;
    private com.toedter.calendar.JDateChooser txtDataDivulgacao;
    private com.toedter.calendar.JDateChooser txtDataFinal;
    private com.toedter.calendar.JDateChooser txtDataInicial;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}

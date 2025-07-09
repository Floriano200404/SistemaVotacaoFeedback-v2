/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.controller;

import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.model.Votacao;
import br.edu.ifro.calama.votacaofeedback.repository.VotacaoRepository;
import br.edu.ifro.calama.votacaofeedback.view.CriarVotacaoView;
import br.edu.ifro.calama.votacaofeedback.view.MenuPrincipalView;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

/**
 *
 * @author Athos
 */
public class VotacaoController {

    private final CriarVotacaoView view;
    private final Usuario usuarioLogado;

    public VotacaoController(CriarVotacaoView view, Usuario usuario) {
        this.view = view;
        this.usuarioLogado = usuario;
    }

    public void salvarVotacao() {
        try {
            String titulo = view.getTxtTitulo().getText();
            String descricao = view.getTxtDescricao().getText();
            Date dataInicial = view.getTxtDataInicial().getDate();
            Date dataFinal = view.getTxtDataFinal().getDate();
            Date dataDivulgacao = view.getTxtDataDivulgacao().getDate();
            
            if (titulo.trim().isEmpty()) {
            view.exibirMensagem("O campo Título é obrigatório.");
            return;
            }
            if (dataInicial == null || dataFinal == null || dataDivulgacao == null) {
                view.exibirMensagem("Todos os campos de data devem ser preenchidos.");
                return;
            }
            if (dataFinal.before(dataInicial)) {
                view.exibirMensagem("A data final não pode ser anterior à data inicial.");
                return;
            }

            Votacao novaVotacao = new Votacao();
            novaVotacao.setTitulo(titulo);
            novaVotacao.setDescricao(descricao);
            novaVotacao.setDataInicio(dataInicial);
            novaVotacao.setDataFim(dataFinal);
            novaVotacao.setDataResultado(dataDivulgacao);
            novaVotacao.setIdCriador(this.usuarioLogado.getId());
            novaVotacao.setIdGrupoDestino(1);
            novaVotacao.setStatus("PENDENTE");

            VotacaoRepository repository = new VotacaoRepository();
            repository.criar(novaVotacao);
            
            view.exibirMensagemDeSucesso("Votação criada com sucesso!");
            Timer timer = new Timer(1500, e -> {
                new MenuPrincipalView(this.usuarioLogado).setVisible(true);
                view.dispose();
                
            });
            timer.setRepeats(false);
            timer.start();

        } catch (Exception e) {
            view.exibirMensagem("Erro ao criar votação: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

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
            String dataInicialStr = view.getTxtDataInicial().getText();
            String dataFinalStr = view.getTxtDataFinal().getText();
            String dataDivulgacaoStr = view.getTxtDataDivulgacao().getText();
            
            if (titulo.trim().isEmpty() || dataInicialStr.trim().length() < 10) {
                view.exibirMensagem("Título e datas são obrigatórios.");
                return;
            }

            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            Date dataInicio = formatador.parse(dataInicialStr);
            Date dataFim = formatador.parse(dataFinalStr);
            Date dataDivulgacao = formatador.parse(dataDivulgacaoStr);

            Votacao novaVotacao = new Votacao();
            novaVotacao.setTitulo(titulo);
            novaVotacao.setDescricao(descricao);
            novaVotacao.setDataInicio(dataInicio);
            novaVotacao.setDataFim(dataFim);
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

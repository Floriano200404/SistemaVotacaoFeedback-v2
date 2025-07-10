/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.controller;

import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.model.Votacao;
import br.edu.ifro.calama.votacaofeedback.service.VotacaoService;
import br.edu.ifro.calama.votacaofeedback.view.CriarVotacaoView;
import java.util.List;

/**
 *
 * @author Athos
 */
public class VotacaoController {

    private final VotacaoService votacaoService;
    
    public VotacaoController() {
        this.votacaoService = new VotacaoService();
    }

    public void atualizarVotacao(Votacao votacao) {
        try {
            votacaoService.atualizar(votacao);
        } catch (Exception e) {
            System.err.println("Erro no controller ao atualizar votação: " + e.getMessage());
            // Aqui você poderia, por exemplo, retornar um booleano ou lançar uma exceção
            // para a View saber que deu erro.
        }
    }

    /**
     * Pede ao serviço as votações criadas por um usuário específico.
     * Chamado pela GerenciarVotacaoView.
     * @param idCriador O ID do usuário.
     * @return Uma lista de votações.
     */
    public List<Votacao> buscarVotacoesPorCriador(int idCriador) {
        return votacaoService.buscarPorIdCriador(idCriador);
    }
    
    /**
     * Pede ao serviço as votações que estão pendentes de aprovação.
     * Chamado pela AprovarVotacaoView.
     * @return Uma lista de votações pendentes.
     */
    public List<Votacao> buscarVotacoesPendentes() {
        return votacaoService.getVotacoesParaAprovar();
    }
    
}

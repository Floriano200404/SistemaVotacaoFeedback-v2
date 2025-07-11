/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.controller;

import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.model.Votacao;
import br.edu.ifro.calama.votacaofeedback.service.VotacaoService;
import br.edu.ifro.calama.votacaofeedback.view.CriarVotacaoView;
import java.util.ArrayList;
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

    public void atualizarVotacao(Votacao votacao) throws Exception {
        votacaoService.atualizar(votacao);
    }
    
    public List<Votacao> buscarTodosPendentes() {
        try {
            return votacaoService.buscarTodosPendentes();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Votacao> buscarPendentesPorCriador(int idCriador) {
        try {
            return votacaoService.buscarPendentesPorCriador(idCriador);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public List<Votacao> buscarVotacoesPendentes() {
        return votacaoService.getVotacoesParaAprovar();
    }
    
    public int criarVotacao(Votacao votacao) {
        try {
            return votacaoService.criar(votacao);
        } catch (Exception e) {
            System.err.println("Erro no controller ao criar votação: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
    
    public int[] getDashboardStats() throws Exception {
        return votacaoService.getDashboardStats();
    }
    
}

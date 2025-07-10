/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.service;

import br.edu.ifro.calama.votacaofeedback.model.Votacao;
import br.edu.ifro.calama.votacaofeedback.repository.VotacaoRepository;

/**
 *
 * @author floriano
 */
public class VotacaoService {
    private VotacaoRepository votacaoRepository;
    
    public VotacaoService(){
        this.votacaoRepository = new VotacaoRepository();
    }
   
    public java.util.List<Votacao> getVotacoesParaAprovar() {
        try {
            VotacaoRepository votacaoRepo = new VotacaoRepository();
            return votacaoRepo.buscarPendentes();
        } catch (Exception e) {

            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }
    
    public int criar(Votacao votacao) throws Exception {
        return this.votacaoRepository.criar(votacao);
    }
    
    public void atualizar(Votacao votacao) throws Exception {
        votacaoRepository.atualizar(votacao);
    }
    
    public java.util.List<Votacao> buscarPorIdCriador(int idCriador) {
        try {
            return this.votacaoRepository.buscarPorIdCriador(idCriador);
        } catch (Exception e) {
            System.err.println("Erro na camada de serviço ao buscar por criador: " + e.getMessage());
            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }
    
}

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
   
public java.util.List<Votacao> getVotacoesParaAprovar() {
    try {
        
        VotacaoRepository votacaoRepo = new VotacaoRepository();
        return votacaoRepo.buscarPendentes();
    } catch (Exception e) {
        
        e.printStackTrace();
        return new java.util.ArrayList<>();
    }
}
    
}

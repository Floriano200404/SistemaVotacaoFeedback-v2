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
    // Este método vai DENTRO da sua classe de Serviço
public java.util.List<Votacao> getVotacoesParaAprovar() {
    try {
        // Supondo que você tenha uma instância do seu repositório aqui
        VotacaoRepository votacaoRepo = new VotacaoRepository();
        return votacaoRepo.buscarPendentes();
    } catch (Exception e) {
        // Trate o erro aqui (ex: mostre um log, retorne uma lista vazia)
        e.printStackTrace();
        return new java.util.ArrayList<>();
    }
}
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.service;

import br.edu.ifro.calama.votacaofeedback.model.OpcaoVoto;
import br.edu.ifro.calama.votacaofeedback.model.Votacao;
import br.edu.ifro.calama.votacaofeedback.repository.OpcaoVotoRepository;
import br.edu.ifro.calama.votacaofeedback.repository.VotacaoRepository;
import java.util.List;

/**
 *
 * @author floriano
 */
public class VotacaoService {
    private VotacaoRepository votacaoRepository;
    private OpcaoVotoRepository opcaoVotoRepository;
    
    public VotacaoService(){
        this.votacaoRepository = new VotacaoRepository();
        this.opcaoVotoRepository = new OpcaoVotoRepository();
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
    
    public void sincronizarOpcoes(int idVotacao, List<String> descricoesDaTela) throws Exception {
        List<OpcaoVoto> opcoesDoBanco = this.opcaoVotoRepository.buscarPorIdVotacao(idVotacao);
        int tamanhoDaTela = descricoesDaTela.size();
        int tamanhoDoBanco = opcoesDoBanco.size();

        int limiteParaUpdate = Math.min(tamanhoDaTela, tamanhoDoBanco);
        for (int i = 0; i < limiteParaUpdate; i++) {
            OpcaoVoto opcaoDoBanco = opcoesDoBanco.get(i);
            String novaDescricao = descricoesDaTela.get(i);
            if (!opcaoDoBanco.getDescricao().equals(novaDescricao)) {
                opcaoDoBanco.setDescricao(novaDescricao);
                this.opcaoVotoRepository.atualizar(opcaoDoBanco);
            }
        }

        if (tamanhoDaTela > tamanhoDoBanco) {
            for (int i = tamanhoDoBanco; i < tamanhoDaTela; i++) {
                OpcaoVoto novaOpcao = new OpcaoVoto();
                novaOpcao.setDescricao(descricoesDaTela.get(i));
                novaOpcao.setIdVotacao(idVotacao);
                this.opcaoVotoRepository.criar(novaOpcao);
            }
        }

        if (tamanhoDoBanco > tamanhoDaTela) {
            for (int i = tamanhoDaTela; i < tamanhoDoBanco; i++) {
                this.opcaoVotoRepository.deletar(opcoesDoBanco.get(i).getIdOpcaoVoto());
            }
        }
    }
    
}

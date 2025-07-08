/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.model;

/**
 *
 * @author Athos
 */
public class OpcaoVoto {
    
    private int idOpcaoVoto;
    private String descricao;
    private int idVotacao; // Chave estrangeira para ligar com a Votacao

    // Construtor vazio
    public OpcaoVoto() {}

    // Getters e Setters
    public int getIdOpcaoVoto() { return idOpcaoVoto; }
    public void setIdOpcaoVoto(int idOpcaoVoto) { this.idOpcaoVoto = idOpcaoVoto; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getIdVotacao() { return idVotacao; }
    public void setIdVotacao(int idVotacao) { this.idVotacao = idVotacao; }
}
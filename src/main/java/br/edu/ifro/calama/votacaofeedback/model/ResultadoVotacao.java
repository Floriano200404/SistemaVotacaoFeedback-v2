/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.model;

/**
 *
 * @author floriano
 */
public class ResultadoVotacao {
    private String descricaoOpcao;
    private int quantidadeVotos;

   
    public String getDescricaoOpcao() { return descricaoOpcao; }
    public void setDescricaoOpcao(String d) { this.descricaoOpcao = d; }
    public int getQuantidadeVotos() { return quantidadeVotos; }
    public void setQuantidadeVotos(int q) { this.quantidadeVotos = q; }
}

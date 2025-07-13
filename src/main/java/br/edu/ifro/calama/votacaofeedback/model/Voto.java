/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.model;

import java.util.Date;

public class Voto {
    private int idVoto;
    private int idVotacao;
    private int idUsuario;
    private int idOpcao;
    private Date data;
 
    public Voto() {
    }

    

    public int getIdVoto() {
        return idVoto;
    }

    public void setIdVoto(int idVoto) {
        this.idVoto = idVoto;
    }

    public int getIdVotacao() {
        return idVotacao;
    }

    public void setIdVotacao(int idVotacao) {
        this.idVotacao = idVotacao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdOpcao() {
        return idOpcao;
    }

    public void setIdOpcao(int idOpcao) {
        this.idOpcao = idOpcao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
}
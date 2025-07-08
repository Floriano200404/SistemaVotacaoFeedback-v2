/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.model;

/**
 *
 * @author Athos
 */
public class Grupo {
    private int idGrupo;
    private String nome;
    private String tipo_grupo;

    
    public int getIdGrupo() {
        return idGrupo;
    }
    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
        public String getTipo_grupo() {
        return tipo_grupo;
    }
    public void setTipo_grupo(String tipo) {
        this.tipo_grupo = tipo;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }
}

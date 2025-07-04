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

    // Construtores, Getters e Setters
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
    
    /**
     * Este método é o "truque" para o JComboBox funcionar facilmente.
     * Ele garante que o nome do grupo seja exibido na lista.
     */
    @Override
    public String toString() {
        return this.nome;
    }
}

package br.edu.ifro.calama.votacaofeedback.model;

/**
 * Representa uma única opção de voto dentro de uma votação.
 */
public class OpcaoVoto {

    private int idOpcaoVoto; // O nome da sua variável
    private String descricao;
    private int idVotacao;

    // Construtor vazio
    public OpcaoVoto() {
    }

    // --- GETTERS E SETTERS CORRIGIDOS ---

    // Método corrigido para retornar o ID da opção
    public int getIdOpcaoVoto() { 
        return idOpcaoVoto;
    }

    public void setIdOpcaoVoto(int idOpcaoVoto) {
        this.idOpcaoVoto = idOpcaoVoto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdVotacao() {
        return idVotacao;
    }

    public void setIdVotacao(int idVotacao) {
        this.idVotacao = idVotacao;
    }
}
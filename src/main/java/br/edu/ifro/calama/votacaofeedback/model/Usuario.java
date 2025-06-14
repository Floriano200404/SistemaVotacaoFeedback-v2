package br.edu.ifro.calama.votacaofeedback.model;

public class Usuario {
    private int id;
    private String login;
    private String senha;
    private String nome;
    private String tipo;

    public Usuario(int id, String login, String senha, String nome, String tipo) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.tipo = tipo;
    }

    // Getters
    public int getId() { return id; }
    public String getLogin() { return login; }
    public String getSenha() { return senha; }
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
}
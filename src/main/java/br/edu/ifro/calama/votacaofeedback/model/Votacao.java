package br.edu.ifro.calama.votacaofeedback.model;

public class Votacao {
    private String titulo;
    private String detalhes; // Usaremos para as datas de forma simples

    public Votacao(String titulo, String detalhes) {
        this.titulo = titulo;
        this.detalhes = detalhes;
    }

    public String getTitulo() { return titulo; }
    public String getDetalhes() { return detalhes; }
}
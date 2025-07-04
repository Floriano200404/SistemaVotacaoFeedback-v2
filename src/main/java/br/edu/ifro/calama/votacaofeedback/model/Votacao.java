package br.edu.ifro.calama.votacaofeedback.model;

import java.util.Date;

public class Votacao {
    private String titulo;
    private String detalhes; // Usaremos para as datas de forma simples
    private int idVotacao;
    private int idCriador;
    private int idGrupoDestino;
    private Date dataResultado;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private String status;
    private String pergunta;
    
    public Votacao() {
    }
    
    public Votacao(String titulo, String detalhes) {
        this.titulo = titulo;
        this.detalhes = detalhes;
    }

    public String getTitulo() { return titulo; }
    public String getDetalhes() { return detalhes; }
    
    // Novos getters e setters (e o setter para o título)
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getIdVotacao() { return idVotacao; }
    public void setIdVotacao(int idVotacao) { this.idVotacao = idVotacao; }

    public int getIdCriador() { return idCriador; }
    public void setIdCriador(int idCriador) { this.idCriador = idCriador; }

    public int getIdGrupoDestino() { return idGrupoDestino; }
    public void setIdGrupoDestino(int idGrupoDestino) { this.idGrupoDestino = idGrupoDestino; }

    public Date getDataResultado() { return dataResultado; }
    public void setDataResultado(Date dataResultado) { this.dataResultado = dataResultado; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Date getDataInicio() { return dataInicio; }
    public void setDataInicio(Date dataInicio) { this.dataInicio = dataInicio; }

    public Date getDataFim() { return dataFim; }
    public void setDataFim(Date dataFim) { this.dataFim = dataFim; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPergunta() { return pergunta; }
    public void setPergunta(String pergunta) { this.pergunta = pergunta; }
}
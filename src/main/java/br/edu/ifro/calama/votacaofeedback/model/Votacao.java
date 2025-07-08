package br.edu.ifro.calama.votacaofeedback.model;

import java.util.Date; 

public class Votacao {

    private int idVotacao;
    private String titulo;
    private String descricao;
    private String pergunta;
    private String status; 
    private Date dataInicio;
    private Date dataFim;
    private Date dataResultado;
    private int idCriador;
    private int idGrupoDestino;

   
    public Votacao() {
    }

    
 
public Votacao(String titulo, Date dataInicio, Date dataFim, Date dataResultado) {
    this.titulo = titulo;
    this.dataInicio = dataInicio;
    this.dataFim = dataFim;
    this.dataResultado = dataResultado;
}

    

    public int getIdVotacao() { return idVotacao; }
    public void setIdVotacao(int idVotacao) { this.idVotacao = idVotacao; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getPergunta() { return pergunta; }
    public void setPergunta(String pergunta) { this.pergunta = pergunta; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getDataInicio() { return dataInicio; }
    public void setDataInicio(Date dataInicio) { this.dataInicio = dataInicio; }

    public Date getDataFim() { return dataFim; }
    public void setDataFim(Date dataFim) { this.dataFim = dataFim; }

    public Date getDataResultado() { return dataResultado; }
    public void setDataResultado(Date dataResultado) { this.dataResultado = dataResultado; }

    public int getIdCriador() { return idCriador; }
    public void setIdCriador(int idCriador) { this.idCriador = idCriador; }

    public int getIdGrupoDestino() { return idGrupoDestino; }
    public void setIdGrupoDestino(int idGrupoDestino) { this.idGrupoDestino = idGrupoDestino; }
}
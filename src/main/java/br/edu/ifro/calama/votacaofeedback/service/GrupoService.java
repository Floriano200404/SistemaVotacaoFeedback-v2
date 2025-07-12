package br.edu.ifro.calama.votacaofeedback.service;


import br.edu.ifro.calama.votacaofeedback.model.Grupo;
import br.edu.ifro.calama.votacaofeedback.repository.GrupoRepository;
import java.util.ArrayList;
import java.util.List;


public class GrupoService {

    private GrupoRepository grupoRepository;

   
    public GrupoService() {
        this.grupoRepository = new GrupoRepository(); // Cria uma instância do nosso buscador de grupos
    }

    
    public List<Grupo> buscarTodosOsGrupos() {
        try {
           
            return grupoRepository.buscarTodos();
        } catch (Exception e) {
           
            e.printStackTrace();
            return new ArrayList<>(); 
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.controller;

import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.repository.UsuarioRepository;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CadastroController {

    private final UsuarioRepository repository;
    private final Map<String, Integer> grupoMap;

    public CadastroController() {
        this.repository = new UsuarioRepository();
        // Mapeia o nome do curso para o seu ID no banco. Facilita a busca.
        this.grupoMap = new HashMap<>();
        grupoMap.put("ANÁLISE E DESENVOLVIMENTO DE SISTEMAS", 5);
        grupoMap.put("ENGENHARIA CIVIL", 6);
        grupoMap.put("ENGENHARIA DE CONTROLE E AUTOMAÇÃO", 7);
        grupoMap.put("ENGENHARIA QUÍMICA", 8);
        grupoMap.put("FÍSICA", 9);
        grupoMap.put("PROFESSOR", 10);
        grupoMap.put("SERVIDORES DO CAMPUS", 11);
    }
    
    public void processarCadastro(String nome, String cpf, String matricula, String email, String senha, String perfilSelecionado) throws SQLException, Exception {
        
        String tipoUsuarioFinal;
        Integer idGrupoFinal = null;

        if (perfilSelecionado.equals("SERVIDORES DO CAMPUS")) {
            tipoUsuarioFinal = "ADMIN";
            idGrupoFinal = grupoMap.get("SERVIDORES DO CAMPUS");
        } else if (perfilSelecionado.equals("PROFESSOR")) {
            tipoUsuarioFinal = "DOCENTE";
            idGrupoFinal = grupoMap.get("PROFESSOR");
        } else {
            tipoUsuarioFinal = "DISCENTE";
            idGrupoFinal = grupoMap.get(perfilSelecionado);
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(nome);
        novoUsuario.setCpf(cpf);
        novoUsuario.setMatricula(matricula);
        novoUsuario.setEmail(email);
        novoUsuario.setSenha(senha);
        novoUsuario.setTipo_usuario(tipoUsuarioFinal);
        
        int novoIdUsuario = repository.cadastrar(novoUsuario);

        if (idGrupoFinal != null && novoIdUsuario > 0) {
            repository.associarUsuarioAGrupo(novoIdUsuario, idGrupoFinal);
        }
    }
}
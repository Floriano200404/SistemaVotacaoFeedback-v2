/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.controller;

import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.repository.UsuarioRepository;
import java.sql.SQLException;

public class CadastroController {

    public void cadastrarUsuario(String nome, String email, String senha) throws SQLException, Exception {
        // Cria um objeto do modelo com os dados da tela
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(nome);
        novoUsuario.setEmail(email);
        novoUsuario.setSenha(senha);

        // Cria uma instância do repositório e chama o método de cadastro
        UsuarioRepository repository = new UsuarioRepository();
        repository.cadastrar(novoUsuario);
    }
}

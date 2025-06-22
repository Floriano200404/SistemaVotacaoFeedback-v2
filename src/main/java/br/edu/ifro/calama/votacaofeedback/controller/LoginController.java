/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.controller;

// Imports necessários para a classe funcionar
import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.repository.UsuarioRepository;

/**
 * Controller responsável pela lógica de login.
 */
public class LoginController {

    public boolean realizarLogin(String login, String senha) throws Exception {
        
        UsuarioRepository repository = new UsuarioRepository();
        
        Usuario usuarioAutenticado = repository.autenticar(login, senha);
        return usuarioAutenticado != null;
    }
}

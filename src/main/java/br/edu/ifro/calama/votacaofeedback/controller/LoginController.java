/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.controller;

// Imports necessários para a classe funcionar
import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.repository.UsuarioRepository;
import java.sql.SQLException;

/**
 * Controller responsável pela lógica de login.
 */
public class LoginController {

    /**
     * Realiza a autenticação do usuário.
     *
     * @param login O email ou login fornecido pelo usuário.
     * @param senha A senha fornecida pelo usuário.
     * @return      `true` se o login for bem-sucedido, `false` caso contrário.
     */
    public boolean realizarLogin(String login, String senha) throws Exception {
        
        // Cria uma instância do repositório para acessar o banco
        UsuarioRepository repository = new UsuarioRepository();
        
        try {
            // Chama o método que busca o usuário no banco
            Usuario usuarioAutenticado = repository.autenticar(login, senha);

            // A LÓGICA PRINCIPAL:
            // Se o objeto retornado for diferente de nulo, o usuário existe.
            // A expressão "usuarioAutenticado != null" resulta em 'true' ou 'false'.
            return usuarioAutenticado != null;

        } catch (SQLException e) {
            // Em caso de erro de banco de dados, o login falha.
            // Imprime o erro no console para o desenvolvedor ver.
            e.printStackTrace(); 
            return false;
        }
    }
}

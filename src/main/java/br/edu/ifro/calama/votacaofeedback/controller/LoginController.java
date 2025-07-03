/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.controller;

import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.repository.UsuarioRepository;
import br.edu.ifro.calama.votacaofeedback.view.LoginView;
import br.edu.ifro.calama.votacaofeedback.view.MenuPrincipalView;


public class LoginController {

    private final LoginView view; // O controller precisa conhecer a view que o chamou

    // O construtor recebe a tela de login
    public LoginController(LoginView view) {
        this.view = view;
    }

    // Este será nosso novo método principal
    public void autenticar() {
        // 1. Pega os dados da View
        String email = view.getTxtLogin().getText(); // Crie esses getters na sua LoginView
        String senha = new String(view.getTxtSenha().getPassword());

        try {
            // 2. Chama o repositório
            UsuarioRepository repository = new UsuarioRepository();
            Usuario usuarioAutenticado = repository.autenticar(email, senha);

            // 3. A VERIFICAÇÃO FINAL E CORRETA
            if (usuarioAutenticado != null) {
                // SUCESSO: Oculta a tela de login
                view.setVisible(false);
                
                // Abre o Menu Principal, PASSANDO O OBJETO USUÁRIO COMPLETO
                MenuPrincipalView menuPrincipal = new MenuPrincipalView(usuarioAutenticado);
                menuPrincipal.setLocationRelativeTo(null); // Centraliza a nova tela
                menuPrincipal.setVisible(true);

                // Fecha a tela de login permanentemente
                view.dispose(); 
            } else {
                // FALHA: Mostra uma mensagem de erro na própria tela de login
                view.exibirMensagem("Email ou senha inválidos.");
            }

        } catch (Exception e) {
            // Trata qualquer erro de banco de dados
            view.exibirMensagem("Erro ao conectar com o banco de dados: " + e.getMessage());
            e.printStackTrace(); // Imprime o erro detalhado no console para depuração
        }
    }
}

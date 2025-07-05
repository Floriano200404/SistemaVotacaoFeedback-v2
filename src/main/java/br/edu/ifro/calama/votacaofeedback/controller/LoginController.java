/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.controller;

import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.repository.UsuarioRepository;
import br.edu.ifro.calama.votacaofeedback.view.LoginView;
import br.edu.ifro.calama.votacaofeedback.view.MenuPrincipalView;
import javax.swing.Timer;


public class LoginController {

    private final LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
    }

    public void autenticar() {
        String email = view.getTxtLogin().getText();
        String senha = new String(view.getTxtSenha().getPassword());

        try {
            UsuarioRepository repository = new UsuarioRepository();
            Usuario usuarioAutenticado = repository.autenticar(email, senha);

            if (usuarioAutenticado != null) {
                view.exibirMensagemDeSucesso("Login realizado com sucesso!");
                
                Timer timer = new Timer(1000, e -> {
                // AÇÃO ATRASADA:
                view.setVisible(false);
                
                MenuPrincipalView menuPrincipal = new MenuPrincipalView(usuarioAutenticado);
                menuPrincipal.setLocationRelativeTo(null);
                menuPrincipal.setVisible(true);

                view.dispose();
               });
                
                timer.setRepeats(false);
            
                timer.start();
                
            } else {
                view.exibirMensagem("Email ou senha inválidos.");
            }
        } catch (Exception e) {
            view.exibirMensagem("Erro ao conectar com o banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

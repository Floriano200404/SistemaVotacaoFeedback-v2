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

                // Usamos um Timer para dar tempo do Toast de sucesso aparecer
                Timer timer = new Timer(1000, e -> {
                    // A Ação de abrir a nova janela agora está dentro do seu próprio try-catch
                    try {
                        // Tenta criar e exibir a nova tela principal
                        MenuPrincipalView menuPrincipal = new MenuPrincipalView(usuarioAutenticado);
                        menuPrincipal.setLocationRelativeTo(null);
                        menuPrincipal.setVisible(true);

                        // Fecha a tela de login apenas se a tela principal abriu com sucesso
                        view.dispose();

                    } catch (Exception ex) {
                        // Se a tela principal não puder ser criada (ex: erro de banco),
                        // exibe o erro na TELA DE LOGIN, que ainda está visível.
                        view.exibirMensagem("Erro ao carregar o menu principal: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                });

                timer.setRepeats(false);
                timer.start();

                } else {
                    view.exibirMensagem("Email ou senha inválidos.");
                }
        } catch (Exception e) {
                // Este catch trata erros na autenticação em si (ex: falha de conexão)
                view.exibirMensagem("Erro ao conectar com o banco de dados: " + e.getMessage());
                e.printStackTrace();
        }
    }
}

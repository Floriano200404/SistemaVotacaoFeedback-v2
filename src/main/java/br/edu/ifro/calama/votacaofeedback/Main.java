package br.edu.ifro.calama.votacaofeedback;


import br.edu.ifro.calama.votacaofeedback.view.LoginView;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new LoginView().setVisible(true);
        });
    }
}
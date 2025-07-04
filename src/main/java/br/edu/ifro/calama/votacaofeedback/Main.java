package br.edu.ifro.calama.votacaofeedback;


import br.edu.ifro.calama.votacaofeedback.view.LoginView;
import br.edu.ifro.calama.votacaofeedback.view.MenuPrincipalView;

public class Main {
    
    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(() -> {

       new LoginView().setVisible(true);
//       new MenuPrincipalView().setVisible(true);
        });
    }
}
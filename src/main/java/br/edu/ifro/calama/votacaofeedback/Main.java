package br.edu.ifro.calama.votacaofeedback;
import br.edu.ifro.calama.votacaofeedback.view.LoginView;
import com.formdev.flatlaf.FlatLightLaf;
public class Main {

    public static void main(String[] args) {
        try {
            FlatLightLaf.setup();
        } catch (Exception ex) {
            System.err.println("Falha ao configurar o tema FlatLaf.");
        }
        java.awt.EventQueue.invokeLater(() -> {
            new LoginView().setVisible(true);
        });
    }
}
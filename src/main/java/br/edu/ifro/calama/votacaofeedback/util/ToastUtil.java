/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

//para deixar a mensagem de erro ou sucesso em baixo dos botões
public class ToastUtil extends JDialog {

    public enum ToastPosition {
        TOP_RIGHT,      // Canto superior direito
        BOTTOM_RIGHT,   // Canto inferior direito
        TOP_CENTER,     // Topo e centralizado
        BOTTOM_CENTER   // Embaixo e centralizado
    }
    
    public enum ToastType {
        SUCCESS, ERROR
    }

    public static final int DURATION = 2500; 
   
    public ToastUtil(JFrame owner, String message, ToastType type, ToastPosition position) {
        super(owner);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setAlwaysOnTop(true);
        setFocusable(false);

        JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        g2.dispose();
    }
};
        
        panel.setOpaque(false);
        
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(350, 50));
        if (type == ToastType.SUCCESS) {
            panel.setBackground(new Color(34, 177, 76));
        } else {
            panel.setBackground(new Color(237, 28, 36));
        }

        JLabel label = new JLabel(message);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label, gbc);

        add(panel);
        pack();

        int x, y;
    
    // Calcula as coordenadas com base no JFrame "dono"
    if (owner != null) {
        Point ownerLocation = owner.getLocationOnScreen();
        int ownerX = ownerLocation.x;
        int ownerY = ownerLocation.y;
        int ownerWidth = owner.getWidth();
        int ownerHeight = owner.getHeight();

        switch (position) {
            case TOP_CENTER:
                x = ownerX + (ownerWidth - getWidth()) / 2;
                y = ownerY + 40;
                break;
            case BOTTOM_CENTER:
                x = ownerX + (ownerWidth - getWidth()) / 2;
                y = ownerY + ownerHeight - getHeight() - 40;
                break;
            case BOTTOM_RIGHT:
                x = ownerX + ownerWidth - getWidth() - 15;
                y = ownerY + ownerHeight - getHeight() - 40;
                break;
            case TOP_RIGHT:
            default: // Posição padrão
                x = ownerX + ownerWidth - getWidth() - 15;
                y = ownerY + 40;
                break;
        }
        } else { // Calcula as coordenadas com base na tela inteira
            Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
            // Lógica similar para a tela inteira, se necessário (vamos focar no owner por enquanto)
            x = screenBounds.width - getWidth() - 15;
            y = screenBounds.height - getHeight() - 15;
        }

    setLocation(x, y);
}

    public ToastUtil(JFrame owner, JComponent anchor, String message, ToastType type) {
    this(owner, message, type, ToastPosition.TOP_RIGHT);

    if (anchor != null) {
        Point anchorLocation = anchor.getLocationOnScreen();
        int anchorX = anchorLocation.x;
        int anchorY = anchorLocation.y;
        int anchorWidth = anchor.getWidth();
        int anchorHeight = anchor.getHeight();
        
        int toastWidth = getWidth();

        int x = anchorX + (anchorWidth / 2) - (toastWidth / 2);
        
        int y = anchorY + anchorHeight + 10;
        
        this.setLocation(x, y);
    }
    }
    
    private void fadeOut() {
    // Este Timer é específico para a ANIMAÇÃO de fade-out.
    // Ele vai rodar a cada 50ms para diminuir a opacidade gradualmente.
    Timer fadeTimer = new Timer(50, e -> {
        // Pega a opacidade atual e a reduz um pouco
        float novaOpacidade = getOpacity() - 0.1f;
        
        if (novaOpacidade <= 0) {
            // Se ficou totalmente transparente, para o timer e fecha a janela
            ((Timer) e.getSource()).stop();
            dispose();
        } else {
            // Se ainda não, apenas atualiza a opacidade
            setOpacity(novaOpacidade);
        }
    });
    fadeTimer.setRepeats(true); // O timer da animação precisa repetir
    fadeTimer.start();
}
    
    public void display() {
        Timer timer = new Timer(DURATION, e -> {
            fadeOut();
        });
        timer.setRepeats(false);
        timer.start();

        setOpacity(1.0f);
        setVisible(true);
    }
}
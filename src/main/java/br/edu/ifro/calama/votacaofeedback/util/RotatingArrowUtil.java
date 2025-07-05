/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.util;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.SwingConstants;


/**
 *
 * @author esten
 */
public class RotatingArrowUtil extends JLabel {

    private double angle = 0; // Ângulo atual em graus
    private double targetAngle = 0;
    private Timer animator;

    public RotatingArrowUtil() {
        super("▼", SwingConstants.CENTER);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
    }

    // Método público para iniciar a animação
    public void rotateTo(double targetAngle, int duration) {
        this.targetAngle = targetAngle;
        
        if (animator != null && animator.isRunning()) {
            animator.stop();
        }

        double totalRotation = targetAngle - angle;
        int delay = 10; // milissegundos por frame
        int steps = duration / delay;
        double stepSize = totalRotation / steps;

        animator = new Timer(delay, e -> {
            angle += stepSize;
            repaint();

            // Verifica se a animação terminou
            if ((stepSize > 0 && angle >= targetAngle) || (stepSize < 0 && angle <= targetAngle)) {
                angle = targetAngle;
                ((Timer) e.getSource()).stop();
                repaint();
            }
        });

        animator.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Rotaciona o "pincel" em torno do centro do componente
        g2.rotate(Math.toRadians(angle), getWidth() / 2.0, getHeight() / 2.0);

        // Chama o método de pintura padrão do JLabel, que agora será desenhado rotacionado
        super.paintComponent(g2);
        g2.dispose();
    }
}

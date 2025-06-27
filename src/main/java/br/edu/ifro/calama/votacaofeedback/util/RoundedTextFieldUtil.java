/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.util;

/**
 *
 * @author Athos
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JTextField;

public class RoundedTextFieldUtil extends JTextField {

    private Shape shape;

    public RoundedTextFieldUtil() {
        setOpaque(false); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());

        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(getBackground());
        
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        
        super.paintComponent(g);
        
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        Graphics2D g2 = (Graphics2D) g.create();
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(getForeground());
        
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        
        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        }
        return shape.contains(x, y);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 *
 * @author Athos
 */
public class RoundedComboBoxUtil<E> extends JComboBox<E> {

    private Shape shape;
    private Color backgroundColor = Color.WHITE;
    private Color borderColor = new Color(200, 200, 200); // Um cinza claro para a borda
    private int cornerRadius = 15;

    public RoundedComboBoxUtil() {
        super();
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8)); // Margem interna

        setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = super.createArrowButton();
                button.setContentAreaFilled(false);
                button.setBorder(BorderFactory.createEmptyBorder());
                return button;
            }
        });
    }
    
    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }
    
    @Override
    public void setBackground(Color bg) {
        this.backgroundColor = bg;
        super.setBackground(bg);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        }
        return shape.contains(x, y);
    }
}

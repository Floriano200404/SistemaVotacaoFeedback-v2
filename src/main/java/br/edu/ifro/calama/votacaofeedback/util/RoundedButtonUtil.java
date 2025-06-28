/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

/**
 *
 * @author Athos
 */
public class RoundedButtonUtil extends JButton {

    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    
    private int radius = 15;

    public RoundedButtonUtil() {
        setColor(new Color(52, 152, 219));
        setColorOver(new Color(74, 172, 239));
        setColorClick(new Color(41, 128, 185));
        setBorderColor(new Color(52, 152, 219));
        
        setFont(new Font("Segoe UI", Font.BOLD, 12));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
    }

    public Color getColor() { return color; }
    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }
    public Color getColorOver() { return colorOver; }
    public void setColorOver(Color colorOver) { this.colorOver = colorOver; }
    public Color getColorClick() { return colorClick; }
    public void setColorClick(Color colorClick) { this.colorClick = colorClick; }
    public Color getBorderColor() { return borderColor; }
    public void setBorderColor(Color borderColor) { this.borderColor = borderColor; }
    public int getRadius() { return radius; }
    public void setRadius(int radius) { this.radius = radius; }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isPressed()) {
            g2.setColor(colorClick);
        } else if (getModel().isRollover()) {
            g2.setColor(colorOver);
        } else {
            g2.setColor(color);
        }

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.dispose();

        super.paintComponent(g);
    }
    
    @Override
    public boolean contains(int x, int y) {
        Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);
        return shape.contains(x, y);
    }
}
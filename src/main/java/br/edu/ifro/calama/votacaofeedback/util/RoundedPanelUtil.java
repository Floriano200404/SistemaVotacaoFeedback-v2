/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.util;

/**
 *
 * @author SEJUS-10885
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class RoundedPanelUtil extends JPanel {

    private Color backgroundColor;
    private int cornerRadius = 15;
    
    public RoundedPanelUtil() {
        super();
        setOpaque(false);
        this.backgroundColor = Color.WHITE;
        this.cornerRadius = 15;
    }

    public RoundedPanelUtil(LayoutManager layout, int radius) {
        super(layout);
        this.cornerRadius = radius;
        setOpaque(false);
    }

    public RoundedPanelUtil(LayoutManager layout, int radius, Color bgColor) {
        super(layout);
        this.cornerRadius = radius;
        this.backgroundColor = bgColor;
        setOpaque(false);
    }

    public RoundedPanelUtil(int radius) {
        super();
        this.cornerRadius = radius;
        setOpaque(false);
    }

    public RoundedPanelUtil(int radius, Color bgColor) {
        super();
        this.cornerRadius = radius;
        this.backgroundColor = bgColor;
        setOpaque(false);
    }
    
    // Getters e Setters
    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void setBackground(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (backgroundColor != null) {
            graphics.setColor(backgroundColor);
        } else {
            graphics.setColor(getBackground());
        }
        
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); 
        
    }

    public void removeAllItems() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void addItem(String selecionar_Perfil_Institucional) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

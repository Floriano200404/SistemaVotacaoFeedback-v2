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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author Athos
 */
public class RoundedButtonUtil extends JButton {


    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private int radius = 0;

    public RoundedButtonUtil() {

        setColor(new Color(52, 152, 219));
        colorOver = new Color(74, 172, 239);
        colorClick = new Color(41, 128, 185);
        borderColor = new Color(52, 152, 219);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        
        this.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);

        setColor(new Color(52, 152, 219)); // Azul padrão
        colorOver = new Color(74, 172, 239); // Azul mais claro para hover
        colorClick = new Color(41, 128, 185); // Azul mais escuro para clique
        borderColor = new Color(52, 152, 219);
        setContentAreaFilled(false); // Remove o preenchimento padrão do botão
        setBorderPainted(false);
        setFocusPainted(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                over = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                over = false;
            }

            @Override
            public void mousePressed(MouseEvent me) {
                setBackground(colorClick);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                // A cor volta para a cor de hover (se o mouse ainda estiver sobre) ou normal
                if (over) {
                    setBackground(colorOver);
                } else {
                    setBackground(color);
                }
            }
        });
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    public Color getColorOver() {
        return colorOver;
    }

    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public Color getColorClick() {
        return colorClick;
    }

    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        // Pinta a borda
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        // Define a cor de fundo com base no estado do mouse (pressionado, hover, normal)
        if (getModel().isPressed()) {
            g2.setColor(colorClick);
        } else if (getModel().isRollover()) {
            g2.setColor(colorOver);
        } else {
            g2.setColor(color);
        }

        // Pinta o fundo do botão com um pequeno recuo para a borda
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);

        g2.dispose();

        super.paintComponent(g);
    }
}

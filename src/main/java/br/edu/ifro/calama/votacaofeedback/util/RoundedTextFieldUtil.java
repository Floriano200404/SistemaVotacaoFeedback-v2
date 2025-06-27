/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.util;

/**
 *
 * @author SEJUS-10885
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
        // Torna o fundo do JTextField transparente, pois vamos desenhar nosso próprio fundo.
        setOpaque(false); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Cria uma cópia do objeto Graphics para não modificar o original
        Graphics2D g2 = (Graphics2D) g.create();
        
        // Ativa o antialiasing para que as bordas fiquem suaves
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Define a cor de fundo do nosso campo arredondado
        // getBackground() pega a cor definida por setBackground() no formulário
        g2.setColor(getBackground());
        
        // Preenche o fundo com um formato de retângulo arredondado
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        
        // Chama o método paintComponent da classe pai (JTextField)
        // Isso é ESSENCIAL para que o texto, o cursor e a seleção de texto sejam desenhados.
        super.paintComponent(g);
        
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Cria uma cópia do objeto Graphics
        Graphics2D g2 = (Graphics2D) g.create();
        
        // Ativa o antialiasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Define a cor da borda
        // getForeground() pega a cor definida por setForeground(), mas você pode usar uma cor fixa
        g2.setColor(getForeground());
        
        // Desenha a borda do retângulo arredondado
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        
        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        // Este método garante que a área de clique do campo corresponda ao formato arredondado
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        }
        return shape.contains(x, y);
    }
}

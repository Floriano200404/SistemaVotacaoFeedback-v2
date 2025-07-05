/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.util;

import java.awt.Component;
import java.awt.Cursor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author esten
 */

public class AlinhamentoSelectUtil extends DefaultListCellRenderer {

    public AlinhamentoSelectUtil() {
        setHorizontalAlignment(LEFT);
    }
    
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        // Chama o método da classe pai para obter o JLabel padrão
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        setBorder(new EmptyBorder(0, 0, 0, 10));
        
        return this;
    }
}
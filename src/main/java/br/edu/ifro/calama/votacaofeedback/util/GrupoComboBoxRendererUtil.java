/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.util;

import br.edu.ifro.calama.votacaofeedback.model.Grupo;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author Athos
 */
public class GrupoComboBoxRendererUtil extends DefaultListCellRenderer {

    private final String placeholder;

    public GrupoComboBoxRendererUtil(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        // Chama a implementação padrão para pegar o estilo (cores, etc.)
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof Grupo) {
            // Se o valor é um objeto Grupo, mostra o nome do grupo
            Grupo grupo = (Grupo) value;
            setText(grupo.getNome());
        } else if (value == null && index == -1) {
            // Se o valor é nulo (nenhum item selecionado), mostra o placeholder
            setText(placeholder);
        }
        
        return this;
    }
}

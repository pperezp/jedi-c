/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class CellRendererProfesorNoVigente implements ListCellRenderer{

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = new JLabel();
        ModeloListaProfesor model = (ModeloListaProfesor)list.getModel();
        label.setText(model.getElementAt(index).toString());
        label.setIcon(new ImageIcon(this.getClass().getResource("/imagenes/offline.png")));
        label.setOpaque(true);
        label.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));
        if(isSelected){
            label.setBackground(new Color(88,179,225));
            label.setForeground(Color.white);
        }else{
            label.setForeground(Color.black);
            label.setBackground(Color.white);
        }
        if(cellHasFocus){
            label.setBackground(new Color(88,179,225));
        }
        return label;
    }
    
}

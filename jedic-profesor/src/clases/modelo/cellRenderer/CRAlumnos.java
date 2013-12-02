/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo.cellRenderer;

import clases.modelo.listModel.LMAlumno;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Administrador
 */
public class CRAlumnos extends JLabel implements ListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//        JLabel label = new JLabel();
        LMAlumno model = (LMAlumno)list.getModel();
        this.setText(model.getElementAt(index).toString());
        this.setIcon(new ImageIcon(this.getClass().getResource("/imagenes/online.png")));
        this.setOpaque(true);
        this.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));
        if(isSelected){
            this.setBackground(new Color(88,179,225));
            this.setForeground(Color.white);
        }else{
            this.setForeground(Color.black);
            this.setBackground(Color.white);
        }
        if(cellHasFocus){
            this.setBackground(new Color(88,179,225));
        }
        return this;
    }
    
}

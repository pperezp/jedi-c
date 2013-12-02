/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;



/**
 *
 * @author Administrador
 */
public class CellRendererListaFonts implements javax.swing.ListCellRenderer{

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        ModeloListaFonts model = (ModeloListaFonts)list.getModel();
         JLabel label;
         
        Font f = (Font)model.getFontAt(index);
        label = new JLabel(f.getFontName());
        System.out.println(f.getName());
//        label.setFont(new Font(f.getName(), Font.PLAIN, 22));
        label.setFont(f);
        label.setOpaque(true);
         if(isSelected){
            label.setBackground(new Color(134,196,202));
            label.setForeground(Color.black);
        }else{
            label.setForeground(Color.black);
            label.setBackground(Color.white);
        }
        if (cellHasFocus) {
            label.setBackground(new Color(134, 196, 202));
        }
       
        return label;
    }
    
}

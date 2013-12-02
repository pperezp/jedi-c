/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class CellRendererSalas implements ListCellRenderer{

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        ListModel model = list.getModel();
        ModeloListaSalas m = (ModeloListaSalas)model;
        Sala sala = m.getSalaAt(index);
        Image im = new ImageIcon(getClass().getResource("/chat/imagenes/sala.png")).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        JLabel label = new JLabel((sala.getPassword().equalsIgnoreCase("")?"":"*")+sala.getNombreSala(),new ImageIcon(im), JLabel.LEFT);
        label.setToolTipText("<"+(sala.getPassword().equalsIgnoreCase("")?"Sala sin Password":"Sala con Password")+">");
        label.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));
        Font fuente = label.getFont();
        label.setOpaque(true);
        if(isSelected){
            label.setBackground(new Color(88,179,225));
            label.setForeground(Color.black);
            label.setFont(new Font(fuente.getFontName(), Font.BOLD, fuente.getSize()));
        }else{
            label.setForeground(Color.black);
            label.setBackground(Color.white);
            label.setFont(fuente);
            
        }
        
        if(cellHasFocus){
            label.setBackground(new Color(88,179,225));
        }else{
//            label.setBackground(Color.LIGHT_GRAY);
        }
        return label;
    }
    
}

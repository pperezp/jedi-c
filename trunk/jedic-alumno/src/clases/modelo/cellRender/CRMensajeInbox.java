/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo.cellRender;

import clases.modelo.MensajeInbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Fabiola
 */
public class CRMensajeInbox extends JLabel implements ListCellRenderer{

    @Override
    public Component getListCellRendererComponent(JList jlist, Object e, int i, boolean isSelected, boolean cellHasFocus) {
        MensajeInbox mi = (MensajeInbox)jlist.getModel().getElementAt(i);
        if(mi.isLeido()){
            this.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mensajeLeido.jpg")));
            this.setFont(new Font("Tahoma", Font.PLAIN, 12));
        }else{
            this.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mensajeNoLeido.png")));
            this.setFont(new Font("Tahoma", Font.BOLD, 12));
        }
        this.setText(mi.toString());
        this.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));
        this.setOpaque(true);
        this.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));
        if (isSelected) {
            this.setBackground(new Color(88, 179, 225));
            this.setForeground(Color.white);
        } else {
            this.setForeground(Color.black);
            this.setBackground(Color.white);
        }
//        if (cellHasFocus) {
//            this.setBackground(new Color(88, 179, 225));
//        }
        return this;
    }
    
}

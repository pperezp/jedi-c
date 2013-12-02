/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class JPanelFondo extends JPanel{
    private Image imagen;

    public JPanelFondo(Image imagen) {
        this.imagen = imagen;
    }
    
    public void setImage(Image imagen){
        this.imagen = imagen;
    }
    
    @Override
    public void paint(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);
        } else {
            setOpaque(true);
        }

        super.paint(g);
    }

}

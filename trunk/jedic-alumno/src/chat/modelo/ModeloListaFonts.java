/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo;


import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Administrador
 */
public class ModeloListaFonts implements javax.swing.ListModel{
    private String[] fonts ;
    
    public ModeloListaFonts(){
        fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    }
    
    public Font getFontAt(int index){
        return new Font(fonts[index], Font.PLAIN, 22);
    }

    @Override
    public int getSize() {
        return fonts.length;
    }

    @Override
    public Object getElementAt(int index) {
        return fonts[index];
    }

    @Override
    public void addListDataListener(ListDataListener l) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

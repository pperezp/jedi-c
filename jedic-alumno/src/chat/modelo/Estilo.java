/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo;

import java.awt.Color;
import java.awt.Font;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class Estilo {
    public static SimpleAttributeSet getEstiloMensajeConexion(){
        SimpleAttributeSet estilo = new SimpleAttributeSet();
        StyleConstants.setFontSize(estilo, 15);
        StyleConstants.setBold(estilo, true);
        StyleConstants.setForeground(estilo, Color.red);
        StyleConstants.setFontFamily(estilo, Font.MONOSPACED);
        StyleConstants.setItalic(estilo, true);
        return estilo;
    }
    
    public static SimpleAttributeSet getEstiloMensajeDesconexion(){
        SimpleAttributeSet estilo = new SimpleAttributeSet();
        StyleConstants.setFontSize(estilo, 15);
        StyleConstants.setBold(estilo, true);
        StyleConstants.setForeground(estilo, Color.blue);
        StyleConstants.setFontFamily(estilo, Font.MONOSPACED);
        StyleConstants.setItalic(estilo, true);
        return estilo;
    }
}

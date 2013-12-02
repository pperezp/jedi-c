/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.mensajes.hilo;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class MensajeHilo {

    public static void setMensaje(JLabel lbl, String msn, Color clr, int drc) {
        HiloMensaje hm = new HiloMensaje();
        hm.setMensaje(lbl, msn, clr, drc);
        hm.start();
    }
}

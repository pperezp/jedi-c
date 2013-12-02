/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.vista.mensajes;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author Patricio Pérez Pinto
 */
public final class MensajeHilo {

    /**
     * método para hacer más fácil al programador usar el hilo HiloMensaje.<br>
     * Se comenzará un hilo del tipo HiloMensaje
     * @param lbl JLabel donde irá el mensaje
     * @param msn el mensaje en sí
     * @param clr el color del mensaje
     * @param duracionEnSegundos la duración del mensaje en segundos
     * @see clases.mensajes.HiloMensaje
     */
    public static void setMensaje(JLabel lbl, String msn, Color clr, int duracionEnSegundos) {
        HiloMensaje hm = new HiloMensaje();
        hm.setMensaje(lbl, msn, clr, duracionEnSegundos);
        hm.start();
    }
}

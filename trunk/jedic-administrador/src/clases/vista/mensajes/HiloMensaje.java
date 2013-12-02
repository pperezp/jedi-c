/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.vista.mensajes;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Hilo encargado de poner mensajes en un JLabel, dependiendo de la
 * duración<br> que se haya puesto en el método setMensaje()
 * @author Patricio Pérez Pinto
 */
public final class HiloMensaje extends Thread {

    private String mensaje;
    private JLabel label;
    private int cont;
    private int duracion;
    private JTextField textField;
    private boolean isLabel;
    private boolean isTextField;

    @Override
    public void run() {
        label.setVisible(true);
        while (cont < duracion) {
            if (isLabel) {
                label.setText(mensaje);
            } else if (isTextField) {
                textField.setText(mensaje);
            }
            try {
                Thread.sleep(1000);
                cont++;
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloMensaje.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        label.setText("");
    }

    /**
     * método para setiar el mensaje que se quiera mostrar
     *
     * @param lbl JLabel donde irá el mensaje
     * @param msn el mensaje en sí
     * @param clr el color del mensaje
     * @param duracionEnSegundos la duración del mensaje en segundos
     */
    public void setMensaje(JLabel lbl, String msn, Color clr, int duracionEnSegundos) {
        mensaje = msn;
        label = lbl;
        label.setForeground(clr);
        cont = 0;
        duracion = duracionEnSegundos;
        isLabel = true;
        isTextField = false;
    }

    /**
     * método para setiar el mensaje que se quiera mostrar
     *
     * @param txt JTextField donde irá el mensaje
     * @param msn el mensaje en sí
     * @param clr el color del mensaje
     * @param duracionEnSegundos la duración del mensaje en segundos
     */
    public void setMensaje(JTextField txt, String msn, Color clr, int duracionEnSegundos) {
        mensaje = msn;
        textField = txt;
        textField.setForeground(clr);
        cont = 0;
        duracion = duracionEnSegundos;
        isLabel = false;
        isTextField = true;
    }
}

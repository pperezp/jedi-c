/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.vista.util;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Pato
 */
public class Cambiar {

    /**
     *
     * @param form
     * @param rutaIcono
     */
    public static void cambiarIcono(JFrame form, String rutaIcono) {
        form.setIconImage(new ImageIcon(form.getClass().getResource(rutaIcono)).getImage());
    }
}

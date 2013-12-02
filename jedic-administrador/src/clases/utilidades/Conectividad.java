/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.utilidades;

import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Administrador
 */
public class Conectividad {
    public static boolean isConectado() {
        boolean e;
        try {
            URL ruta = new URL("http://www.google.cl");
            URLConnection rutaC = ruta.openConnection();
            rutaC.connect();
            e = true;
        } catch (Exception err) {
            e = false;
        }
        return e;
    }
}

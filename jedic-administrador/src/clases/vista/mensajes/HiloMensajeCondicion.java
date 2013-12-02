/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.vista.mensajes;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *Hilo encargado de mandar un mensaje mientras otor hilo se este ejecutando
 * @author Patricio Pérez Pinto
 */
public final class HiloMensajeCondicion extends Thread {

    private Thread hr;
    private JLabel lblMensaje;

    /**
     * Constructor
     * @param hiloDeCondicion es el hilo que será el principal. Mientras este <br>
     * hilo este vivo, el HiloMensajeCondicion estará vivo tambien
     * @param lblMensaje el JLabel donde irá el mensaje (el mensaje debe venir setiado en el JLabel)
     */
    public HiloMensajeCondicion(Thread hiloDeCondicion, JLabel lblMensaje) {
        this.hr = hiloDeCondicion;
        this.lblMensaje = lblMensaje;
        lblMensaje.setForeground(Color.red);
    }

    @Override
    public void run() {
        int c = 0;
        while (hr.isAlive() || this.isInterrupted()) {//MIENTRAS EL HILO DEL REPORTE ESTE VIVO O ESTE HILO (HiloMensajeCondicion) NO HALLA SIDO INTERRUMPIDO
            try {
                lblMensaje.setVisible((c % 2 == 0 ? true : false));//HAGO ESTO PARA QUE EL MENSAJE PARPADEE
                c++;//AUMENTO EL CONTADOR
                Thread.sleep(200);//PAUSA DE 200 MILISEGUNDOS
            } catch (InterruptedException ex) {
                lblMensaje.setVisible(false);
            } finally {
                lblMensaje.setVisible(false);
            }
        }
    }
}

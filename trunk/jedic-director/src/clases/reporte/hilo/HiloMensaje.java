/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.reporte.hilo;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class HiloMensaje extends Thread{
    private HiloReporte hr;
    private JLabel lblMensaje;

    public HiloMensaje(HiloReporte hr, JLabel lblMensaje) {
        this.hr = hr;
        this.lblMensaje = lblMensaje;
        lblMensaje.setForeground(Color.red);
    }
    
    
    
    @Override
    public void run(){
        int c = 0;
        while(hr.isAlive() || this.isInterrupted()){//MIENTRAS EL HILO DEL REPORTE ESTE VIVO O ESTE HILO (HiloMensaje) NO HALLA SIDO INTERRUMPIDO
            try {
                lblMensaje.setVisible((c % 2 == 0 ? true:false));//HAGO ESTO PARA QUE EL MENSAJE PARPADEE
                c++;//AUMENTO EL CONTADOR
                Thread.sleep(200);//PAUSA DE 200 MILISEGUNDOS
            } catch (InterruptedException ex) {
                lblMensaje.setVisible(false);
            }finally{
                lblMensaje.setVisible(false);
            }
        }
    }
}

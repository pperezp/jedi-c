/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.utilidades;

import java.awt.Color;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class HiloMensaje extends Thread {

    private String mensaje;
    private JLabel label;
    private int cont;
    private int duracion;
    private JTextField textField;
    private boolean isLabel;
    private boolean isTextField;

    @Override
    public void run() {
        while (cont < duracion) {
            if (isLabel) {
//                if(cont % 2 == 0){
                    label.setText(mensaje);
//                }else{
//                    label.setText("");
//                }
            } else if (isTextField) {
//                if(cont % 2 == 0){
                    textField.setText(mensaje);
//                }else{
//                    textField.setText("");
//                }
                
            }

//            System.out.println("\n"+this.getName()+"  ------>  "+cont);
            try {
                Thread.sleep(1000);
                cont++;
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloMensaje.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        label.setText("");
    }

    public void setMensaje(JLabel lbl, String msn, Color clr, int duracion) {
        mensaje = msn;
        label = lbl;
        label.setForeground(clr);
        cont = 0;
        this.duracion = duracion;
        isLabel = true;
        isTextField = false;
    }

    public void setMensaje(JTextField txt, String msn, Color clr, int duracion) {
        mensaje = msn;
        textField = txt;
        textField.setForeground(clr);
        cont = 0;
        this.duracion = duracion;
        isLabel = false;
        isTextField = true;
    }
}

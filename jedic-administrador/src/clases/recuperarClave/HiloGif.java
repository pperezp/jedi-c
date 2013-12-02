/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.recuperarClave;

import clases.vista.util.Redimensionar;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class HiloGif extends Thread {

    private HiloRecuperarClave hc;
    private JFrame frame;
    private JLabel lblMen;
    private int cont;

    /**
     *
     * @param hc Objeto del Tipo HiloRecuperarContraseña
     * @param frame Es el Frame que contiene el gif que dice enviando o procesando
     * @param lblMen Es el Label que contiene el mensaje enviando o procesando
     */
    public HiloGif(HiloRecuperarClave hc, JFrame frame, JLabel lblMen) {
        this.hc = hc;
        this.frame = frame;
        this.lblMen = lblMen;
        frame.setAlwaysOnTop(true);
        Redimensionar.redimensionarFormulario(frame, false, "");
        cont = 0;
    }

    @Override
    public void run() {
        try {
            while (hc.isAlive()) {
                cont++;
                if (!lblMen.getText().equalsIgnoreCase("Enviado!")) {
                    switch (cont) {
                        case 1: {
                            lblMen.setText(lblMen.getText().replaceAll("\\.", ""));
                            break;
                        }
                        case 2: {
                            lblMen.setText(lblMen.getText() + ".");
                            break;
                        }
                        case 3: {
                            lblMen.setText(lblMen.getText() + ".");
                            break;
                        }
                        case 4: {
                            lblMen.setText(lblMen.getText() + ".");
                            cont = 0;
                        }
                    }
                } else {
                    lblMen.setText(lblMen.getText().replaceAll("\\.", ""));
                }


                if (!frame.isVisible()) {
                    frame.setVisible(true);
                }
                Thread.sleep(250);

            }
        } catch (InterruptedException ex) {
            System.out.println("Hilo Gif interrumpido");
            frame.setVisible(false);
        }
        frame.setVisible(false);
    }
}

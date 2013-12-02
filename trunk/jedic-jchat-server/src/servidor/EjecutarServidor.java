/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class EjecutarServidor {
    

    public static void main(String[] args) {
        String puerto = javax.swing.JOptionPane.showInputDialog("Puerto", "4500");
        Servidor serv = new Servidor(Integer.parseInt(puerto));
        serv.iniciarServidor();
    }
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo.mensajes;

import chat.modelo.Cliente;



/**
 *
 * @author Patricio Pérez Pinto
 */
public class MensajeConexion extends Cliente implements java.io.Serializable{

    private String mensaje;
    public MensajeConexion(int codigo, String nombre, String mensaje) {
        super(codigo, nombre);
        this.mensaje = nombre.toUpperCase() + " " +mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}

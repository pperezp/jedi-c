/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo.cliente;

import chat.modelo.Cliente;

/**
 *
 * @author Administrador
 */
public class ClienteDesconectado extends Cliente implements java.io.Serializable{
    private final String salaActual;
    
    public ClienteDesconectado(int codigo, String nombre, String nick, final String salaActual) {
        super(codigo, nombre, nick);
        this.salaActual = salaActual;
    }

    public String getSalaActual() {
        return salaActual;
    }
}

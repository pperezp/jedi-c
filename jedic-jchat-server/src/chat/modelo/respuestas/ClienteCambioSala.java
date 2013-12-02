/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo.respuestas;

import java.io.Serializable;
import chat.modelo.Cliente;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class ClienteCambioSala extends Cliente implements Serializable{
    private String sala;

    public ClienteCambioSala(int codigo, String nombre, String nick, String sala) {
        super(codigo, nombre, nick);
        this.sala = sala;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }
    
    
}

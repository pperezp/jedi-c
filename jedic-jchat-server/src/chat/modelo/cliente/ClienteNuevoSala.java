/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo.cliente;

import java.io.Serializable;
import chat.modelo.Cliente;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class ClienteNuevoSala extends ClienteNuevo implements Serializable{

    public ClienteNuevoSala(Cliente cliente) {
        super(cliente);
    }
    
}

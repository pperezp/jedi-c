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
public class RemoverUsuarioDeSala extends Cliente implements Serializable{

    public RemoverUsuarioDeSala(Cliente cliente) {
        super(cliente.getCodigo(), cliente.getNombre(), cliente.getNick());
    }   
}

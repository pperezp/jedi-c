/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo.respuestas;

import chat.modelo.Cliente;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class RespuestaPeticionActualizarImagen extends Cliente implements java.io.Serializable{

    public RespuestaPeticionActualizarImagen(Cliente cliente) {
        super(cliente.getCodigo(), cliente.getNombre(), cliente.getNick(), cliente.getImagen(), cliente.getEstilo());
    }

    
    
}

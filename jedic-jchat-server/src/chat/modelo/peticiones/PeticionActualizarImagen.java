/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo.peticiones;

import chat.modelo.Cliente;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class PeticionActualizarImagen extends Cliente implements java.io.Serializable{

    public PeticionActualizarImagen(Cliente cliente) {
        super(cliente.getCodigo(), cliente.getNombre(), cliente.getNick(), cliente.getImagen(), cliente.getEstilo());
    }
}

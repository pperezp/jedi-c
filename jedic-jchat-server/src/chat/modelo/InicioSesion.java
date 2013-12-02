/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo;

import java.io.Serializable;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class InicioSesion extends Cliente implements Serializable{
    public InicioSesion(Cliente cliente) {
        super(cliente.getCodigo(), cliente.getNombre(), cliente.getNick(), cliente.getImagen(), cliente.getEstilo());
    }
}

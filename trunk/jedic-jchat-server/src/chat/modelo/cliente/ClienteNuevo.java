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
public class ClienteNuevo extends Cliente implements java.io.Serializable{

    public ClienteNuevo(Cliente cliente) {
        super(cliente.getCodigo(), cliente.getNombre(), cliente.getNick(), cliente.getImagen(), cliente.getEstilo());
    }
}

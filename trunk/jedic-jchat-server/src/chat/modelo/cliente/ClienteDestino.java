/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo.cliente;

import java.io.Serializable;
import javax.swing.text.SimpleAttributeSet;
import chat.modelo.Cliente;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class ClienteDestino extends Cliente implements Serializable{

    public ClienteDestino(int codigo, String nombre, String nick, SimpleAttributeSet estilo) {
        super(codigo, nombre, nick, estilo);
    }

   public ClienteDestino(Cliente cliente) {
        super(cliente.getCodigo(), cliente.getNombre(), cliente.getNick(), cliente.getImagen(), cliente.getEstilo());
    }
    
    
}

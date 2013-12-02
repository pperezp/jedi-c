/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo.cliente;


import chat.modelo.Cliente;
import java.net.Socket;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class ClienteConSocket extends Cliente {
    private Socket socket;
    
    public ClienteConSocket(Cliente cliente, Socket socket) {
        super(cliente.getCodigo(), cliente.getNombre(), cliente.getNick(), cliente.getImagen(), cliente.getEstilo());
        this.socket = socket;
    }
    
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}

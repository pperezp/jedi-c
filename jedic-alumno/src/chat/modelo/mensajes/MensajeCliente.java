/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo.mensajes;

import java.io.Serializable;
import chat.modelo.Cliente;
import chat.modelo.cliente.ClienteDestino;
import chat.modelo.cliente.ClienteOrigen;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class MensajeCliente extends Cliente implements Serializable{
    private ClienteDestino clienteDestino;
    private boolean isParaTodos;
    private String mensaje;
    private String nombreSala;

    public MensajeCliente(String mensaje, ClienteDestino clienteReceptor, ClienteOrigen clienteEmisor, String nombreSala) {
        super(clienteEmisor.getCodigo(), clienteEmisor.getNombre(), clienteEmisor.getNick(), clienteEmisor.getEstilo());
        this.mensaje = mensaje;
        this.clienteDestino = clienteReceptor;
        this.isParaTodos = false;
        this.nombreSala = nombreSala;
    }
    
    public MensajeCliente(String mensaje, ClienteOrigen clienteEmisor, boolean isParaTodos, String nombreSala) {
        super(clienteEmisor.getCodigo(), clienteEmisor.getNombre(), clienteEmisor.getNick(), clienteEmisor.getEstilo());
        this.clienteDestino = null;
        this.isParaTodos = isParaTodos;
        this.mensaje = mensaje;
        this.nombreSala = nombreSala;
    }

    public ClienteDestino getClienteDestino() {
        return clienteDestino;
    }

    public void setClienteDestino(ClienteDestino clienteDestino) {
        this.clienteDestino = clienteDestino;
    }
    
    public ClienteOrigen getClienteEmisor() {
        return new ClienteOrigen(super.getCodigo(), super.getNombre(), super.getNick(), super.getEstilo());
    }

    public boolean isIsParaTodos() {
        return isParaTodos;
    }

    public void setIsParaTodos(boolean isParaTodos) {
        this.isParaTodos = isParaTodos;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }
    
    
}

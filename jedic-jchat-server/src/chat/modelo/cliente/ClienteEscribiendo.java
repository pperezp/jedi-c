/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo.cliente;

import java.io.Serializable;
import chat.modelo.Cliente;
import chat.modelo.IEscribiendo;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class ClienteEscribiendo extends Cliente implements IEscribiendo, Serializable{
    private Cliente cliente;
    private String salaActual;
    
    public ClienteEscribiendo(Cliente cliente, String salaActual) {
        super(cliente.getCodigo(), cliente.getNombre(), cliente.getNick());
        this.salaActual = salaActual;
    }
    
    @Override
    public void setAquienEscribe(Cliente cliente){
        this.cliente = new Cliente(cliente.getCodigo(), cliente.getNombre(), cliente.getNick());
    }
    
    @Override
    public Cliente getClienteAquienEscribe(){
        return cliente;
    }
    
    @Override
    public Cliente getClienteQueEstaEscribiendo(){
        return new Cliente(super.getCodigo(), super.getNombre(), super.getNick());
    }

    public String getSalaActual() {
        return salaActual;
    }

    public void setSalaActual(String salaActual) {
        this.salaActual = salaActual;
    }
    
    
}

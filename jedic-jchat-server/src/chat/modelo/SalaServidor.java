/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo;

import chat.modelo.cliente.ClienteConSocket;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class SalaServidor extends Sala implements Serializable{
    private ArrayList<ClienteConSocket> clientes;
    
    public SalaServidor(Sala sala){
        super(sala.getDueñoSala(), sala.getNombreSala(), sala.getPassword());
        clientes = new ArrayList<>();
    }

    public synchronized ArrayList<ClienteConSocket> getClientesConSocket() {
        return clientes;
    }
    
    public synchronized ArrayList<Socket> getSocketsDeClientes(){
        ArrayList<Socket> lista = new ArrayList<>();
        for(ClienteConSocket c: clientes){
            lista.add(c.getSocket());
        }
        return lista;
    }
    
    public synchronized ArrayList<Cliente> getClientes(){
        ArrayList<Cliente> lista = new ArrayList<>();
        for(ClienteConSocket c : clientes){
            lista.add(new Cliente(c.getCodigo(), c.getNombre(), c.getNick(), c.getImagen(), c.getEstilo()));
        }
        return lista;
    }
    
    public synchronized void addCliente(ClienteConSocket cliente){
        clientes.add(cliente);
    }
    
    private synchronized void removeCliente(ClienteConSocket cliente){
        clientes.remove(cliente);
    }
    
    /**
     * 
     * @param nombreCliente
     * @return me entraga el clienteConSocket que acabo de sacar de la sala, para ponerlo en el lobi 
     */
    public synchronized ClienteConSocket removeCliente(String nombreCliente){
        ClienteConSocket cliente = null;
        for(ClienteConSocket c:clientes){
            if(c.getNombre().equalsIgnoreCase(nombreCliente)){
                cliente = c;
                removeCliente(c);
                return cliente;
            }
        }
        return cliente;
    }
    
//    public synchronized int getCantidadDeClientes(){
//        return clientes.size();
//    }
    
    public synchronized Sala getSala(){
        Sala s = new Sala(super.getDueñoSala(), super.getNombreSala(), super.getPassword());
        s.setCantidadDeClientes(super.getCantidadDeClientes());
        return s;
    }
    
    public synchronized Socket getSocketDe(String nombreCliente){
        for(ClienteConSocket c: clientes){
            if(c.getNombre().equalsIgnoreCase(nombreCliente)){
                return c.getSocket();
            }
        }
        return null;
    }
    
}

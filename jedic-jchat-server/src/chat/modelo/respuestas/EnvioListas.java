/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo.respuestas;

import java.io.Serializable;
import java.util.ArrayList;
import chat.modelo.Cliente;
import chat.modelo.Sala;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class EnvioListas implements Serializable{
    private ArrayList<Cliente> clientes;
    private ArrayList<Sala> salas;

    public EnvioListas(ArrayList<Cliente> clientes, ArrayList<Sala> salas) {
        this.clientes = clientes;
        this.salas = salas;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Sala> getSalas() {
        return salas;
    }

    public void setSalas(ArrayList<Sala> salas) {
        this.salas = salas;
    }
    
    
}

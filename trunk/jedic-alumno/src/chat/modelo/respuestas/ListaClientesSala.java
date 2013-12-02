/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo.respuestas;

import java.io.Serializable;
import java.util.ArrayList;
import chat.modelo.Cliente;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class ListaClientesSala implements Serializable{
    private ArrayList<Cliente> clientes;
    private String nombreSala;

    public ListaClientesSala(ArrayList<Cliente> clientes, String nombreSala) {
        this.clientes = clientes;
        this.nombreSala = nombreSala;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }
    
    
}

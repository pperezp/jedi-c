/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class ConexionExitosa implements Serializable{
    private final ArrayList<Cliente> listaClientes;
    private final ArrayList<Sala> salas;

    public ConexionExitosa(ArrayList<Cliente> listaClientes, ArrayList<Sala> salas) {
        this.listaClientes = listaClientes;
        this.salas = salas;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public ArrayList<Sala> getListaSalas() {
        return salas;
    }
    
    
}

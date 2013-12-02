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
public class Sala extends Cliente implements Serializable{
    private String nombreSala;
    private String password;
    private int cantidadDeClientes;
    
    
    /**
     * Constructor para crear una sala por parte de un cliente
     * @param clienteDuenoSala
     * @param nombreSala
     * @param password 
     */
    public Sala(Cliente clienteDuenoSala, String nombreSala, String password){
        super(clienteDuenoSala.getCodigo(), clienteDuenoSala.getNombre(), clienteDuenoSala.getNick());
        this.nombreSala = nombreSala;
        this.password = password;
        cantidadDeClientes = 0;//el dueño
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Cliente getDueñoSala(){
        return new Cliente(super.getCodigo(), super.getNombre(), super.getNick());
    }

    public int getCantidadDeClientes() {
        return cantidadDeClientes;
    }

    public void aumentarClientesEnUno() {
        cantidadDeClientes = cantidadDeClientes + 1 ;
    }
    
    public void disminuirClientesEnUno() {
        cantidadDeClientes = cantidadDeClientes - 1 ;
    }

    public void setCantidadDeClientes(int cantidadDeClientes) {
        this.cantidadDeClientes = cantidadDeClientes;
    }
}

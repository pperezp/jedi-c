/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo.peticiones;

import java.io.Serializable;
import chat.modelo.Cliente;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class AbandonarSala extends Cliente implements Serializable{
    private String nombreSala;

    public AbandonarSala(String nombreSala, Cliente cliente) {
        super(cliente.getCodigo(), cliente.getNombre(), cliente.getNick());
        this.nombreSala = nombreSala;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }
    
    
}

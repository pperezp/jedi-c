/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo.respuestas;

import java.io.Serializable;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class SalaYaExiste implements Serializable{
    private String nombreSala;

    public SalaYaExiste(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }
    
    
}

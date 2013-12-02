/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo.respuestas;

import java.io.Serializable;
import java.util.ArrayList;
import chat.modelo.Sala;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class ListaDeMisSalas implements Serializable{
    private ArrayList<Sala> salas;

    public ListaDeMisSalas(ArrayList<Sala> salas) {
        this.salas = salas;
    }

    public ArrayList<Sala> getSalas() {
        return salas;
    }

    public void setSalas(ArrayList<Sala> salas) {
        this.salas = salas;
    }
    
    
}

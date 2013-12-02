/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo.peticiones;

import java.io.Serializable;
import chat.modelo.Sala;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class PeticionCerrarSala extends Sala implements Serializable{
    public PeticionCerrarSala(Sala sala){
        super(sala.getDueñoSala(), sala.getNombreSala(), sala.getPassword());
    }
}
